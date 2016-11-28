<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.beans.Cliente" %>
<%@ page import="model.dao.interfaces.ClienteDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// login.jsp
	
	if(sessao.getAttribute("cpf") != null)
	{
		Cliente cliente;
		ClienteDAO clienteDAO = MySQLLojaUfscarDAOFactory.getClienteDAO();
		cliente = clienteDAO.retrieveCPF(sessao.getAttribute("cpf").toString());
		
		
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
		<style>
		
		dl{
		margin: 20px 10px;}
		
		dl dt{
		
    	color:#000;
    	float:left; 
    	font-weight:bold; 
    	margin-right:20px; 
    	padding:5px;  
    	width:200px; 
		}
		
		dl dd{
			margin:2px 0; 
    		padding:5px 0;
		}
		
		.menu-minhaconta{
        font-weight: bold;
        }
		
		</style>        
        
        <title>LojaUfscar</title>
    </head>
    <body>
        
        <div class="container-fluid">
           	<jsp:include page="header.jsp" />
            
            <section class="row">
                
				 <jsp:include page="menu_cliente.jsp" />
				 <div class="col-md-9 center">
                    <div class="row panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><b>Meus dados</b></h3>
                        </div>
                        
						<div class="container">
							<dl>
	    						<dt>Nome</dt>
	    						<dd><%= cliente.getNome() %></dd>
	
	    						<dt>CPF</dt>
	    						<dd><%= cliente.getCpf() %></dd>
	
							    <dt>E-mail</dt>
							    <dd><%= cliente.getEmail() %></dd>
							
							    <dt>Data de Nascimento</dt>
							    <dd><%= cliente.getData_nascimento() %></dd>
							    
							    <dt>Telefone 1</dt>
							    <dd><%= cliente.getTelefone1() %></dd>
							    
							    <dt>Telefone 2</dt>
							    <dd><%= cliente.getTelefone2() %></dd>
							    
							    <dt>Telefone 3</dt>
							    <dd><%= cliente.getTelefone2() %></dd>
						 	</dl>
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
		response.sendRedirect("login.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>