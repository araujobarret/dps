<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// login.jsp
	if(sessao.getAttribute("cpf") != null)
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
           	<jsp:include page="header.jsp" />
            
            <section class="row">
                <div class="col-md-2 center">
                    <nav class="text-center panel panel-primary">
                        <div class="panel-heading">
                            <span>Menu</span>
                        </div>
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">Meus dados</a></li>
                                <li><a href="enderecos.jsp">Endereços</a></li>
                                <li><a href="#">Meus Pedidos</a></li>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div class="col-md-6 center">
                    <% if(request.getParameter("mensagem") != null) { %>
               				<%= "<span class=\"alert alert-warning\">" %>
               				<%= request.getParameter("mensagem") %>
               				<%= "</span>" %>
               			<% } %>
                </div>
            </section>
        </div>
    </body>
</html>
<%
	}
	else
		response.sendRedirect("login.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>