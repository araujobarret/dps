<%@ page language="java" contentType="text/html; UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.ProdutosDestaqueDAO"%>
<%@ page import="model.dao.interfaces.ProdutoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory"%>
<%@ page import="model.beans.Produto" %>
<%@ page import="model.beans.ProdutosDestaque"%>
<%@ page import="java.util.List"%>
<%
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// admin.jsp
	if (sessao.getAttribute("login") != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
.menu-pagamento {
	font-weight: bold;
}
</style>
<title>LojaUfscar</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header_interno.jsp" />

		<section class="row">
			<jsp:include page="menu_funcionario.jsp" />
			<div class="col-md-9 center">
				<div class="row panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<b>Produtos em destaque</b>
						</h3>
					</div>

					<div class="mensagem">
						<%
							int i;
							String id;
							Produto produto;
							ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
							ProdutosDestaqueDAO destaquesDAO = MySQLLojaUfscarDAOFactory.getProdutosDestaqueDAO();
							List <Produto> produtos;
							ProdutosDestaque destaques;
							// Consulta aos produtos
							produtos = produtoDAO.retrieveList();
							destaques = destaquesDAO.retrieve();
							
							if (request.getParameter("mensagem") != null) {
						%>
						<label><%=request.getParameter("mensagem")%></label>
						<%
							}
						%>
					</div>

					<div class="panel-body">
						<form action="ServletProdutosDestaque" method="POST">
						 <% // Laço de impressão das 4 opções de produtos
						 		for(i = 1; i <= 4; i++) { 
						    	id = "id" + i;
						 %>
								<div class="row">
									<div class="col-md-2">
										<label for="${id}">Produto <%= i %>: </label>
									</div>
									<div class="col-md-10">
										<select name="<%= id %>">
											<% if(destaques.getId_produtoX(i) == 0) { %>										
												<option value="0" selected>Selecione um produto</option>
											<%} 
												 else{ %>
												 <option value="<%= destaques.getId_produtoX(i) %>" selected>produto</option>
											<% } %>
											<% for(Produto p : produtos) { %>
												<option value="<%=p.getId()%>"><%=p.getDescricao()%></option>
											<% } %>
										</select>		
									</div>
								</div>
								
								<br/>
							<% } %>
						  <button type="submit" class="btn btn-default" onClick="this.form.submit()">Salvar</button>
						</form>						
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
<%
	} else
		response.sendRedirect("admin.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>
</html>