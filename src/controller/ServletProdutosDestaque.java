package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.ProdutosDestaque;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ProdutosDestaqueDAO;
/**
 * Servlet implementation class ServletProdutosDestaque
 */
@WebServlet("/ServletProdutosDestaque")
public class ServletProdutosDestaque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProdutosDestaque() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try {
        	ProdutosDestaque destaque;
        	ProdutosDestaqueDAO destaqueDAO;
        	String mensagem = "?mensagem=";
        	destaque = new ProdutosDestaque();
        	
        	destaque.setId(1);
        	if(request.getParameter("id1")!= null)
        		destaque.setId_produto1(Integer.parseInt(request.getParameter("id1")));
        	if(request.getParameter("id2")!= null)
        		destaque.setId_produto2(Integer.parseInt(request.getParameter("id2")));
        	if(request.getParameter("id3")!= null)
        		destaque.setId_produto3(Integer.parseInt(request.getParameter("id3")));
        	if(request.getParameter("id4")!= null)
        		destaque.setId_produto4(Integer.parseInt(request.getParameter("id4")));
        	
        	destaqueDAO = MySQLLojaUfscarDAOFactory.getProdutosDestaqueDAO();
        	
        	if(destaqueDAO.save(destaque)){
        		mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Alteração de"
                  		+ " destaques realizada com sucesso.</span>";
        	}
        	else {
        		mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao atualizar os destaques</span>";
        	}
        	request.setAttribute("mensagem", mensagem);
        	request.getRequestDispatcher("produtos_destaque.jsp" + mensagem).forward(request, response);    
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
