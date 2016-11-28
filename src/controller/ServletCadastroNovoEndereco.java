package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Cliente;
import model.beans.Endereco;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.EnderecoDAO;

/**
 * Servlet implementation class ServletCadastroNovoEndereco
 */
@WebServlet("/ServletCadastroNovoEndereco")
public class ServletCadastroNovoEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroNovoEndereco() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{

        response.setContentType("text/html;charset=UTF-8");
        
        try
        {
        	String mensagem ="?mensagem=";
        	HttpSession sessao = request.getSession();
            int cpf = (int) sessao.getAttribute("cpf");
        	Cliente cliente = new Cliente();
        	cliente.setCpf(cpf);
            
            // Captura as informa��es do endere�o;
            String descricao = request.getParameter("descricao");
            String logradouro = request.getParameter("logradouro");
      	  	String numero = request.getParameter("numero");
      	  	String complemento = request.getParameter("complemento");
      	  	String bairro = request.getParameter("bairro");
      	  	String cidade = request.getParameter("cidade");
      	  	String uf = request.getParameter("uf");
      	  	String cep = request.getParameter("cep");

         
          //Verifica se existem atributos com null ou vazios
          if( descricao != null && !descricao.equals("")
    	    	  && logradouro != null && !logradouro.equals("")
    	    	  && numero != null && !numero.equals("")
    	    	  && complemento != null && !complemento.equals("")
    	    	  && bairro != null && !bairro.equals("")
    	    	  && cidade != null && !cidade.equals("")
    	    	  && uf != null && !uf.equals("")
    	    	  && cep != null && !cep.equals(""))
          {
        	  
        	  Endereco endereco = new Endereco();

        	  try {
        		  endereco.setCliente_cpf(cliente);
        		  endereco.setDescricao(descricao);
        		  endereco.setLogradouro(logradouro);
        		  endereco.setNumero(numero);
        		  endereco.setComplemento(complemento);
        		  endereco.setBairro(bairro);
        		  endereco.setCidade(cidade);
        		  endereco.setUf(uf.substring(0, 2));
        		  endereco.setCep(cep);
        		  
        		  EnderecoDAO enderecoDAO = MySQLLojaUfscarDAOFactory.getEnderecoDAO();
        		                    
                  if(enderecoDAO.save(endereco))  //Tenta gravar os dados no banco de dados. Se retornar true ent�o o cadastro ocorreu com sucesso
                  {
                	 mensagem += "cod1";                	 
                  }
                  else
                  {
                	 mensagem += "cod3";
                  }
                  response.sendRedirect("enderecos.jsp" + mensagem);
            }
            catch (Exception ex)
        	{
            	mensagem += "Erro: " + ex.getMessage();
            	response.sendRedirect("enderecos.jsp" + mensagem);
            }
          }          
          else
          {
        	mensagem += "cod4";
          	response.sendRedirect("enderecos.jsp" + mensagem);
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


}
