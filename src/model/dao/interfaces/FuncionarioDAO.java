package model.dao.interfaces;

import model.beans.Funcionario;

public interface FuncionarioDAO {
	public abstract boolean save(Funcionario funcionario);
	public Funcionario retrieve(Funcionario funcionario) throws Exception;
}
