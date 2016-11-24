package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ProdutoDAO;

/**
 * Servlet implementation class ServletExcluiProduto
 */
@WebServlet("/ServletExcluiProduto")
public class ServletExcluiProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExcluiProduto() {
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
        String mensagem = "?mensagem="; // Mensagem devolvida ao JSP
        
        try
        {
          
        	int  idProduto = Integer.parseInt(request.getParameter("id"));
          
        	  try {
        		  
        		  ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
        		  
        		  if(produtoDAO.deleteProduto(idProduto)){
        			  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Produto excluído"
                      		+ " com sucesso.</span>";
        		  }
        		  else{
        			  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao excluir "
                      		+ " produto.</span>";
        		  }
            }
            catch (Exception ex){
            	mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao suspender produtos "
            	  		+ "o produto! " + ex.getMessage() + "</span>";
            }
          
          request.setAttribute("mensagem", mensagem);
      	  request.getRequestDispatcher("lista_produtos.jsp" + mensagem).forward(request, response);          
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
