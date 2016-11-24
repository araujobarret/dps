<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.ProdutoCarrinho" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.Carrinho" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.text.NumberFormat" %>

<%
	int i = 0;
	String total;
	NumberFormat formato = NumberFormat.getCurrencyInstance();
	Carrinho carrinho = new Carrinho();
	Produto pesquisa;
	ProdutoCarrinho produto;
	ProdutoDAO produtoDAO;
	// Verifica se já existe um carrinho de compra previamente alocado na sessão
	HttpSession sessao = request.getSession();
	if(sessao.getAttribute("carrinho") != null)
	{
		carrinho = (Carrinho)sessao.getAttribute("carrinho");
		if(request.getParameter("idRemove") != null)
		{
			carrinho.removeItem(Integer.parseInt(request.getParameter("idRemove")));
			sessao.setAttribute("carrinho", carrinho);
		}
	}	
	if(request.getParameter("id") != null)
	{
		pesquisa = new Produto();
		pesquisa.setId(Integer.parseInt(request.getParameter("id")));
		produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		pesquisa = produtoDAO.retrieve(pesquisa);
		
		produto = new ProdutoCarrinho();
		produto.setId(pesquisa.getId());
		produto.setPreco_venda(pesquisa.getPreco_venda());
		produto.setDescricao(pesquisa.getDescricao());
		produto.setQuantidade_carrinho(1);
		carrinho.addItem(produto);
		
		sessao.setAttribute("carrinho", carrinho);
	}
	total = formato.format(carrinho.getTotal());	
%>

<form name="form" action="finalizar.jsp" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
<% 
	while(i < carrinho.size())
	{
		produto = new ProdutoCarrinho();
		produto = carrinho.getProduto(i);
%>         				
	<div class="row">
		<div class="col-md-2">			
			<a href="produto.jsp?id=<%=produto.getId()%>"><img class="thumbnail" src="img/produtos/<%= produto.getId() + "_mini.jpg" %>" /></a>
		</div>	
		<div class="col-md-6 m-x-auto">
			<b><%= produto.getDescricao() %></b>			
		</div>
		<div class="col-md-2"><input type="text" disabled name="quantidade" value="<%=produto.getQuantidade_carrinho()%>"/></div>
		<div class="col-md-2"><a href="carrinho.jsp?idRemove=<%= i %>">X</a></div>
	</div>
<% 
		i++;
	}
%>	
	<b>Total: <%= total %></b>
	<br>
	<br>
	<button onClick="this.submit()" class="btn btn-primary">Finalizar compra</button>&nbsp&nbsp
	<a href="index.jsp" class="btn btn-primary">Continuar comprando</a>
</form>