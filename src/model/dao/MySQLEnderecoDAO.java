package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.Cliente;
import model.beans.Endereco;
import model.dao.interfaces.EnderecoDAO;

/**
 * Este DAO permite manipular dados da tabela endereco 
 * de clientes do banco de dados lojaufscar
 * 
 * @author Carlos
 */
public class MySQLEnderecoDAO implements EnderecoDAO {
	private Session session;

	public ArrayList<Endereco> retrieveList(Cliente cpf) throws Exception 
	{
		Endereco endereco = new Endereco();
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Query q = session.createQuery("select u from Endereco u where u.cliente_cpf=?");
		q.setInteger(0, cpf.getCpf());
		List lista = q.list();
		if(!lista.isEmpty())
		{
			return (ArrayList<Endereco>)lista;
		}
		return null;
	}
	
	public boolean save(Endereco endereco) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(endereco);
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
