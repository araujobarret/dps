<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário permenace na página 
	// para efetuar o login
	if(sessao.getAttribute("login") == null)
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
                <div class="col-md-4 col-md-offset-4 center">
                    <div class="panel panel-default">
                      <div class="panel-heading">
                        <h3 class="panel-title"><b>Login</b></h3>
                      </div>
                      <div class="panel-body">                        
                        <form name="form" action="ServletLoginFuncionario" method="POST" accept-charset="iso-8859-1,utf-8">
                        		<div class="form-group">
                        			<% if(request.getParameter("mensagem") != null) { %>
                        				<%= "<span class=\"alert alert-danger\">" %>
                        				<%= request.getParameter("mensagem") %>
                        				<%= "</span>" %>
                        			<% } %>
                        		</div>
                            <div class="form-group">
                                <label for="login">Login</label>    
                                <input type="text" class="form-control" name="login"/>
                            </div>
                            <div class="form-group">
                                <label for="senha">Senha</label>    
                                <input type="password" class="form-control" name="senha"/>
                            </div>
                            <button type="submit" class="btn btn-default" onClick="this.form.submit()">Entrar</button>
                        </form>
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
		response.sendRedirect("painel.jsp");
%>
            