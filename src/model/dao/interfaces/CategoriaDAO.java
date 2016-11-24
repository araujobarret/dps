package model.dao.interfaces;

import java.util.ArrayList;

import model.beans.Categoria;

public interface CategoriaDAO {
	
	public ArrayList<Categoria> retrieve() throws Exception;
}
