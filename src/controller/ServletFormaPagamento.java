package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.FormaPagamento;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.FormaPagamentoDAO;

/**
 * Servlet implementation class ServletEditaFormaPagamento
 */
@WebServlet("/ServletFormaPagamento")
public class ServletFormaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFormaPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException, NoSuchAlgorithmException
    {
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "?mensagem="; // Mensagem devolvida ao JSP do formul�rio de formas de pagamento 	            	           	        
        try
        {
        	FormaPagamento formaPagamento = new FormaPagamento();
        	FormaPagamentoDAO formaPagamentoDAO = MySQLLojaUfscarDAOFactory.getFormaPagamentoDAO();
        	
        	//Obt�m os valores digitados nos campos do formul�rio          
            String descricao = request.getParameter("descricao");
            
            // verifica se n�o existem dados vazios 
            if(descricao != null && !descricao.equals("") && request.getParameter("id") != null && !request.getParameter("id").equals(""))
            {            	
            	formaPagamento.setId(Integer.parseInt(request.getParameter("id")));
            	formaPagamento.setDescricao(descricao);
            	try
            	{
            		formaPagamentoDAO.update(formaPagamento);
            		mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Forma de pagamento salva com sucesso</span>"
            				+ "&id=" + formaPagamento.getId();
            	}
            	catch(Exception e)
            	{            		
            		mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao editar!<br/>"
            				+ e.getMessage() + "</span>";
            	}
            }
            else
            {
            	// Se receber somente a descriçãoo considera-se uma nova inserção de Forma de Pagamento
        		if(descricao != null && !descricao.equals(""))
        		{
        			formaPagamento.setDescricao(descricao);
        			formaPagamentoDAO.save(formaPagamento);
        			mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Forma de pagamento salva com sucesso</span>" ;           				
        			
        		}
        		else
        			mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Os campos n�o podem estar vazios!</span>";
            }
            request.setAttribute("mensagem", mensagem);
        	request.getRequestDispatcher("forma_pagamento.jsp" + mensagem).forward(request, response);
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
