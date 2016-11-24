<%@ page import="model.dao.interfaces.CategoriaDAO" %>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="model.beans.Categoria" %>
<%@ page import="java.util.List" %>
<div class="col-md-2 center">
  <nav class="text-center panel panel-primary">
      <div class="panel-heading">
      <span>Categorias</span>
      </div>
      <div class="panel-body">
        <ul class="nav nav-pills nav-stacked">
						<%
            	int i = 0;
            	Categoria categoria;
            	CategoriaDAO lista = MySQLLojaUfscarDAOFactory.getCategoriaDAO();
            	List<Categoria> categorias = lista.retrieve();
            	while(i < categorias.size())
            	{
            		categoria = categorias.get(i);
            %>
            		<li><a href="index.jsp?categoria_id=<%=categoria.getId()%>"><%= categoria.getDescricao() %></a></li>
            <% 
            		i++;
            	}
            %>	
        </ul>        
      </div>
  </nav>
</div>