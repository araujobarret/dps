package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.Cliente;
import model.beans.Pedido;
import model.dao.interfaces.PedidoDAO;

/**
 * Este DAO permite manipular dados da tabela pedido 
 * de clientes do banco de dados lojaufscar
 * 
 * @author Carlos
 */


public class MySQLPedidoDAO implements PedidoDAO {
	private Session session;
	
	@Override
	public Pedido save(Pedido pedido) 
	{
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
				
		try{
			tx = session.beginTransaction();
			session.save(pedido);			
			tx.commit();
			return pedido;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); //  Desfaz a opera��o de inser��o
		}
		finally{
			session.close();
		}		
		return null;		
	}
	
	public ArrayList<Pedido> retrieveList() throws Exception 
	{
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Pedido");
	    List lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Pedido>)lista;
	    }
		return null;
	}
	
	public void liberarEntrega(Pedido pedido) throws Exception
	{
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
				
		try{
			tx = session.beginTransaction();
			session.update(pedido);			
			tx.commit();
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); // Desfaz a opera��o de inser��o
		}
		finally{
			session.close();
		}		
	}

	@Override
	public ArrayList<Pedido> retrieveListPeriodo(String data1, String data2) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    	    
		Query q = session.createQuery("from Pedido where data_pedido >= '" + data1 + "' and data_pedido <= '" + data2 + "'");
	    
	    List lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Pedido>)lista;
	    }
		return null;
	}

	
	@Override
	public ArrayList<Pedido> retrievePedidosCliente(Cliente cliente) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    	    
		Query q = session.createQuery("from Pedido where cliente_cpf = " + cliente.getCpf() + " order by id desc");
	    
	    List<?> lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Pedido>)lista;
	    }
		return null;
	}

	@Override
	public void cancelarPedido(Pedido pedido) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
				
		try{
			tx = session.beginTransaction();
			session.update(pedido);			
			tx.commit();
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); // Desfaz a opera��o de inser��o
		}
		finally{
			session.close();
		}		
	}
}
