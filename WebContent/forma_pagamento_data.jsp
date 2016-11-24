<%@page import="model.dao.interfaces.FormaPagamentoDAO"%>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.PedidoDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.FormaPagamento" %>
<%@ page import="java.util.List" %>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// admin.jsp
	if(sessao.getAttribute("login") != null)
	{
%>
<!DOCTYPE html>
<html>
  <head>
	  <meta charset="utf-8">
	  <link rel="stylesheet" href="css/main.css" />
	  <link rel="stylesheet" href="css/bootstrap.min.css" />
	  <script src="js/jquery-2.2.4.min.js"></script>
	  <script src="js/bootstrap.min.js"></script>
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
	          	<h3 class="panel-title"><b>Forma de pagamento</b></h3>
	          </div>
	          <div class="panel-body">
	          	<form name="form" action="ServletFormaPagamento" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8">
	            	<div class="form-group">                        				
	           			<% 	if(request.getParameter("mensagem") != null)
	           			   	{	
	           			%>
	           			<%= request.getParameter("mensagem") %>
	           			<%	} %>
	           		</div>
								<%
								String descricao = "";
								if(request.getParameter("id") != null)
								{
									FormaPagamentoDAO formaPagamentoDAO = MySQLLojaUfscarDAOFactory.getFormaPagamentoDAO();	
									FormaPagamento formaPagamento;	
									formaPagamento = formaPagamentoDAO.retrieve(Integer.parseInt(request.getParameter("id")));
									descricao = formaPagamento.getDescricao();
								%>	
	              <div class="form-group">
	                <label class="control-label col-md-2" for="nome">Id</label>  
	                <div class="col-md-4">
	                  <input type="text" id="disabledInput" class="form-control" name="id" value=<%=request.getParameter("id")%> />
	                </div>
	              </div>
	              <%
								}
	              %>
	              <div class="form-group">
	                <label class="control-label col-md-2" for="descricao">Descrição: </label>  
	                <div class="col-md-4">
	                  <input type="text" class="form-control" name="descricao" value="<%=descricao%>" />
	                  <br/>
	                		<button type="submit" class="pull-right btn btn-default" onClick="this.form.submit()">Salvar</button>
	                </div>                                                                                                                        
	              </div>                                
	            </form>
	          </div>
	        </div>
	      </div>
	    </section>
		</div>
	</body>
</html>
<%
	}
	else
		response.sendRedirect("admin.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>