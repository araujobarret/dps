<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// login.jsp
	if(sessao.getAttribute("cpf") != null && sessao.getAttribute("carrinho") != null)
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
              <main class="col-md-10 col-md-offset-1">
                <div class="row panel panel-primary">
                	<div class="panel-heading">
                  	<h3 class="panel-title"><b>Dados da compra</b></h3>
                	</div>                            
               		<div class="panel-body">
               	  		<jsp:include page="finalizar_dados.jsp" />
               		</div>
               	</div>
              </main>
            </section>           	
        </div>
    </body>
</html>
<%
	}
	else
		response.sendRedirect("login.jsp?mensagem=cod5");
%>