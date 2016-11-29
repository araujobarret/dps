<%@page import="model.beans.ProdutoCarrinho"%>
<%@page import="java.text.Normalizer.Form"%>
<%@ page import="model.dao.interfaces.EnderecoDAO"%>
<%@ page import="model.dao.interfaces.FormaPagamentoDAO"%>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory"%>
<%@ page import="model.beans.Cliente"%>
<%@ page import="model.beans.Endereco"%>
<%@ page import="model.beans.FormaPagamento"%>
<%@ page import="model.Carrinho"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>

<%
	HttpSession sessao = request.getSession();
	int i = 0;
	String total;

	ProdutoCarrinho produto;
	EnderecoDAO enderecoDAO;
	List<Endereco> enderecos;
	Endereco endereco;

	FormaPagamentoDAO formaPagamentoDAO;
	List<FormaPagamento> formas;
	FormaPagamento forma;

	NumberFormat formato = NumberFormat.getCurrencyInstance();
	Carrinho carrinho = new Carrinho();
	Cliente cpf = new Cliente();
	cpf.setCpf((int) sessao.getAttribute("cpf"));

	if (sessao.getAttribute("carrinho") != null) {
		// Aqui o carrinho já está com o cliente, falta apenas o objeto pedido a ser tratado no servlet
		carrinho = (Carrinho) sessao.getAttribute("carrinho");
	}

	carrinho.setCliente(cpf);

	sessao.setAttribute("carrinho", carrinho);

	enderecoDAO = MySQLLojaUfscarDAOFactory.getEnderecoDAO();
	enderecos = enderecoDAO.retrieveList(cpf);

	formaPagamentoDAO = MySQLLojaUfscarDAOFactory.getFormaPagamentoDAO();
	formas = formaPagamentoDAO.retrieveList();
%>
<form action="ServletComprar" method="POST" class="form-horizontal">
	<div class="row">

		<div class="table-responsive ">
			<table class="table">
				<thead>
					<tr>
						<th>Descrição do produto</th>
						<th>Quantidade</th>
						<th>Valor unitário</th>
						<th>Valor total</th>
					</tr>
				</thead>
				<tbody>

					<%
						for (i = 0; i < carrinho.size(); i++) {
							produto = new ProdutoCarrinho();
							produto = carrinho.getProduto(i);

							// Busca a quantidade digitada pelo usuário e atualiza o carrinho
							produto.setQuantidade_carrinho(Integer.parseInt(request.getParameter("quantidade_row" + i)));
							carrinho.alteraItem(i, produto);

							if (produto.getQuantidade_carrinho() > 0) {
					%>
					<tr>
						<td><%=produto.getDescricao()%>t</td>
						<td><%=produto.getQuantidade_carrinho()%></td>
						<td><%=produto.getPreco_venda()%></td>
						<td><%=produto.getQuantidade_carrinho() * Double.parseDouble(produto.getPreco_venda().toString())%></td>
					</tr>
					<%
						}
						}
						total = formato.format(carrinho.getTotal());
					%>
				</tbody>



			</table>
		</div>

		<br> <br> <br>
		<div class="form-group">
			<label class="control-label col-md-2" for="endereco">
				Endereco: </label>
			<div class="col-md-4">
				<select name="endereco" class="form-control">
					<%
						for (i = 0; i < enderecos.size(); i++) {
							endereco = enderecos.get(i);
					%>
					<option value="<%=endereco.getId()%>"><%=endereco.getDescricao()%>
						<%=endereco.getLogradouro()%>,
						<%=endereco.getNumero()%></option>
					<%
						}
					%>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-2" for="forma_pagamento">
				Forma de pagamento: </label>
			<div class="col-md-4">
				<select name="forma_pagamento" class="form-control">
					<%
						for (i = 0; i < formas.size(); i++) {
							forma = formas.get(i);
					%>
					<option value="<%=forma.getId()%>"><%=forma.getDescricao()%></option>
					<%
						}
					%>

				</select>
			</div>
		</div>

		<div class="form-group">
			<span class="col-md-2"></span> <span class="col-md-2"><b>
					Total: <%=total%></b></span>
		</div>



		<div class="form-group">
			<span class="col-md-1"></span>
			<button onclick="voltar()" class="btn btn-info col-md-1">
				<span class="glyphicon glyphicon-chevron-left"></span>Voltar
			</button>
			<span class="col-md-2"></span>
			<button onClick="this.submit()" class="btn btn-primary col-md-2">Finalizar compra</button>
		</div>
	</div>
</form>
<script>
	function voltar() {
		window.history.back();
	}
</script>