package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CriptografiaMD5;
import model.beans.Cliente;
import model.beans.Endereco;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.ClienteDAO;
import model.dao.interfaces.EnderecoDAO;


/**
 * Servlet implementation class ServletCadastroCliente
 */
@WebServlet("/ServletCadastroCliente")
public class ServletCadastroCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws NoSuchAlgorithmException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NoSuchAlgorithmException
    {
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "?mensagem="; // Mensagem devolvida ao JSP de cadastro do funcionário
        
        try
        {
          //Obtém os valores digitados nos campos do formulário
          String senha = CriptografiaMD5.getHash(request.getParameter("senha")); // Criptografa a senha passada pelo POST e armazena na string que irá para o BD
          String nome = request.getParameter("nome");
          int cpf;
          String data_nascimento = request.getParameter("data_nascimento");
          String email = request.getParameter("email");
          String telefone1 = request.getParameter("telefone1");
          String telefone2 = request.getParameter("telefone2");
          String telefone3 = request.getParameter("telefone3");
          
          // Captura as informações do endereço;
          String descricao = request.getParameter("descricao");
          String logradouro = request.getParameter("logradouro");
      	  String numero = request.getParameter("numero");
      	  String complemento = request.getParameter("complemento");
      	  String bairro = request.getParameter("bairro");
      	  String cidade = request.getParameter("cidade");
      	  String uf = request.getParameter("uf");
      	  String cep = request.getParameter("cep");

          
          //Verifica se não há atributos com null ou vazios
          if(senha != null && !senha.equals("")
        		  && nome != null && !nome.equals("")
        		  && data_nascimento != null && !data_nascimento.equals("")
        		  && email != null && !email.equals("")
    		  	  && telefone1 != null && !telefone1.equals("")
    		  	  && telefone2 != null && !telefone2.equals("")
    		  	  && telefone3 != null && !telefone3.equals("")
    		  	  
        		  // Verificação do endereço
    		  	  && descricao != null && !descricao.equals("")
    	    	  && logradouro != null && !logradouro.equals("")
    	    	  && numero != null && !numero.equals("")
    	    	  && complemento != null && !complemento.equals("")
    	    	  && bairro != null && !bairro.equals("")
    	    	  && cidade != null && !cidade.equals("")
    	    	  && uf != null && !uf.equals("")
    	    	  && cep != null && !cep.equals(""))
          {
        	  cpf = Integer.parseInt(request.getParameter("cpf"));
        	  Cliente cliente = new Cliente();
        	  Endereco endereco = new Endereco();

        	  try {
              
        		  cliente.setSenha(senha);
        		  cliente.setNome(nome);
        		  cliente.setCpf(cpf);
        		  cliente.setData_nascimento(data_nascimento);
        		  cliente.setEmail(email);
        		  cliente.setTelefone1(telefone1);
        		  cliente.setTelefone2(telefone2);
        		  cliente.setTelefone3(telefone3);        		  
        		  
        		  ClienteDAO clienteDAO = MySQLLojaUfscarDAOFactory.getClienteDAO();
        		  
                  
                  if(clienteDAO.save(cliente))  //Tenta gravar os dados no banco de dados. Se retornar true então o cadastro ocorreu com sucesso
                  {
                	  // Aqui o objeto da classe Endereço recebe seus valores
            		  endereco.setCliente_cpf(cliente);
            		  endereco.setDescricao(descricao);
            		  endereco.setLogradouro(logradouro);
            		  endereco.setNumero(numero);
            		  endereco.setComplemento(complemento);
            		  endereco.setBairro(bairro);
            		  endereco.setCidade(cidade);
            		  endereco.setUf(uf.substring(0, 2));
            		  endereco.setCep(cep);
            		  
                	  EnderecoDAO enderecoDAO = MySQLLojaUfscarDAOFactory.getEnderecoDAO();
                	  // Tenta gravar endereço do cliente na tabela endereco
                	  if(enderecoDAO.save(endereco)){
                		  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-success\">Cadastro de"
                          		+ " cliente realizado com sucesso.</span>";	
                	  }
                	  else{
                		  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao "
                      	 		+ "cadastrar o endereço do cliente!</span>";
                	  }
                  }
                  else{
                	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar "
                	  		+ "o cliente!</span>";
                  }
                    
            }
            catch (Exception ex){
            	mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">Erro ao cadastrar "
            	  		+ "o cliente! " + ex.getMessage() + "</span>";
            }
          }
          
          else{
        	  mensagem += "<span class=\"col-md-5 col-md-offset-1 alert alert-danger\">É necessário preencher todos "
          	  		+ "os campos do formulário!</span>";
          }
          request.setAttribute("mensagem", mensagem);
      	  request.getRequestDispatcher("cadastro_cliente.jsp" + mensagem).forward(request, response);          
        } 
        finally
        {
          
        }
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
