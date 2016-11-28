package model.dao;

import model.dao.interfaces.CategoriaDAO;
import model.dao.interfaces.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** F�brica de conex�es que permite estabelecer conex�o 
 * 	com o banco de dados MySQL
 * @author Matheus
**/
public class MySQLLojaUfscarDAOFactory {
	private static final SessionFactory sessionFactory; // A f�brica de sess�es para as conex�es dos usu�rios com o banco
	
	//Thread utilizada para executar a sess�o do usu�rio concorrentemente a outras threads
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	static{
		try{
			/* Configura a f�brica de conex�es para obter os dados da conex�o 
			 * 		a partir do arquivo hibernate.cfg.xml */
			
			sessionFactory = new 
					Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		catch(Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}


	}
		
	/**
	 * Esta fun��o cria uma sess�o para o usu�rio e a retorna
	 * @return A sess�o do usu�rio que permitir� o acesso ao banco de dados
	 */
	public static Session getInstance(){
		Session session = (Session) threadLocal.get();
		session = sessionFactory.openSession();
		threadLocal.set(session);
		return session;
	}
	
	public static FuncionarioDAO getFuncionarioDAO(){
		return new MySQLFuncionarioDAO();
	}
	
	public static ClienteDAO getClienteDAO(){
		return new MySQLClienteDAO();
	}
	
	public static EnderecoDAO getEnderecoDAO(){
		return new MySQLEnderecoDAO();
	}
	
	public static CategoriaDAO getCategoriaDAO(){
		return new MySQLCategoriaDAO();
	}

	public static ProdutoDAO getProdutoDAO(){
		return new MySQLProdutoDAO();
	}
	
	public static FormaPagamentoDAO getFormaPagamentoDAO(){
		return new MySQLFormaPagamentoDAO();
	}
	
	public static PedidoDAO getPedidoDAO(){
		return new MySQLPedidoDAO();
	}
	
	public static PedidoProdutoDAO getPedidoProdutoDAO(){
		return new MySQLPedidoProdutoDAO();
	}
	
	public static ProdutosDestaqueDAO getProdutosDestaqueDAO(){
		return new MySQLProdutosDestaqueDAO();
	}
	
}
