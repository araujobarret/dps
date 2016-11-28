<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.dao.interfaces.PedidoProdutoDAO" %>
<%@ page import="model.dao.interfaces.PedidoDAO" %>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.PedidoProduto" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.beans.Pedido" %>
<%@ page import="model.ResultadoMaisVendidos" %>
<%@ page import="java.util.List" %>

<%
	String data1 = request.getParameter("data1");
	String data2 = request.getParameter("data2");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                            <h3 class="panel-title"><b>Produtos Vendidos por período (relatório)</b></h3>
                        </div>
                        
                        <div class="panel-body">
	                        <div class="table-responsive">
	  							<table class="table table-hover">
	  								<thead>
								      <tr>
								        <th>Id Produto</th>
								        <th>Descrição</th>
								        <th>Categoria</th>
								        <th>Data</th>
								      </tr>
								    </thead>
								    <tbody>
	<%
	PedidoDAO lista = MySQLLojaUfscarDAOFactory.getPedidoDAO();
	List<Pedido> vendidos = lista.retrieveListPeriodo(data1, data2);		// arrumar aqui. - colocar o período
	
	int i = 0;
	
	if(vendidos != null){
	while(i < vendidos.size())
	{
		PedidoProdutoDAO listaPedidosDia = MySQLLojaUfscarDAOFactory.getPedidoProdutoDAO();
		List<PedidoProduto> vendidosDia = listaPedidosDia.retrievePedido(vendidos.get(i));
		
			int j = 0;
			if(vendidosDia != null){
				while(j < vendidosDia.size()){
		%>
	 								    	<tr onclick="window.location.href='detalhe_produto.jsp?id=<%=vendidosDia.get(j).getProduto_id().getId()%>';">
									    		<td><%= vendidosDia.get(j).getProduto_id().getId()%></td>
	        									<td><%= vendidosDia.get(j).getProduto_id().getDescricao()%></td>
										        <td><%= vendidosDia.get(j).getProduto_id().getCategoria_id().getDescricao()%></td>
										        <td><%= vendidos.get(i).getData_pedido()%></td>
									    	</tr>
	  <%
				j++;
				}
			}
  		i++;
		}
	}
  %>
									</tbody>
	  							 </table>
	  						</div>      
                        </div>
                    </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>