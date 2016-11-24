<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
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
            <jsp:include page="header_interno.jsp" />   
            
            <section class="row">
              <jsp:include page="menu_funcionario.jsp" />   
              <div class="col-md-6 center">
               	<div class="row panel panel-primary">
                	<div class="panel-heading">
                  	<h3 class="panel-title"><b>Cadastro de funcionário</b></h3>
                  </div>
                  <div class="panel-body">
                  	<form name="form" action="ServletCadastroFuncionario" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
                    	<div class="form-group">                        				
                   			<% 	if(request.getParameter("mensagem") != null)
                   			   	{	
                   			%>
                   			<%= request.getParameter("mensagem") %>
                   			<%	} %>
                   		</div>
                      <div class="form-group">
                      	<label class="control-label col-md-2" for="login">Login</label>  
                        	<div class="col-md-4">
                          	<input type="text" class="form-control" name="login"/>
                          </div>
                       </div>
                       <div class="form-group">
                         <label class="control-label col-md-2" for="nome">Nome</label>  
                         <div class="col-md-4">
                           <input type="text" class="form-control" name="nome"/>
                         </div>
                       </div>
                       <div class="form-group">
                         <label class="control-label col-md-2" for="senha">Senha</label>  
                         <div class="col-md-4">
                           <input type="password" class="form-control" name="senha"/>
                         </div>
                       </div>
                       <div class="form-group">
                         <label class="control-label col-md-2" for="email">Email</label>  
                         <div class="col-md-4">
                           <input type="email" class="form-control" name="email"/>
                           <br/>
                         		<button type="submit" class="pull-right btn btn-default" onClick="this.form.submit()">Salvar</button>
                         </div>                                                                                                                        
                       </div>                                
                    </form>
                  </div>
                 </div>
                </div>
            </section>
        </div>
    </body>
</html>