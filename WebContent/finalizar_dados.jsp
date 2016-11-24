<%@page import="model.beans.ProdutoCarrinho"%>
<%@page import="java.text.Normalizer.Form"%>
<%@ page import="model.dao.interfaces.EnderecoDAO" %>
<%@ page import="model.dao.interfaces.FormaPagamentoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Cliente" %>
<%@ page import="model.beans.Endereco" %>
<%@ page import="model.beans.FormaPagamento" %>
<%@ page import="model.Carrinho" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.List" %>

<%
	HttpSession sessao = request.getSession();
	int i = 0;
	String total;
	
	EnderecoDAO enderecoDAO;
	List<Endereco> enderecos;
	Endereco endereco;
	
	FormaPagamentoDAO formaPagamentoDAO;
	List<FormaPagamento> formas;
	FormaPagamento forma;
	
	NumberFormat formato = NumberFormat.getCurrencyInstance();
	Carrinho carrinho = new Carrinho();
	Cliente cpf = new Cliente();
	cpf.setCpf((int)sessao.getAttribute("cpf"));
	
	if(sessao.getAttribute("carrinho") != null)
	{
		// Aqui o carrinho já está com o cliente, falta apenas o objeto pedido a ser tratado no servlet
		carrinho = (Carrinho)sessao.getAttribute("carrinho");
	}
	

	carrinho.setCliente(cpf);
	total = formato.format(carrinho.getTotal());
	sessao.setAttribute("carrinho", carrinho);
	
	enderecoDAO = MySQLLojaUfscarDAOFactory.getEnderecoDAO();
	enderecos = enderecoDAO.retrieveList(cpf);
	
	formaPagamentoDAO = MySQLLojaUfscarDAOFactory.getFormaPagamentoDAO();
	formas = formaPagamentoDAO.retrieveList();	
	
%>
<form action="ServletComprar" method="POST" class="form-horizontal">
	<div class="row">
		<div class="form-group">
			<label class="control-label col-md-2" for="endereco"> Endereco: </label>
			<div class="col-md-4">
				<select name="endereco" class="form-control">
				<% 
					for(i=0; i < enderecos.size(); i++) 
					{
						endereco = enderecos.get(i);					
				%>
					<option value="<%= endereco.getId() %>"><%= endereco.getDescricao() %></option>
				<% 
					}
				%>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-2" for="forma_pagamento"> Forma de pagamento: </label>
			<div class="col-md-4">
				<select name="forma_pagamento" class="form-control">
				<% 
					for(i=0; i < formas.size(); i++) 
					{
						forma = formas.get(i);					
				%>
					<option value="<%= forma.getId() %>"><%= forma.getDescricao() %></option>
				<% 
					}
				%>
		
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<span class="col-md-2"></span>
			<span class="col-md-2"><b> Total: <%= total %></b></span>
		</div>
		
		<div class="form-group">
			<span class="col-md-2"></span>
			<button onClick="this.submit()" class="btn btn-primary col-md-2">Finalizar compra</button>
		</div>
		
	</div>	
</form>