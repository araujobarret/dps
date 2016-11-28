package model.dao.interfaces;

import java.util.ArrayList;

import model.beans.Cliente;
import model.beans.Pedido;

public interface PedidoDAO 
{
	public abstract Pedido save(Pedido pedido);
	
	public ArrayList<Pedido> retrieveList() throws Exception;
	
	public void liberarEntrega(Pedido pedido) throws Exception;
	
	public void cancelarPedido(Pedido pedido) throws Exception;
	
	public ArrayList<Pedido> retrieveListPeriodo(String data1, String data2) throws Exception;
	
	public ArrayList<Pedido> retrievePedidosCliente(Cliente cliente) throws Exception;

}
