package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.beans.FormaPagamento;
import model.dao.interfaces.FormaPagamentoDAO;

/**
 * Este DAO permite manipular dados da tabela forma_pagamento 
 * do banco de dados lojaufscar
 * 
 * @author Carlos
 */

public class MySQLFormaPagamentoDAO implements FormaPagamentoDAO
{
	private Session session;
	
	public ArrayList<FormaPagamento> retrieveList() throws Exception
	{
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from FormaPagamento");
	    List lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<FormaPagamento>)lista;
	    }
		return null;
	}
	
	@Override
	public FormaPagamento retrieve(int id) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from FormaPagamento where id = ?");
	    q.setInteger(0, id);
	    List<FormaPagamento> lista = q.list();
	    if (!lista.isEmpty()){
	    	return lista.get(0);
	    }
	    return null;
	}
	
	@Override
	public boolean save(FormaPagamento formaPagamento) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(formaPagamento);
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
	public boolean delete(int id) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		
		try{
			Query q = session.createQuery("delete from FormaPagamento where id = ?");
			q.setInteger(0, id);
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
	public boolean update(FormaPagamento formaPagamento) {
		session = MySQLLojaUfscarDAOFactory.getInstance();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.update(formaPagamento);
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
