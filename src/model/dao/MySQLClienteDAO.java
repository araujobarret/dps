package model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.Cliente;
import model.dao.interfaces.ClienteDAO;

/**
 * Este DAO permite manipular dados da tabela Cliente 
 * do banco de dados lojaufscar
 * 
 * @author Matheus
 */
public class MySQLClienteDAO implements ClienteDAO {
	private Session session;
	
	public boolean save(Cliente cliente) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(cliente);
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

	public Cliente retrieve(Cliente login) throws Exception {
		if(login == null)
			throw new Exception("O parâmetro é nulo");

		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("select u from Cliente u where u.senha = ? and u.cpf = ?");
	    q.setString(0, login.getSenha());
	    q.setInteger(1, login.getCpf());
	    
	    List<?> l = q.list();
	    if (!l.isEmpty()){
	    	Cliente cliente = (Cliente)l.get(0);
	        return cliente;
	    }
	    return null;
	}
}
