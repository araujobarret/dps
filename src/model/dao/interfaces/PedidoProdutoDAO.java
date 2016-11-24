package model.dao.interfaces;

import model.beans.PedidoProduto;

public interface PedidoProdutoDAO {
	public boolean save(PedidoProduto pedidoProduto) throws Exception;
}
