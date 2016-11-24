<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.PedidoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.Format" %>

<!DOCTYPE html>
<html>
    <head>
	    <meta charset="utf-8">
      <link rel="stylesheet" href="css/main.css" />
      <link rel="stylesheet" href="css/bootstrap.min.css" />
      <script src="js/jquery-2.2.4.min.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <script src="ckeditor/ckeditor.js"></script>
        
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
                            <h3 class="panel-title"><b>Listagem de Pedidos</b></h3>
                        </div>
                        
                        <div class="mensagem">                        				
                        	<% if(request.getParameter("mensagem") != null)
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
								        <th>Data</th>
								        <th>Cliente</th>
								        <th>Forma Pagamento</th>
								        <th>Valor total</th>
								        <th>Estado</th>
								      </tr>
								    </thead>
								    <tbody>
	<%
			int i;
			String estado;
			Pedido pedido;	
			PedidoDAO pedidoDAO = MySQLLojaUfscarDAOFactory.getPedidoDAO();			
			
			
			List<Pedido>pedidos = pedidoDAO.retrieveList();
			for(i=0; i < pedidos.size(); i++)
			{
				pedido = pedidos.get(i);
				// Checa se recebeu parâmetro de liberação de pedido
				if(request.getParameter("id") != null)
				{
					if(pedido.getId() == Integer.parseInt(request.getParameter("id")))
					{
						pedido.setId(Integer.parseInt(request.getParameter("id")));
						pedido.setStatus('1');
						pedidoDAO.liberarEntrega(pedido);
					}
				}
				
				if(pedido.getStatus() == '0')
					estado = "Aguardando pagamento";
				else
					estado = "Entregue";
	%>
 								    	<tr>
        									<td><%= pedido.getData_pedido()%></td>
									        <td><%= pedido.getCliente_cpf().getNome()%></td>
									        <td><%= pedido.getForma_pagamento_id().getDescricao()%></td>
									        <td>R$ <%= pedido.getTotal()%></td>
									        <td>
									        	<% if(estado.equals("Entregue"))
									        		{	
									        	%>
									        		<%= estado %>
									        	<% 
									        		}
									        		else
									        		{		
									        	%>	
									        	<a href="pedidos.jsp?id=<%= pedido.getId() %>">Liberar</a>
									        	<% } %>
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