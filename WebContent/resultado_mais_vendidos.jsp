<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.dao.interfaces.PedidoProdutoDAO" %>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.PedidoProduto" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.ResultadoMaisVendidos" %>
<%@ page import="java.util.List" %>

<%
	String ordem = request.getParameter("ordem");
	int quantia = Integer.parseInt(request.getParameter("quantidade"));


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
                            <h3 class="panel-title"><b>Produtos mais Vendidos (relatório)</b></h3>
                        </div>
                        
                        <div class="panel-body">
	                        <div class="table-responsive">
	  							<table class="table table-hover">
	  								<thead>
								      <tr>
								        <th>Id</th>
								        <th>Descrição</th>
								        <th>Quantidade Vendida</th>
								        <th>Categoria</th>
								      </tr>
								    </thead>
								    <tbody>
	<%

	
	PedidoProdutoDAO lista = MySQLLojaUfscarDAOFactory.getPedidoProdutoDAO();
	List<ResultadoMaisVendidos> vendidos = lista.maisVendidos(ordem, quantia);
	
	int i = 0;
	
	while(i < vendidos.size())
	{
		Produto pesquisa = new Produto();
		pesquisa.setId(vendidos.get(i).getProduto_id());
		
		ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		Produto produto = produtoDAO.retrieve(pesquisa);
	%>
 								    	<tr onclick="window.location.href='detalhe_produto.jsp?id=<%=produto.getId()%>';">
								    		<td><%= produto.getId()%></td>
        									<td><%= produto.getDescricao()%></td>
									        <td><%= vendidos.get(i).getSoma_produtos()%></td>
									        <td><%= produto.getCategoria_id().getDescricao()%></td>
								    	</tr>
  <%
  		i++;
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