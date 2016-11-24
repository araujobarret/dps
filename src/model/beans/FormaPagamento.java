package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * JavaBean que representa a tabela forma_pagamento do BD
 * no sistema Loja UFSCar
 * 
 * @author Carlos
**/

@Entity
@Table(name="forma_pagamento")
public class FormaPagamento {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descricao")
	private String descricao;
	
	public FormaPagamento(){}
	
	public FormaPagamento(int id, String descricao)
	{
		this.id = id;
		this.descricao = descricao;
	}
	
	// GETTERS E SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
