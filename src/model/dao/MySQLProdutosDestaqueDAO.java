package model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import model.beans.ProdutosDestaque;
import model.beans.ProdutosDestaque;
import model.dao.interfaces.ProdutosDestaqueDAO;

public class MySQLProdutosDestaqueDAO implements ProdutosDestaqueDAO {
	private Session session;
	
	// Edição da linha única destaque
	@Override
	public boolean save(ProdutosDestaque destaque) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.update(destaque);
			tx.commit();
			return true;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback(); // Desfaz a operaÃ§Ã£oo de inserÃ§Ã£o
		}
		finally{
			session.close();
		}
		
		return false;
	}

	// Retorna o objeto do banco de dados com os dados pertinentes
	@Override
	public ProdutosDestaque retrieve(){		

		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("select u from ProdutosDestaque u");	    
	    
	    List<?> l = q.list();
	    if (!l.isEmpty()){
	    	ProdutosDestaque destaque = (ProdutosDestaque)l.get(0);
	        return destaque;
	    }
	    return null;
	}
}
