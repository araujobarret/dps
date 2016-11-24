<%@page import="model.dao.interfaces.FormaPagamentoDAO"%>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.PedidoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.FormaPagamento" %>
<%@ page import="java.util.List" %>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// admin.jsp
	if(sessao.getAttribute("login") != null)
	{
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>LojaUfscar</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="header_interno.jsp" />   
            
            <section class="row">
               <jsp:include page="menu_funcionario.jsp" />  
                <div class="col-md-9 center">
             	  	<div class="row panel panel-primary">
                  	<div class="panel-heading">
                    	<h3 class="panel-title"><b>Formas de pagamento</b></h3>
                    </div>
                        
                    <div class="mensagem">                        				
                    	<% 	if(request.getParameter("mensagem") != null)
                    			{	
                    	%>
                    	<label><%= request.getParameter("mensagem")%></label>
                      <%} %>
                    </div>
                        
                    <div class="panel-body">
	                    <div class="table-responsive">
						  				  <table class="table table-hover">
						  					  <thead>
													  <tr>
													    <th>Id</th>
													    <th>Descrição</th>
													    <th>Editar</th>
													    <th>Excluir</th>													    
													  </tr>
													</thead>
													<tbody>
														<%
															int i;
															String estado;
															FormaPagamento formaPagamento;	
															FormaPagamentoDAO formaPagamentoDAO = MySQLLojaUfscarDAOFactory.getFormaPagamentoDAO();			
															
															
															List<FormaPagamento>formas = formaPagamentoDAO.retrieveList();
															for(i=0; i < formas.size(); i++)
															{
																formaPagamento = formas.get(i);
																// Checa se recebeu parâmetro de exclusão da forma de pagamento
																if(request.getParameter("id") != null)
																{
																	if(formaPagamento.getId() == Integer.parseInt(request.getParameter("id")))
																	{
																		formaPagamento.setId(Integer.parseInt(request.getParameter("id")));
																		// Exclui a forma de pagamento
																		formaPagamentoDAO.delete(formaPagamento.getId());
																	}
																}																
														%>
 								    				<tr>
		        									<td><%= formaPagamento.getId() %></td>
											        <td><%= formaPagamento.getDescricao()%></td>
											        <td>
											        	<a href="forma_pagamento_data.jsp?id=<%= formaPagamento.getId() %>">Editar</a>
											        </td>
											        <td>
											       		<a href="forma_pagamento.jsp?id=<%= formaPagamento.getId() %>">Excluir</a>
									    				</td>
								    				</tr>
			  										<%
			  										}
			  										%>
													</tbody>
				  							</table>
	  									</div>      
              			</div>
            			</div>
          			</div>                
            </section>
        </div>
    </body>
</html>
<%
	}
	else
		response.sendRedirect("admin.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>
