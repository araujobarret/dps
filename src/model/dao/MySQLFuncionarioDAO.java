package model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.Funcionario;
import model.dao.interfaces.FuncionarioDAO;

/**
 * Este DAO permite manipular dados da tabela funcionario 
 * do banco de dados lojaufscar
 * 
 * @author Matheus
 */
public class MySQLFuncionarioDAO implements FuncionarioDAO {
	private Session session;
	
	public boolean save(Funcionario funcionario) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(funcionario);
			tx.commit();
			return true;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); // Desfaz a opera��o de inser��o
		}
		finally{
			session.close();
		}
		
		return false;
	}
	
	public Funcionario retrieve(Funcionario login) throws Exception {
		if(login == null)
			throw new Exception("O parâmetro é nulo");

		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("select u from Funcionario u where u.senha = ? and u.login = ?");
	    q.setString(0, login.getSenha());
	    q.setString(1, login.getLogin());
	    
	    List<?> l = q.list();
	    if (!l.isEmpty()){
	    	Funcionario funcionario = (Funcionario)l.get(0);
	        return funcionario;
	    }
	    return null;
	}
	
}
