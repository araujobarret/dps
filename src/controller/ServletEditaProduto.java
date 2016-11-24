package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Categoria;
import model.beans.Produto;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ProdutoDAO;

/**
 * Servlet implementation class ServletEditaProduto
 */
@WebServlet("/ServletEditaProduto")
public class ServletEditaProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditaProduto() {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NoSuchAlgorithmException{

        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "?mensagem="; // Mensagem devolvida ao JSP de cadastro do funcionário
        
        try
        {
          // Criação de objeto categoria
          Categoria categoriaId = new Categoria();
          
          //Obtém os valores digitados nos campos do formulário          
          String descricao = request.getParameter("descricao");
          String caracteristicas = request.getParameter("textCaracteristicas");
          categoriaId.setId(Integer.parseInt(request.getParameter("categoria")));
          int quantidade = Integer.parseInt(request.getParameter("quantidade"));
          BigDecimal preco_custo = new BigDecimal(request.getParameter("preco_custo"));
          BigDecimal preco_venda = new BigDecimal(request.getParameter("preco_venda"));
          int status = Integer.parseInt(request.getParameter("ativo"));          
          
          //Verifica se não há atributos com null ou vazios
          if(descricao != null && !descricao.equals("")
        		  && caracteristicas != null && !caracteristicas.equals("")
    		)
          {

        	  Produto produto = new Produto();
        	  try {
        		  produto.setId(Integer.parseInt(request.getParameter("id")));
        		  produto.setDescricao(descricao);
        		  produto.setCaracteristicas(caracteristicas);
        		  produto.setCategoria_id(categoriaId);
        		  produto.setQuantidade_estoque(quantidade);
        		  produto.setPreco_custo(preco_custo);
        		  produto.setPreco_venda(preco_venda);
        		  produto.setStatus((short) status);
        		  
        		  
        		  ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
        		  
                  if(produtoDAO.updateProduto(produto))  //Tenta gravar os dados no banco de dados. Se retornar true então o cadastro ocorreu com sucesso
                  {
                	  
                		  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Alteração de"
                          		+ " produto realizada com sucesso.</span>";	
                  }
                  else{
                	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao alterar dados "
                	  		+ "do produto!</span>";
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
      	  request.getRequestDispatcher("detalhe_produto.jsp" + mensagem).forward(request, response);          
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
