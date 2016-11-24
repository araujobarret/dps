package model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.PedidoProduto;
import model.dao.interfaces.PedidoProdutoDAO;;

/**
 * Este DAO permite manipular dados da tabela pedido_produto 
 * do banco de dados lojaufscar
 * 
 * @author Carlos
 */

public class MySQLPedidoProdutoDAO implements PedidoProdutoDAO {
	private Session session;
	
	public boolean save(PedidoProduto pedidoProduto)
	{
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(pedidoProduto);
			tx.commit();
			return true;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); // Desfaz a operação de inserção
		}
		finally{
			session.close();
		}
		
		return false;
	}	
}
