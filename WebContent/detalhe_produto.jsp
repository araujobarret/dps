<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="java.util.List" %>

<%
	int idProduto = Integer.parseInt(request.getParameter("id"));

%>

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
        	img{float: right;}
        	button{margin-right: 100px;}
        	.mensagem{margin-top: 20px;}
        </style>
        
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
                            <h3 class="panel-title"><b>Detalhes do Produto: id #<%=idProduto%></b></h3>
                        </div>
						<div class="mensagem">                        				
                        	<% if(request.getParameter("mensagem") != null)
                        	{	
                        	%>
                        	<label><%= request.getParameter("mensagem")%></label>
                        	<%} %>
                       </div>
						
						
                        <div class="panel-body">
                        	<%
                        		ProdutoDAO lista = MySQLLojaUfscarDAOFactory.getProdutoDAO();
                    				Produto produto = lista.retrieveProdutoID(idProduto);
                        	%>
							
                              	
							<img src="img/produtos/<%= produto.getId()%>_mini.jpg"/>
    						
    						<div class="form-group">
    							<label for="descricao">Descrição</label>
    							<p><%= produto.getDescricao()%></p>
    						</div>
    						
    						<div class="form-group">
    							<label for="caracteristicas">Características</label>
    							<p><%=produto.getCaracteristicas()%></p>
    						</div>
    						                      
                        	<div class="form-group">
    							<label for="quantidade">Quantidade</label>
    							<p><%=produto.getQuantidade_estoque()%></p>
    						</div>
                        
                        	<div class="form-group">
    							<label for="preco_custo">Preço Custo</label>
    							<p><%=produto.getPreco_custo()%></p>
    						</div>
                        
                        	<div class="form-group">
    							<label for="preco_venda">Preço Venda</label>
    							<p><%=produto.getPreco_venda()%></p>
    						</div>
                        	
                        	<div class="form-group">
    							<label for="status">Status</label>
    							<%
    								if(produto.getStatus() == 1){
    									;
    							%>
    							<p>Produto ativo</p>
    							<%
    								}else{
    							%>
    							<p>Produto inativo</p>
    							<%}%>
    						</div>
    						
    						<form class="form-group" method="post" action="editar_produto.jsp?id=<%=idProduto%>">
    							<button type="submit" class="btn btn-info col-md-2">Editar produto</button>
    						</form>
    						
    						<form class="form-group" method="post" action="ServletExcluiProduto?id=<%=idProduto%>">
    							<button type="delete" class="btn btn-danger col-md-2">Excluir produto</button>
    						</form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>