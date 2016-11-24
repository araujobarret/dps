package model.dao.interfaces;

import java.util.ArrayList;

import model.beans.Cliente;
import model.beans.Endereco;


public interface EnderecoDAO {
	
	public ArrayList<Endereco> retrieveList(Cliente cpf) throws Exception;
	public abstract boolean save(Endereco endereco);
}
