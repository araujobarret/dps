<header class="row page-header">
	<header class="col-md-1">
		<a href="index.jsp"><img src="img/logo.png" class="img_logo" /></a>
	</header>
	<span class="col-md-4"><h1>Loja Ufscar</h1></span>
	<div class="barra_topo">
		<a href="carrinho.jsp">
			<button type="button" class="btn btn-primary" aria-label="Left Align"
				data-toggle="tooltip" data-placement="bottom"
				title="Carrinho de compras">
				<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
			</button>
		</a>

		<%
			HttpSession sessao = request.getSession();
			if (sessao.getAttribute("cpf") != null) {
		%>
		<!-- PARA CLIENTE LOGADO -->

		<div class="dropdown pull-left">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">
				Olá, Matheus Conceição <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="minhaconta.jsp">Meus dados</a></li>
				<li><a href="enderecos.jsp">Meus endereços</a></li>
				<li><a href="meuspedidos.jsp">Meus pedidos</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="ServletLogoutCliente"><strong>Sair</strong></a></li>
			</ul>
		</div>

		<style>
.barra_topo {
	margin: 10px;
}

.dropdown {
	margin-right: 10px;
}
</style>

		<!-- FIM PARA CLIENTE LOGADO -->

		<%
			} else {
		%>

		<!-- HEADER PARA QUANDO O CLIENTE NÃO ESTIVER LOGADO -->
		<a href="admin.jsp">
			<button type="button" class="btn btn-primary" aria-label="Left Align"
				data-toggle="tooltip" data-placement="bottom" title="Administração">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button>
		</a>

		<!-- INSERIR AQUI -->
		<button class="btn btn-primary btn-md" data-toggle="modal"
			data-target="#modalLogin" title="Login do usuário">Entrar</button>

		<!-- Modal -->
		<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Fechar</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Login</h4>
					</div>

					<!-- Modal Body -->
					<div class="modal-body">

						<%
							if (request.getParameter("mensagem") != null) {
									String mensagemRetorno = "";

									if (request.getParameter("mensagem").equals("cod1"))
										mensagemRetorno = "Usuário e/ou senha inválidos";
									else if (request.getParameter("mensagem").equals("cod2"))
										mensagemRetorno = "Erro ao efetuar o login!";
									else if (request.getParameter("mensagem").equals("cod3"))
										mensagemRetorno = "É necessário fornecer o cpf e a senha.";
						%>
						<div class="alert alert-danger">
							<%=mensagemRetorno%>
						</div>
						<%
							}
						%>
						<form name="form" action="ServletLoginCliente" method="POST"
							accept-charset="iso-8859-1,utf-8">
							<p>
								<input type="text" class="span2" name="cpf" id="cpf"
									placeholder="CPF">
							</p>
							<p>
								<input type="password" class="span2" name="senha"
									placeholder="Senha">
							</p>
							<p>
								<button type="submit" class="btn btn-primary">Entrar</button>

							</p>
						</form>
					</div>
					<div class="modal-footer">
						Novo usuário? <a href="cadastro_cliente.jsp"
							class="btn btn-primary">Cadastrar-se</a>
					</div>

				</div>
			</div>
		</div>
		<!-- FIM DO HEADER PARA CLIENTE NÃO LOGADO -->
		<%
			}
		%>
	</div>
</header>