package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JavaBean que representa o cadastro de um Endereco 
 * do Cliente no sistema Loja UFSCar
 * 
 * @author Matheus
**/

@Entity
@Table(name="endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cliente_cpf")
	private Cliente cliente_cpf;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="logradouro")
	private String logradouro;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="complemento")
	private String complemento;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="uf")
	private String uf;
	
	@Column(name="cep")
	private String cep;

	
	public Endereco() {
		super();
	}

	public Endereco(int id, Cliente cliente_cpf, String descricao,
			String logradouro, String numero, String complemento,
			String bairro, String cidade, String uf, String cep) {
		super();
		this.id = id;
		this.cliente_cpf = cliente_cpf;
		this.descricao = descricao;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}


	// GETTERS & SETTERS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Cliente getCliente_cpf() {
		return cliente_cpf;
	}


	public void setCliente_cpf(Cliente cliente_cpf) {
		this.cliente_cpf = cliente_cpf;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}
}
