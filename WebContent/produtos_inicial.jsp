<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.beans.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<div class="row row-eq-height">
	<%
		int i = 0;
		Produto produto;
		NumberFormat formato = NumberFormat.getCurrencyInstance();
		ProdutoDAO lista = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		List<Produto> produtos;
		// Verifica se foi feita a consulta por categoria
		if(request.getParameter("categoria_id") != null)
		{
			Categoria categoria = new Categoria();
			categoria.setId(Integer.parseInt(request.getParameter("categoria_id")));
			produtos = lista.retrieveProdutosCategoria(categoria);
		}
		else
			if(request.getParameter("busca") != null && !request.getParameter("busca").equals(""))
			{
				produtos = lista.searchProdutosAtivos(request.getParameter("busca"));
			}
			else
				produtos = lista.retrieveList();
		
		if(produtos != null)
		{
			while(i < produtos.size())
			{
				produto = produtos.get(i);
	%>
  <div class="col-md-3">
    <a href="<%="produto.jsp?id="+produto.getId()%>" class="thumbnail">
    	<img src="img/produtos/<%= produto.getId()%>_mini.jpg"/><%= produto.getDescricao() %>
    	<br><%= formato.format(produto.getPreco_venda()) %>
    </a>
  </div>  
  <%
  			i++;
			}
		}
  %>
</div>