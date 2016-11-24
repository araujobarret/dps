package model.dao.interfaces;

import java.util.ArrayList;

import model.beans.Pedido;

public interface PedidoDAO 
{
	public abstract Pedido save(Pedido pedido);
	
	public ArrayList<Pedido> retrieveList() throws Exception;
	
	public void liberarEntrega(Pedido pedido) throws Exception;
	
}
