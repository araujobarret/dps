<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="java.text.NumberFormat" %>

<% 	if(request.getParameter("id") == null) 
	{ 
		response.sendRedirect("index.jsp");
	}
	else
	{
		Produto pesquisa = new Produto();
		pesquisa.setId(Integer.parseInt(request.getParameter("id")));
		ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		Produto produto = produtoDAO.retrieve(pesquisa);
		NumberFormat formato = NumberFormat.getCurrencyInstance();
		String preco_venda = formato.format(produto.getPreco_venda());
		String img = produto.getId() + ".jpg";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
        	function submeter()
        	{
            	document.
          }
        </script>
        <title>LojaUfscar</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="header.jsp" />
            <section class="row">
                <jsp:include page="categorias.jsp" />
                
                <main class="col-md-10">
                	<form method="POST" action="carrinho.jsp" accept-charset="iso-8859-1,utf-8">
                		<input type="hidden" value="<%= produto.getId() %>" name="id"/>
                    <div class="row row-eq-height">
                        <div class="col-md-4 produto_grande">
                            <img src="img/produtos/<%=img%>"/>
                        </div>
                        <div class="col-md-4 produto_geral">
                            <span class="produto_nome"><%= produto.getDescricao() %></span>
                            <hr/>
                            <div class="row">
                                <div class="col-md-6">
                                    <span class="produto_preco"><b>R$ <%= preco_venda %></b></span>
                                </div>
                                <div class="col-md-6 comprar_btn">
                                    <button class="btn btn-primary btn-lg" onClick="submeter()">Comprar</button>
                                    <button class="btn btn-primary btn-lg">Comprar<br>com um clique</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-8 produto_descricao">
                            <p>
                                <b>Características:</b>
                                <br/>                                
                                <%= produto.getCaracteristicas() %>
                            </p>
                        </div>
                    </div>
                  </form>
                </main>
            </section>
        </div>
    </body>
</html>
<% } %>