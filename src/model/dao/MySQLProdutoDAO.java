package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.Categoria;
import model.beans.Produto;
import model.dao.interfaces.ProdutoDAO;

/**
 * Este DAO permite manipular dados da tabela Produto 
 * do banco de dados lojaufscar
 * 
 * @author Carlos
 */
public class MySQLProdutoDAO implements ProdutoDAO {
	private Session session;
	
	public ArrayList<Produto> retrieveList() throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Produto");
	    List lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Produto>)lista;
	    }
	    return null;
	}
	
	@Override
	public boolean saveProduto(Produto produto) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(produto);
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
	
	public Produto retrieve(Produto pesquisa) throws Exception {
		if(pesquisa == null)
			throw new Exception("O parâmetro é nulo");

		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("select u from Produto u where u.id = ?");
	    q.setInteger(0, pesquisa.getId());	    
    
	    List<?> l = q.list();
	    if (!l.isEmpty()){
	    	Produto produto = (Produto)l.get(0);
	        return produto;
	    }
	    return null;
	}
	
	@Override
	public Produto retrieveProdutoID(int idProduto) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Produto where id = ?")
	    		.setInteger(0, idProduto);
	    	    List<Produto> lista = q.list();
	    if (!lista.isEmpty()){
	    	return lista.get(0);
	    }
	    return null;
	}

	@Override
	public boolean updateProduto(Produto produto) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.update(produto);
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

	@Override
	public boolean deleteProduto(int idProduto) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		
		try{
			Query q = session.createQuery("delete from Produto where id = ?")
			    		.setInteger(0, idProduto);
			q.executeUpdate();
			
			return true;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return false;
	}

	@Override
	public ArrayList<Produto> searchProdutosAtivos(String produto) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Produto where status = 1 AND descricao LIKE ?");
	    q.setString(0, "%" + produto + "%");
	    List<Produto> lista = q.list();	    
	    if (!lista.isEmpty()){
	    	return (ArrayList<Produto>)lista;
	    }
	    return null;
	}
	
	@Override
	public ArrayList<Produto> retrieveProdutosCategoria(Categoria categoria) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Produto where status = 1 AND categoria_id=?");
	    q.setInteger(0, categoria.getId());
	    List<Produto> lista = q.list();	    
	    if (!lista.isEmpty()){
	    	return (ArrayList<Produto>)lista;
	    }
	    return null;
	}

	@Override
	public ArrayList<Produto> retrieveProdutosAtivos() throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Produto where status = 1");
	    List<Produto> lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Produto>)lista;
	    }
	    return null;
	}
	
	@Override
	public boolean suspendeProduto(int idProduto) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		
		try{
			Query q = session.createQuery("update Produto set status = 0 where id = ?")
			    		.setInteger(0, idProduto);
			q.executeUpdate();
			
			return true;
		}
		catch(HibernateException ex){
			ex.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return false;
	}
	
}
