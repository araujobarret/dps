<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.beans.Cliente" %>
<%@ page import="model.dao.interfaces.ClienteDAO" %>
<%@ page import="model.beans.Endereco" %>
<%@ page import="model.dao.interfaces.EnderecoDAO"%>
<%@ page import="model.dao.MySQLLojaUfscarDAOFactory" %>
<%@ page import="java.util.List"%>
<% 
	HttpSession sessao = request.getSession();
	// Se o usuário estiver com a sessao aberta permite o acesso ao painel da sua conta, caso contrário retorna para a página
	// login.jsp
	
	String mensagem = null;
	
	if(request.getParameter("mensagem") != null)
		mensagem = request.getParameter("mensagem");
	
	if(sessao.getAttribute("cpf") != null)
	{
		Cliente cliente;
		ClienteDAO clienteDAO = MySQLLojaUfscarDAOFactory.getClienteDAO();
		cliente = clienteDAO.retrieveCPF(sessao.getAttribute("cpf").toString());
		
		Endereco endereco;
		EnderecoDAO listaEnderecos = MySQLLojaUfscarDAOFactory.getEnderecoDAO();
		List<Endereco> enderecos = listaEnderecos.retrieveList(cliente);
		
		int i = 0;
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

        <style>
        .panel-endereco{
        background-color: #b3b3b3;
        margin-top: 20px;
        padding: 30px 20px;
        }
        
        .btn-primary{
        margin-bottom: 50px;
        }
        
        .alert{
        margin-top: 10px;
        }
        
        .menu-endereco{
        font-weight: bold;
        }
        
        
        </style>
    </head>
	<body>
		<div class="container-fluid">
		  	<jsp:include page="header.jsp" />
	            
	  		<section class="row">
		 		<jsp:include page="menu_cliente.jsp" />
	
				<div class="col-md-9 center">
					<div class="row panel panel-primary">
		        		<div class="panel-heading">
		                	<h3 class="panel-title"><b>Meus endereços</b></h3>
		                </div>
						<div class="container">
			       			
			       			<% if(mensagem != null){
			       				if(mensagem.contains("cod1")){
			       			%>
			       			
				       			<div class="alert alert-success col-md-6">
	  								 Endereço cadastrado com <strong>sucesso!</strong>
								</div>
				       			<%
				       			}
				       			else if(mensagem.contains("cod4")){
				       			%>
				       			
					       			<div class="alert alert-warning col-md-6">
		  								<strong>Aviso!</strong> Todos os campos devem ser preenchidos
									</div>
					       		<%
					       		} 
				       			else if (mensagem.contains("cod3")){
				       			%>
				       				<div class="alert alert-danger col-md-6">
		  								<strong>Erro</strong> ao cadastrar o endereço!
									</div>
				       			<%
				       			}
				       			else if(mensagem.contains("Erro:")){
				       			%>
				       				<div class="alert alert-danger col-md-6">
		  								<%=mensagem %>
									</div>
				       			<%
				       			}
			       			}
			       			%>
			       			
			       			<div class="col-md-9 center listagemEnderecos">
			         			<div class="row">
					         		
					         		<%
					         			while(i < enderecos.size()){
					         				endereco = enderecos.get(i);
					         			
					         		
					         		%>
					         		<div class="col-md-6">
				      					<div class="panel panel-primary panel-endereco">
				        					<span><strong>Descrição:  </strong><%= endereco.getDescricao() %></span></br>
					    					<span><strong>Logradouro:  </strong><%= endereco.getLogradouro() %>, <%= endereco.getNumero() %></span></br>
					    					<span><strong>Bairro:  </strong><%= endereco.getBairro() %></span></br>	
											<span><strong>Cidade:  </strong><%= endereco.getCidade() %> - <%= endereco.getUf() %></span></br>
					    					<span><strong>CEP:  </strong><%= endereco.getCep() %></span></br>
				        				</div>
				    				</div>
				    				
				    				<%
				    						i++;	
					         			}
				    				%>
			         			</div>
			         			
			         			
			         				
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modalFormularioEndereco">
    										Cadastrar novo endereço
								</button>
								
															<!-- Modal -->
							<div class="modal fade" id="modalFormularioEndereco" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							    <div class="modal-dialog">
							        <div class="modal-content">
							            <!-- Modal Header -->
							            <div class="modal-header">
							                <button type="button" class="close" 
							                   data-dismiss="modal">
							                       <span aria-hidden="true">&times;</span>
							                       <span class="sr-only">Fechar</span>
							                </button>
							                <h4 class="modal-title" id="myModalLabel">
							                    Cadastrar novo endereço
							                </h4>
							            </div>
							            
							            <!-- Modal Body -->
							            <div class="modal-body">
							                
							                <form name="form" action="ServletCadastroNovoEndereco" method="POST" class="form-horizontal" accept-charset="iso-8859-1,utf-8" role="form">
							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="descricao">Tipo</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="descricao"/>
							                    </div>
							                  </div>
							                  
							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="logradouro">Logradouro</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="logradouro"/>
							                    </div>
							                  </div>

							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="numero">Número</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="numero"/>
							                    </div>
							                  </div>
							                  
							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="complemento">Complemento</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="complemento"/>
							                    </div>
							                  </div>

							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="bairro">Bairro</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="bairro"/>
							                    </div>
							                  </div>
							                  
							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="cidade">Cidade</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="cidade"/>
							                    </div>
							                  </div>							                  

							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="uf">UF</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="uf"/>
							                    </div>
							                  </div>							                  
			
							                  <div class="form-group">
							                    <label  class="col-sm-2 control-label" for="cep">CEP</label>
							                    <div class="col-sm-10">
							                        <input type="text" class="form-control" name="cep"/>
							                    </div>
							                  </div>												
											
											<div class="modal-footer">
							                	<button type="submit" class="btn btn-primary" onClick="this.form.submit()">
							                    	Salvar endereço
							                	</button>
							                </div>
							                </form>
							            </div>
							        </div>
							    </div>
							</div>
			       			</div>
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
		response.sendRedirect("login.jsp?mensagem='É necessário fazer o login para acessar esta área.'");
%>