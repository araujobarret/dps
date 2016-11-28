package model.dao.interfaces;

import model.beans.Cliente;

public interface ClienteDAO {
	
	public abstract boolean save(Cliente cliente);
	
	public Cliente retrieve(Cliente cliente) throws Exception;
	
	public Cliente retrieveCPF(String cpf) throws Exception;
	
}