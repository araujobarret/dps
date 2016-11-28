<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.beans.Categoria" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="controller.BuscaPreferencia" %>
<div class="row row-eq-height">
	<%
		int i = 0, categoriaPreferencia;
		Categoria categoriaTemp;
		Produto produto;
		
		// Inicializa a sessão
		HttpSession sessao = request.getSession();
		
		// Verifica a existencia de preferência em categoria de exibição
		BuscaPreferencia preferencia = new BuscaPreferencia(request);
		
		// Predefinição de formato numérico
		NumberFormat formato = NumberFormat.getCurrencyInstance();		
		
		// Faz a consulta e retorna com os dados dos produtos
		ProdutoDAO lista = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		List<Produto> produtosRaw, produtos, produtosCategorizados;	
		
		// Inicializa as listas temporárias
		produtos = new ArrayList<Produto>();
		produtosCategorizados = new ArrayList<Produto>();
		
		// Verifica se a consulta é por categoria
		if(request.getParameter("categoria_id") != null)
		{	
			Categoria categoria = new Categoria();
			categoria.setId(Integer.parseInt(request.getParameter("categoria_id")));
			preferencia.votarCategoria(Integer.parseInt(request.getParameter("categoria_id")));
			produtosRaw = lista.retrieveProdutosCategoria(categoria);			
		}
		else
			// Verifica se a consulta foi feita usando a caixa de busca
			if(request.getParameter("busca") != null && !request.getParameter("busca").equals(""))
			{
				produtosRaw = lista.searchProdutosAtivos(request.getParameter("busca"));
			}
			else
				produtosRaw = lista.retrieveList();
		
		if(produtosRaw != null)
		{
			// Verifica se foi feita a consulta por categoria
			System.out.println(preferencia.hasCategoria());
			if(preferencia.hasCategoria()){		
				// Pega a ID da preferencia mais votada
				categoriaPreferencia = preferencia.getVotoCategoria().get(0).getId();
				
				for(Produto p : produtosRaw){
					categoriaTemp = p.getCategoria_id();
					// Se o produto for da categoria em questão alimenta a lista de produtos categorizados com a preferência
					if(categoriaTemp.getId() == categoriaPreferencia)
						produtosCategorizados.add(p);
					else
						produtos.add(p);
				}
				// Limpa o array antigo para receber primeiro a lista categorizada depois a lista normal para a exibição
				produtosRaw.clear();
				for(Produto p : produtosCategorizados){
					produtosRaw.add(p);
					System.out.println(p.getDescricao());
				}
				for(Produto p : produtos)
					produtosRaw.add(p);
				
			}
			while(i < produtosRaw.size())
			{					
				
					produto = produtosRaw.get(i);					
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