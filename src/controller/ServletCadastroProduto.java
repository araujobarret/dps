package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Categoria;
import model.beans.Produto;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ProdutoDAO;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;



/**
 * Servlet implementation class ServletCadastroProduto
 */
@WebServlet("/ServletCadastroProduto")
public class ServletCadastroProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws NoSuchAlgorithmException 
     */
    @SuppressWarnings("deprecation")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NoSuchAlgorithmException
    {

        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "?mensagem="; // Mensagem devolvida ao JSP de cadastro do funcionário
        
        try
        {                    
          try
          {
        	  // Criação de objeto categoria
              Categoria categoriaId = new Categoria();
              
              String descricao = "", caracteristicas = "";
              int quantidade = 0, status = 0;
              BigDecimal preco_custo = new BigDecimal(0);
              BigDecimal preco_venda = new BigDecimal(0);
              
	          DiskFileItemFactory factory = new DiskFileItemFactory();	  
			  ServletFileUpload upload = new ServletFileUpload(factory);
			  List items = upload.parseRequest(request); 		 
			  	  
			  for(int i = 0; i < items.size(); i++)
			  {
				  FileItem itemForm = (FileItem)items.get(i);
				  if(itemForm.isFormField())
				  {
					  if(itemForm.getName().equals("descricao"))
					  {
						  descricao = itemForm.getString();
					  }
					  if(itemForm.getName().equals("textCaracteristicas"))
					  {
						  caracteristicas = itemForm.getString();
					  }
					  if(itemForm.getName().equals("categoria"))
					  {
						  categoriaId.setId(Integer.parseInt(itemForm.getString()));
					  }
					  if(itemForm.getName().equals("quantidade"))
					  {
						  quantidade = Integer.parseInt(itemForm.getString());
					  }
					  if(itemForm.getName().equals("preco_custo"))
					  {
						  preco_custo = new BigDecimal(itemForm.getString());
					  }
					  if(itemForm.getName().equals("preco_venda"))
					  {
						  preco_venda = new BigDecimal(itemForm.getString());
					  }
					  if(itemForm.getName().equals("status"))
					  {
						  status = Integer.parseInt(itemForm.getString());
					  }
				  }
			  }
	          //Obtém os valores digitados nos campos do formulário          //
	          /*String descricao = request.getParameter("descricao");
	          String caracteristicas = request.getParameter("textCaracteristicas");
	          categoriaId.setId(Integer.parseInt(request.getParameter("categoria")));
	          int quantidade = Integer.parseInt(request.getParameter("quantidade"));
	          BigDecimal preco_custo = new BigDecimal(request.getParameter("preco_custo"));
	          BigDecimal preco_venda = new BigDecimal(request.getParameter("preco_venda"));
	          int status = Integer.parseInt(request.getParameter("ativo"));    */      
	          
	          
	          //Verifica se não há atributos com null ou vazios
	          if(descricao != null && !descricao.equals("")
	        		  && caracteristicas != null && !caracteristicas.equals("")
	    		)
	          {
	
	        	  Produto produto = new Produto();
	        	  try {
	              
	        		  produto.setDescricao(descricao);
	        		  produto.setCaracteristicas(caracteristicas);
	        		  produto.setCategoria_id(categoriaId);
	        		  produto.setQuantidade_estoque(quantidade);
	        		  produto.setPreco_custo(preco_custo);
	        		  produto.setPreco_venda(preco_venda);
	        		  produto.setStatus((short) status);
	        		  
	        		  
	        		  ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
	        		  
	                  if(produtoDAO.saveProduto(produto))  //Tenta gravar os dados no banco de dados. Se retornar true então o cadastro ocorreu com sucesso
	                  {
	                	  if(FileUpload.isMultipartContent(request))
	                	  {             		               		  
	                		  Iterator<FileItem> iter = items.iterator();	
	                		  while (iter.hasNext())
	                		  {
	                		      FileItem item = iter.next();
	
	                		      if (!item.isFormField())                		       
	                		      {
	                		    	  File file = new File(this.getServletContext().getRealPath("/img/produtos") + 
	                		    			  String.valueOf(produto.getId()));
	                		          item.write(file);
	                		      }
	                		  }                		  
	                		  
	                          
	                	  }
	                	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Cadastro de"
	                          		+ " produto realizado com sucesso.</span>";	
	                  }
	                  else{
	                	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar "
	                	  		+ "o produto!</span>";
	                  }
	                    
	            }
	            catch (Exception ex){
	            	mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar "
	            	  		+ "o produto! " + ex.getMessage() + "</span>";
	            }
	          }
	          
	          else{
	        	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">É necessário preencher todos "
	          	  		+ "os campos do formulário!</span>";
	          }
	          request.setAttribute("mensagem", mensagem);
	      	  request.getRequestDispatcher("cadastro_produto.jsp" + mensagem).forward(request, response);  
          }
          catch(Exception ex)
          {
        	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar "
          	  		+ "o produto! " + ex.getMessage() + "</span>";
        	  request.setAttribute("mensagem", mensagem);
	      	  request.getRequestDispatcher("cadastro_produto.jsp" + mensagem).forward(request, response); 
	      	  ex.printStackTrace();
          }
        } 
        finally
        {
          
        }
    
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
