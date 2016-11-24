package model.beans;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.dao.interfaces.EnderecoDAO;


/**
 * JavaBean que representa a tabela Pedido do BD
 * no sistema Loja UFSCar
 * 
 * @author Carlos
**/

@Entity
@Table(name="pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="data_pedido")
	private String data_pedido;
	
	@ManyToOne
	@JoinColumn(name="cliente_cpf")
	private Cliente cliente_cpf;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private Endereco endereco_entrega_id;
	
	@ManyToOne
	@JoinColumn(name="forma_pagamento_id")
	private FormaPagamento forma_pagamento_id;
	
	@Column(name="frete", precision = 10, scale = 2 )
	private BigDecimal frete;
	
	@Column(name="total", precision = 10, scale = 2 )
	private BigDecimal total;
	
	@Column(name="status")
	private char status;
	
	public Pedido(){
		this.status = '0';
	}
	
	public Pedido(int id, String data_pedido, Cliente cliente_cpf, Endereco endereco_entrega_id, FormaPagamento forma_pagamento_id,
			BigDecimal frete, BigDecimal total, char status)
	{
		this.id = id;
		this.data_pedido = data_pedido;
		this.cliente_cpf = cliente_cpf;
		this.endereco_entrega_id = endereco_entrega_id;
		this.forma_pagamento_id = forma_pagamento_id;
		this.frete = frete;
		this.total = total;
		this.status = status;
	}
	
	// GETTERS E SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(String data_pedido) {
		this.data_pedido = data_pedido;
	}
	
	
	public Cliente getCliente_cpf()
	{
		return this.cliente_cpf;
	}
	
	public void setCliente_cpf(Cliente cliente_cpf)
	{
		this.cliente_cpf = cliente_cpf;
	}
	
	
	public Endereco getEndereco_entrega_id()
	{
		return this.endereco_entrega_id;
	}
	
	public void setEndereco_entrega_id(Endereco endereco_entrega_id)
	{
		this.endereco_entrega_id = endereco_entrega_id;
	}
	
	
	public FormaPagamento getForma_pagamento_id() {
		return this.forma_pagamento_id;
	}

	public void setForma_pagameto_id(FormaPagamento forma_pagamento_id) {
		this.forma_pagamento_id = forma_pagamento_id;
	}
	
	
	public BigDecimal getFrete() {
		return this.frete;
	}

	public void setFrete(BigDecimal frete) {
		this.frete = frete;
	}
	
	
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public char getStatus()	{
		return this.status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}	
}
