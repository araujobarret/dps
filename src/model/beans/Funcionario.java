package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JavaBean que representa o cadastro de um Funcionário
 * no sistema Loja UFSCar
 * 
 * @author Matheus
**/

@Entity
@Table(name="funcionario")
public class Funcionario {
	@Id
	private String login;
	@Column(name="nome")
	private String nome;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="email")
	private String email;

	
	public Funcionario() {
	}
	
	public Funcionario(String login, String nome, String senha, String email) {
		super();
		this.login = login;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	
	// GETTERS & SETTERS
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
