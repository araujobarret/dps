<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
        <style>
        	button{margin-top: 30px;
        	float: right;}
        </style>
        <title>LojaUfscar</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="header.jsp" />
            
            <section class="row">
                <div class="col-md-2 center">
                    <nav class="text-center panel panel-primary">
                        <div class="panel-heading">
                            <span>Menu</span>
                        </div>
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">Usuários</a></li>
                                <li><a href="produtos.html">Produtos</a></li>
                                <li><a href="#">Pedidos</a></li>
                                <li><a href="#">Formas de pagamento</a></li>
                                <li><a href="#">Loja</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div class="col-md-9 center">
                    <div class="row panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><b>Suspensão de Produtos</b></h3>
                        </div>
                        
                        <div class="mensagem">                        				
                        	<% if(request.getParameter("mensagem") != null)
                        	{	
                        	%>
                        	<label><%= request.getParameter("mensagem")%></label>
                        	<%} %>
                       </div>
                        
                   
                   
                   
                        <div class="panel-body">
	                        
	                     <form class="form-group" method="post" action="ServletSuspendeProdutos">   
	                        
	                        <div class="table-responsive">
	  							<table class="table table-hover">
	  								<thead>
								      <tr>
								        <th>Id</th>
								        <th>Descrição</th>
								        <th>Quantidade</th>
								        <th>Preço Custo</th>
								        <th>Preço Venda</th>
								        <th>Suspender</th>
								      </tr>
								    </thead>
								    <tbody>
	<%
		int i = 0;
		Produto produto;
		ProdutoDAO lista = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		List<Produto> produtos = lista.retrieveProdutosAtivos();
		if(produtos != null){
			
		
		
		while(i < produtos.size())
		{
			produto = produtos.get(i);
	%>
 								    	<tr>
								    		<td><%= produto.getId()%></td>
        									<td><%= produto.getDescricao()%></td>
									        <td><%= produto.getQuantidade_estoque()%></td>
									        <td><%= produto.getPreco_custo()%></td>
									        <td><%= produto.getPreco_venda()%></td>
									        <td style="text-align:center; vertical-align:middle"><input  type="checkbox" name="idProdutoStatus" value="<%=produto.getId()%>"></td>
								    	</tr>
  <%
  		i++;
		} %>
									</tbody>
	  							 </table>
	  							 
	  						</div>      
							
    							<button type="submit" class="btn btn-danger">Suspender produtos</button>
    						</form>
							                        
        <%}%>                
                        </div>
                        
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>