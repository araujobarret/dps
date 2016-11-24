package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CriptografiaMD5;
import model.beans.Funcionario;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.FuncionarioDAO;

/**
 * Servlet implementation class ServletLoginFuncionario
 */
@WebServlet("/ServletLoginFuncionario")
public class ServletLoginFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginFuncionario() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException, NoSuchAlgorithmException
    {
        response.setContentType("text/html;charset=UTF-8");
        Funcionario funcionario;
        FuncionarioDAO funcionarioDAO;
        try
        {
          //Obtém os valores digitados nos campos do formulário
          String senha = CriptografiaMD5.getHash(request.getParameter("senha"));
          String login = request.getParameter("login");
          
          if(senha != null && !senha.equals("") && login != null && !login.equals(""))
          {
        	  // Instancia os dados recebidos no objeto funcionario
        	  funcionario = new Funcionario();          
        	  funcionario.setSenha(senha);
        	  funcionario.setLogin(login);
        	  
        	  funcionarioDAO = MySQLLojaUfscarDAOFactory.getFuncionarioDAO();
        	  
        	  funcionario = funcionarioDAO.retrieve(funcionario);
        	  // Valida se encontrou o login e senha respectivos
        	  if(funcionario != null)
        	  {
        		// Cria Sessão...
                HttpSession sessao = request.getSession(true);//Obtém a sessão do usuário
                sessao.setAttribute("login", funcionario.getLogin());// Adiciona o login do usuário à sessão
                response.sendRedirect("painel.jsp");
        	  }
        	  else
        	  {
        		  response.sendRedirect("admin.jsp?mensagem='Login e/ou senha inválidos.'");
        	  }
          }
          else
          {
        	  response.sendRedirect("admin.jsp?mensagem='É necessário fornecer o login e a senha.'");
          }
        }
        catch(Exception ex)
        {
        	response.sendRedirect("admin.jsp?mensagem='Erro ao efetuar o login!' " + ex.getMessage());
        }
        finally {}
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
