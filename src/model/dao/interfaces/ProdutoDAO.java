package model.dao.interfaces;

import java.util.ArrayList;

import model.beans.Categoria;
import model.beans.Produto;

public interface ProdutoDAO {
	
	public ArrayList<Produto> retrieveList() throws Exception;
	
	public abstract boolean saveProduto(Produto produto);
	
	public Produto retrieve(Produto pesquisa) throws Exception;
	
	public ArrayList<Produto> retrieveProdutosAtivos() throws Exception;
	
	public ArrayList<Produto> retrieveProdutosCategoria(Categoria categoria) throws Exception;
	
	public ArrayList<Produto> searchProdutosAtivos(String produto) throws Exception;
	
	public Produto retrieveProdutoID(int idProduto);
	
	public abstract boolean updateProduto(Produto produto);
	
	public abstract boolean deleteProduto(int idProduto);
	
	public abstract boolean suspendeProduto(int idProduto);
}
