package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Cliente;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ClienteDAO;
import model.CriptografiaMD5;
/**
 * Servlet implementation class ServletLoginCliente
 */
@WebServlet("/ServletLoginCliente")
public class ServletLoginCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginCliente() {
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
    		throws ServletException, IOException, NoSuchAlgorithmException
    {
        response.setContentType ("text/html;charset=UTF-8");

        try
        {
          //Obtém os valores digitados nos campos do formulário
          String senha = CriptografiaMD5.getHash(request.getParameter("senha"));
          int cpf; 
          
          //Verifica se não há atributos com null ou vazios
          if(senha != null && !senha.equals("")
    	    	  && request.getParameter("cpf").length() > 0
    	    	  && request.getParameter("cpf") != null)
          {
        	  cpf = Integer.parseInt(request.getParameter("cpf"));
        	  Cliente cliente = new Cliente();

        	  try {
              
        		  cliente.setSenha(senha);
        		  cliente.setCpf(cpf);
        		  
        		  ClienteDAO clienteDAO = MySQLLojaUfscarDAOFactory.getClienteDAO();
        		  
        		  cliente = clienteDAO.retrieve(cliente);
        		                    
                  if(cliente != null)  //Tenta gravar os dados no banco de dados. Se retornar true então o cadastro ocorreu com sucesso
                  {
                      // Cria Sessão...
                      HttpSession sessao = request.getSession(true);    //Obtém a sessão do usuário
                      sessao.setAttribute("cpf", cliente.getCpf()); 	//Adiciona o login do usuario em um objeto de sessão
                      if(request.getParameter("comprar").equals("1"))
                    	  response.sendRedirect("ServletComprar");
                      else
                    	  response.sendRedirect("index.jsp");
                  }
                  else{
                	  response.sendRedirect("login.jsp?mensagem=cod1");
                  }
                    
            }
            catch (Exception ex){
            	response.sendRedirect("login.jsp?mensagem=cod2" + ex.getMessage());
            }
          }
          
          else{
        	  response.sendRedirect("login.jsp?mensagem=cod3");
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
