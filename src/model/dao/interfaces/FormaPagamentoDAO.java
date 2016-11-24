package model.dao.interfaces;

import java.util.ArrayList;
import model.beans.FormaPagamento;

public interface FormaPagamentoDAO {
	public ArrayList<FormaPagamento> retrieveList() throws Exception;
	
	public abstract boolean save(FormaPagamento formaPagamento);
	
	public abstract boolean delete(int id);
	
	public abstract boolean update(FormaPagamento formaPagamento);
	
	public abstract FormaPagamento retrieve(int id);
	
}
