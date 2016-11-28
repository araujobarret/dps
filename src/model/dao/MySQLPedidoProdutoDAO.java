package model.dao;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.ResultadoMaisVendidos;
import model.beans.Pedido;
import model.beans.PedidoProduto;
import model.beans.Produto;
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> buscaResultado() throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("SELECT u from PedidoProduto where u.");
	    List<Integer> lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Integer>)lista;
	    }
	    return null;
	}

	


	
	@Override
	public ArrayList<ResultadoMaisVendidos> maisVendidos(String ordem, int quantia) throws Exception {
		
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createSQLQuery("select produto_id, sum(quantidade) as soma from pedido_produto group by produto_id ORDER BY soma " + ordem + " limit ?");
	    q.setInteger(0, quantia);
	    
	    List<Object[]> lista = q.list();
	    List<ResultadoMaisVendidos> resultado = new ArrayList<>(lista.size());
	    
	    for(Object[] obj : lista){
	    	ResultadoMaisVendidos rmv = new ResultadoMaisVendidos();
	    	rmv.setProduto_id((int) obj[0]);
	    	rmv.setSoma_produtos((BigDecimal) obj[1]);
	    	
	    	resultado.add(rmv);
	    }
	    
	    if (!resultado.isEmpty()){
	    	return  (ArrayList<ResultadoMaisVendidos>) resultado;
	    }
	    return null;
}

	@Override
	public ArrayList<PedidoProduto> retrievePedido(Pedido pedido) throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Query q = session.createQuery("from PedidoProduto where pedido_id = ?");
		q.setInteger(0, pedido.getId());
		
		List<?> lista = q.list();
		
		 if (!lista.isEmpty()){
		    	return  (ArrayList<PedidoProduto>) lista;
		    }
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
