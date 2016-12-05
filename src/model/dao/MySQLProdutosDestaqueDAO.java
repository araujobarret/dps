package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import model.beans.Produto;
import model.beans.ProdutosDestaque;
import model.dao.interfaces.ProdutosDestaqueDAO;
import model.dao.interfaces.ProdutoDAO;
import model.dao.MySQLLojaUfscarDAOFactory;

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
	
	public ArrayList<Produto>retrieveList() throws Exception {
		ProdutosDestaque destaque = null;
		ProdutoDAO produtoDAO = MySQLLojaUfscarDAOFactory.getProdutoDAO();
		List<Produto> produtos;
		ArrayList<Produto>produtosReturn = new ArrayList<Produto>();		
		List<ProdutosDestaque> destaques;
		try{
			produtos = produtoDAO.retrieveList();
		
			session = MySQLLojaUfscarDAOFactory.getInstance();
		    Query q = session.createQuery("from ProdutosDestaque");	    
		    
		    List lista = q.list();	    
		    if (!lista.isEmpty()){
		    	System.out.println("AQUI");
		    	destaques = (ArrayList<ProdutosDestaque>)lista;
		    	for(ProdutosDestaque d : destaques){
		    		destaque = d;
		    	}
		    	for(Produto p : produtos){
		    		if(p.getId() == destaque.getId_produto1())
		    			produtosReturn.add(p);
		    		if(p.getId() == destaque.getId_produto2())
		    			produtosReturn.add(p);
		    		if(p.getId() == destaque.getId_produto3())
		    			produtosReturn.add(p);
		    		if(p.getId() == destaque.getId_produto4())
		    			produtosReturn.add(p);
		    	}
		    	return produtosReturn;
		    }
		    return null;
		}
	    catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}
}
