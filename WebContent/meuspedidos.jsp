<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.beans.Cliente"%>
<%@ page import="model.dao.interfaces.ClienteDAO"%>
<%@ page import="model.beans.Pedido"%>
<%@ page import="model.dao.interfaces.PedidoDAO"%>
<%@ page import="model.beans.PedidoProduto"%>
<%@ page import="model.dao.interfaces.PedidoProdutoDAO"%>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory"%>
<%@ page import="java.util.List"%>
<%
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// login.jsp

	if (sessao.getAttribute("cpf") != null) {
		Cliente cliente;
		ClienteDAO clienteDAO = MySQLLojaUfscarDAOFactory.getClienteDAO();
		cliente = clienteDAO.retrieveCPF(sessao.getAttribute("cpf").toString());
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
dl {
	margin: 20px 10px;
}

dl dt {
	color: #000;
	float: left;
	font-weight: bold;
	margin-right: 20px;
	padding: 5px;
	width: 200px;
}

dl dd {
	margin: 2px 0;
	padding: 5px 0;
}

.menu-pedidos {
	font-weight: bold;
}

.tabela-detalhes-head {
	background: #605ce0;
	color: #fff;
}

.tabela-detalhes-body {
	background: #d3d2e0;
}
</style>

<title>LojaUfscar</title>
</head>
<body>

	<div class="container-fluid">
		<jsp:include page="header.jsp" />

		<section class="row">

			<jsp:include page="menu_cliente.jsp" />
			<div class="col-md-9 center">
				<div class="row panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<b>Meus pedidos</b>
						</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>

										<th>Nº pedido</th>
										<th>Data</th>
										<th>Valor da compra</th>
										<th>Status</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<%
										int i;
											String estado;
											Pedido pedido;
											PedidoDAO pedidoDAO = MySQLLojaUfscarDAOFactory.getPedidoDAO();

											List<Pedido> pedidos = pedidoDAO.retrievePedidosCliente(cliente);

											if (pedidos != null) {
												for (i = 0; i < pedidos.size(); i++) {
													pedido = pedidos.get(i);
													// Checa se recebeu parâmetro de liberação de pedido
													if (request.getParameter("id") != null) {
														if (pedido.getId() == Integer.parseInt(request.getParameter("id"))) {
															pedido.setId(Integer.parseInt(request.getParameter("id")));
															pedido.setStatus('2');
															pedidoDAO.cancelarPedido(pedido);
														}
													}

													if (pedido.getStatus() == '0')
														estado = "Pendente";
													else if (pedido.getStatus() == '1')
														estado = "Entregue";
													else
														estado = "Cancelado";
									%>
									<tr data-toggle="collapse" data-target="#demo<%=i%>"
										class="accordion-toggle">
										<td><%=pedido.getId()%></td>
										<td><%=pedido.getData_pedido()%></td>
										<td><%=pedido.getTotal()%></td>
										<td><%=estado%></td>

										<td>
											<%
												if (pedido.getStatus() == '0') {
											%> <a href="meuspedidos.jsp?id=<%=pedido.getId()%>">Cancelar
												Pedido</a> <%
 	}
 %>
										</td>
										<td><button class="btn btn-primary btn-xs pull-right">VER
												DETALHE</button></td>
									</tr>
									<tr>
										<td colspan="6" class="hiddenRow">
											<div id="demo<%=i%>" class="accordian-body collapse ">
												<div class="table-responsive">
													<table class="table">
														<thead class="tabela-detalhes-head">
															<tr>
																<th>Produto</th>
																<th>Quantidade</th>
																<th>Valor unitário</th>
																<th>Valor total</th>
															</tr>
														</thead>
														<tbody class="tabela-detalhes-body">
															<%
																int j;
																			PedidoProduto pedidoProduto;

																			PedidoProdutoDAO pedidoProdutoDAO = MySQLLojaUfscarDAOFactory.getPedidoProdutoDAO();

																			List<PedidoProduto> pedidosProduto = pedidoProdutoDAO.retrievePedido(pedido);

																			for (j = 0; j < pedidosProduto.size(); j++) {
																				pedidoProduto = pedidosProduto.get(j);
															%>

															<tr>
																<td><%=pedidoProduto.getProduto_id().getDescricao()%></td>
																<td><%=pedidoProduto.getQuantidade()%></td>
																<td><%=pedidoProduto.getValor_unitario()%>
																<td><%=pedidoProduto.getValor_total()%></td>
															</tr>
															<%
																}
															%>
														</tbody>
													</table>
												</div>
											</div>
										</td>
									</tr>
									<%
										}
											}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>
<%
	} else
		response.sendRedirect("login.jsp?mensagem=cod4");
%>