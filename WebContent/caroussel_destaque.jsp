<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dao.interfaces.ProdutosDestaqueDAO" %>
<%@ page import="model.beans.ProdutosDestaque" %>
<%@ page import="java.text.NumberFormat" %>

<% 
	String ativo;
	int i;
	//Faz a consulta e retorna com os dados dos produtos
	ProdutosDestaqueDAO produtos = MySQLLojaUfscarDAOFactory.getProdutosDestaqueDAO();
	List<Produto>lista = produtos.retrieveList();
	
	// Predefinição de formato numérico
	NumberFormat formato = NumberFormat.getCurrencyInstance();	
	if(lista != null)
	{
%>
<div id="carousel_destaque" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<% for(i = 0; i<lista.size(); i++) { 
					if(i == 0)
						ativo = "active";
					else
						ativo = "";
		%>
	  <li data-target="#carousel_destaque" data-slide-to="<%=i%>" class="<%=ativo%>"></li>
	  <% } %>
	</ol>
	 
	<div class="carousel-inner" role="listbox">
	  <%
	  	i = 0;
	    for(Produto produto : lista) {
				if(i == 0)
					ativo = "item active";
				else{
					ativo = "item";
				}
				i++;
		%>
	  <div class="<%=ativo%>">
	  	
	    <a href="<%="produto.jsp?id="+produto.getId()%>">
	    	<img src="img/produtos/<%= produto.getId()%>_mini.jpg">
	      	<div class="carousel-caption">
	        	<%= produto.getDescricao() %> - <%= formato.format(produto.getPreco_venda()) %>
	      	</div>
	    </a>
	    
	   </div>
	  <% } %>
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
<% } else { %>
	<ol class="carousel-indicators">
	  <li data-target="#carousel_destaque" data-slide-to="0" class="active"></li>
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
<% } %>