<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
          $(document).ready(function(){
            console.log( $('.carousel'));
            $('.carousel').carousel();
            $('[data-toggle="tooltip"]').tooltip();
          });
        </script>
        <title>LojaUfscar</title>
    </head>
    <body>
        <div class="container-fluid">
        	<jsp:include page="header.jsp" />           
            <section class="row">
              <jsp:include page="categorias.jsp" />
              
              <main class="col-md-10">
              	<div class="row">     
              		<div class="col-md-4 col-md-offset-4">  
              			<form method="post" action="index.jsp"> 		
		              	  <div class="input-group">
											  <span class="input-group-addon glyphicon glyphicon-search" id="basic-addon1"></span>
											  <input type="text" class="form-control pull-right" placeholder="Buscar" 
												  aria-describedby="basic-addon1" name="busca" 
											  	onkeydown="if (event.keyCode == 13) { this.form.submit(); return false; }"/>
											</div>
										</form>
									</div>
									
                </div>
               	<div class="row">
               		<br/>
               		<% if(request.getParameter("mensagem") != null) { %>
           		     	<%= "<span class=\"alert alert-danger\">" %>
           		     	<%= request.getParameter("mensagem") %>
          	      	<%= "</span>" %>
           	   	  <% } %>
           	    	<div id="carousel_destaque" class="carousel slide" data-ride="carousel">
              
                  <ol class="carousel-indicators">
                    <li data-target="#carousel_destaque" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel_destaque" data-slide-to="1"></li>
                  </ol>
                  
                    
                  <div class="carousel-inner" role="listbox">
                    <div class="item active">
                      <a href="produto.html">
                        <img src="img/produto01.png" alt="Notebook">
                          <div class="carousel-caption">
                            [Descrição] - [Preço]
                          </div>
                        </a>
                      </div>
                     
                    <div class="item">
                      <img src="img/produto01.png" alt="Celular">
                      <div class="carousel-caption">
                        [Descrição] - [Preço]
                      </div>
                    </div>
                  </div>
                  
                    
                  <a class="left carousel-control" href="#carousel_destaque" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="right carousel-control" href="#carousel_destaque" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
                <br/>
                <jsp:include page="produtos_inicial.jsp" />
                </div>
              </main>
            </section>           	
        </div>
    </body>
</html>