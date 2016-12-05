package model.dao.interfaces;

import java.util.ArrayList;
import model.beans.ProdutosDestaque;
import model.beans.Produto;

public interface ProdutosDestaqueDAO {
	public abstract boolean save(ProdutosDestaque destaque);
	
	public abstract ProdutosDestaque retrieve();
	
	public ArrayList<Produto> retrieveList() throws Exception;
}
