<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário permanece
	// na página para seu cadastro
	if(sessao.getAttribute("cpf") == null)
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
            
            <div class="row">
             	<div class="col-md-8 col-md-offset-2 center">
	             	<% if(request.getParameter("mensagem") != null)
	       					{	
	       				%>
	       				<%= request.getParameter("mensagem") %>
	       				<%} %>
       				</div>
       			</div>
            
            <section class="row">
                <div class="col-md-8 col-md-offset-2 center">
                    <div class="panel panel-default">
                      <div class="panel-heading">
                        <h3 class="panel-title"><b>Formulário de cadastro</b></h3>
                      </div>
                      
                      <div class="panel-body">                      	
                        <div class="col-md-6">
                            <form name="form" action="ServletCadastroCliente" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">            				                        				
                              <div class="form-group">
                                  <label for="nome">Nome</label>    
                                  <input type="text" class="form-control" name="nome"/>
                              </div>
                              <div class="form-group">
                                  <label for="cpf">CPF</label>    
                                  <input type="text" class="form-control" name="cpf"/>
                              </div>
                              <div class="form-group">
                                  <label for="email">Email</label>    
                                  <input type="email" class="form-control" name="email"/>
                              </div>
                              <div class="form-group">
                                  <label for="data_nascimento">Data de nascimento</label>    
                                  <input type="date" class="form-control" name="data_nascimento"/>
                              </div>
                              <div class="form-group">
                                  <label for="telefone1">Telefone 1</label>    
                                  <input type="text" class="form-control" name="telefone1"/>
                              </div>
                              <div class="form-group">
                                  <label for="telefone2">Telefone 2</label>    
                                  <input type="text" class="form-control" name="telefone2"/>
                              </div>
                              <div class="form-group">
                                  <label for="telefone2">Telefone 3</label>    
                                  <input type="text" class="form-control" name="telefone3"/>
                              </div>
                              <div class="form-group">
                                  <label for="senha">Senha</label>    
                                  <input type="password" class="form-control" name="senha"/>
                              </div>
                          	</div>
                          	<div class="col-md-6">
                          		<div class="form-group">
                                  <label for="descricao">Tipo de endereço</label>    
                                  <input type="text" class="form-control" name="descricao"/>
                              </div>
                              <div class="form-group">
                                  <label for="logradouro">Logradouro</label>    
                                  <input type="text" class="form-control" name="logradouro"/>
                              </div>
                              <div class="form-group">
                                  <label for="numero">Numero</label>  
                                  <input type="text" class="form-control" name="numero"/>
                              </div>
                              <div class="form-group">
                                  <label for="complemento">Complemento</label>  
                                  <input type="text" class="form-control" name="complemento"/>
                              </div>
                              <div class="form-group">
                                  <label for="bairro">Bairro</label>  
                                  <input type="text" class="form-control" name="bairro"/>
                              </div>
                              <div class="form-group">
                                  <label for="cidade">Cidade</label>  
                                  <input type="text" class="form-control" name="cidade"/>
                              </div>
                              <div class="form-group">
                                  <label for="uf">UF</label>  
                                  <input type="text" class="form-control" name="uf"/>
                              </div>
                              <div class="form-group">
                                  <label for="cep">CEP</label>  
                                  <input type="text" class="form-control" name="cep"/>
                              </div>
                              <button type="submit" class="btn btn-default pull-right" onClick="this.form.submit()">Cadastrar</button>
                         		 </div>
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
		response.sendRedirect("minhaconta.jsp");
%>