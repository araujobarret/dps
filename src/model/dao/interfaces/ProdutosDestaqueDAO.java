package model.dao.interfaces;

import model.beans.ProdutosDestaque;

public interface ProdutosDestaqueDAO {
	public abstract boolean save(ProdutosDestaque destaque);
	
	public abstract ProdutosDestaque retrieve();
}
