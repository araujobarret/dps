<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.dao.interfaces.CategoriaDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Categoria" %>
<%@ page import="java.util.List" %>

<%
	int i = 0;
	Categoria categoria;
	CategoriaDAO lista = MySQLLojaUfscarDAOFactory.getCategoriaDAO();
	List<Categoria> categorias = lista.retrieve();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
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
                            <h3 class="panel-title"><b>Requisição produtos por período</b></h3>
                        </div>
                        <div class="panel-body">
                            <form name="form" action="resultado_periodo.jsp" method="POST" accept-charset="iso-8859-1,utf-8" class="form-horizontal">
                                
                                <div class="form-group">
                                    <label class="control-label col-md-4" for="data1">Entre:</label>  
                                    <div class="col-md-2">
                                        <input type="date"  name="data1" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4" for="data2">e:</label>  
                                    <div class="col-md-2">
                                        <input type="date"  name="data2" required/>
                                    </div>
                                </div>
                                
                                
                                
                                <button type="submit" class="pull-right btn btn-default" onClick="this.form.submit()">Gerar relatório</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>