<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="ckeditor/ckeditor.js"></script>
<style>
.rodape {
	margin: 50px 0;
}

.btn-menu {
	margin: 20px;
	width: 300px;
	height: 40px;
}

.menu-produto {
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
			<div class="col-md-6 center">
				<div class="row panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<b>Gestão de Produtos</b>
						</h3>
					</div>
					<div class="container">
						<form id="form_compromisso" method="POST"
							action="cadastro_produto.jsp">
							<button type="submit" class="btn btn-primary btn-menu">Cadastrar
								Novo Produto</button>
						</form>

						<form id="form_compromisso" method="POST"
							action="lista_produtos.jsp">
							<button type="submit" class="btn btn-primary btn-menu">Listagem
								de Produtos</button>
						</form>

						<form id="form_compromisso" method="POST"
							action="suspende_produtos.jsp">
							<button type="submit" class="btn btn-primary btn-menu">Suspender
								Produtos</button>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>