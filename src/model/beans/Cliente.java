package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * JavaBean que representa o cadastro de um Cliente
 * no sistema Loja UFSCar
 * 
 * @author Matheus
**/

@Entity
@Table(name="cliente")
public class Cliente {
	@Column(name="senha")
	private String senha;
	
	@Column(name="nome")
	private String nome;
	
	@Id
	private int cpf;
	
	@Column(name="data_nascimento")
	private String data_nascimento;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefone1")
	private String telefone1;
	
	@Column(name="telefone2")
	private String telefone2;
	
	@Column(name="telefone3")
	private String telefone3;

	
	public Cliente() {
	}

	public Cliente(String senha, String nome, int cpf, String data_nascimento,
			String email, String telefone1, String telefone2, String telefone3) {
		super();
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
	}



	// GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
}
