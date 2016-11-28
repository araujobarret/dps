package model.dao.interfaces;

import model.ResultadoMaisVendidos;
import model.beans.Pedido;
import model.beans.PedidoProduto;

import java.util.ArrayList;

public interface PedidoProdutoDAO {
	public boolean save(PedidoProduto pedidoProduto) throws Exception;
	
	public ArrayList<Integer> buscaResultado() throws Exception;
	
	public ArrayList<ResultadoMaisVendidos> maisVendidos(String ordem, int quantia) throws Exception;
	
	public ArrayList<PedidoProduto> retrievePedido(Pedido pedido) throws Exception;
		
	
}
