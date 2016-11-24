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
        <script>
        	$(document).ready(function(){
						$('.formulario').hide();
        	});
        	function exibeForm(){    		        	
						$('.listagemEnderecos').hide();
						$('.formulario').show();
        	}
        </script>
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
               <li><a href="#">Endereços</a></li>
               <li><a href="#">Meus Pedidos</a></li>
               <li><a href="#">Dúvidas</a></li>
	           </ul>
	         </div>
         </nav>
       </div>

       <div class="col-md-6 center listagemEnderecos">
         <div class="row">
	         <select onChange="getEndereco()">
	             
	         </select>
	         <br/>
	         <br/>
	         <button class="btn btn-primary" onClick="exibeForm()">Cadastrar novo endereço</button>
         </div>
       </div>
                
       <div class="row formulario">
         <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><b>Cadastro de novo endereço</b></h3>
          </div>
	       	<div class="panel-body">                      	
	         <form name="form" action="ServletCadastroNovoEndereco" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">  
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
		response.sendRedirect("login.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>