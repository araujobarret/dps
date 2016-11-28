<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário permenace na página 
	// para efetuar o login
	if(sessao.getAttribute("cpf") == null)
	{
		String comprar = "0";
		if(request.getParameter("comprar") != null)
			comprar = "1";
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
        
        <style>
        .panel-footer a{
        align: right;
        }
        
        </style>
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
                        <form name="form" action="ServletLoginCliente" method="POST" accept-charset="iso-8859-1,utf-8">
                        	<input type="hidden" name="comprar" value="<%=comprar%>"/>
                        		<div class="form-group">
                        			<% if(request.getParameter("mensagem") != null) {
                        				String mensagemRetorno = "";
                        				
                        				if(request.getParameter("mensagem").equals("cod1"))
                        					mensagemRetorno = "Usuário e/ou senha inválidos";
                        				else if(request.getParameter("mensagem").equals("cod2"))
                        					mensagemRetorno = "Erro ao efetuar o login!";
                        				else if(request.getParameter("mensagem").equals("cod3"))
                        					mensagemRetorno = "É necessário fornecer o cpf e a senha.";
                        				else if(request.getParameter("mensagem").equals("cod5"))
                        					mensagemRetorno = "É necessário fazer o login para finalizar a compra";
                        				else 
                        					mensagemRetorno = "É necessário fazer o login para acessar esta área.";
                        				%>
                        				
                        				<span class="alert alert-danger">
                        				<%= mensagemRetorno%>
                        				</span>
                        			<% } %>
                        		</div>
                        	<br>	
                            <div class="form-group">
                                <input type="text" class="form-control" name="cpf" id="cpf" placeholder="CPF">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="senha" id="senha" placeholder="Senha"/>
                            </div>
                            
                            <button type="submit" class="btn btn-primary" onClick="this.form.submit()">Entrar</button>
                        </form>
                        
                        <div class="pull-right">
									 Novo usuário?
							            	<a href="cadastro_cliente.jsp" class="btn btn-primary">Cadastrar-se</a>
									
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
		response.sendRedirect("index.jsp");
%>
            