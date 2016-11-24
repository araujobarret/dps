package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.beans.Categoria;
import model.dao.interfaces.CategoriaDAO;

/**
 * Este DAO permite manipular dados da tabela Categoria 
 * do banco de dados lojaufscar
 * 
 * @author Carlos
 */
public class MySQLCategoriaDAO implements CategoriaDAO {
	private Session session;	

	public ArrayList<Categoria> retrieve() throws Exception {
		session = MySQLLojaUfscarDAOFactory.getInstance();
	    Query q = session.createQuery("from Categoria");
	    List lista = q.list();
	    if (!lista.isEmpty()){
	    	return (ArrayList<Categoria>)lista;
	    }	    
	    return null;
	}
	
}