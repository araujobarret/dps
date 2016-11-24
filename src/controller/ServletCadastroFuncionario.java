package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Funcionario;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.FuncionarioDAO;
import model.CriptografiaMD5;
/**
 * Servlet implementation class ServletCadastroFuncionario
 */
@WebServlet("/ServletCadastroFuncionario")
public class ServletCadastroFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroFuncionario() {
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
    	String mensagem = "?mensagem="; // Mensagem devolvida ao JSP de cadastro do funcion�rio
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); //Objeto utilizado para enviar algum retorno ao cliente
        try
        {
          //Obt�m os valores digitados nos campos do formul�rio
          String login = request.getParameter("login");
          String nome = request.getParameter("nome");
          String senha = CriptografiaMD5.getHash(request.getParameter("senha"));
          String email = request.getParameter("email");
          
          
          //Verifica se não há atributos com null ou vazios
          if(login != null && !login.equals("") 
        		  && nome != null && !nome.equals("")
        		  && senha != null && !senha.equals("")
        		  && email != null && !email.equals(""))
          {
        	  
        	  Funcionario funcionario = new Funcionario();

        	  try {
              funcionario.setEmail(email);
              funcionario.setLogin(login);
              funcionario.setNome(nome);
              funcionario.setSenha(senha);
              
              
              FuncionarioDAO funcionarioDAO = MySQLLojaUfscarDAOFactory.getFuncionarioDAO();
                  if(funcionarioDAO.save(funcionario))  //Tenta gravar os dados no banco de dados. Se retornar true ent�o o cadastro ocorreu com sucesso
                  {
                	mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Cadastro de"
                		+ " funcionário realizado com sucesso.</span>";	
                  }
                 else{
                	 mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao "
                	 		+ "cadastrar o funcionário!</span>";                	
                 }
                    
            }
            catch (Exception ex){
            	mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar o funcionário!" + 
            			ex.getMessage() + "</span>";
            	request.getRequestDispatcher("cadastro_funcionario.jsp" + mensagem).forward(request, response);
            }
          }
          
          else{
        	  mensagem = "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">É necessário preencher todos "
        	  		+ "os campos do formulário!</span>";
          }

          request.setAttribute("mensagem", mensagem);
      	  request.getRequestDispatcher("cadastro_funcionario.jsp" + mensagem).forward(request, response);
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
