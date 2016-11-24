package model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

/**
 * JavaBean que representa a tabela pedido_produto do BD
 * no sistema Loja UFSCar
 * 
 * @author Carlos
**/

@Entity
@Table(name="pedido_produto")
public class PedidoProduto implements Serializable
{
	@Id
	@ManyToOne @JoinColumn(name="produto_id")
	private Produto produto_id;
	
	@Id
	@ManyToOne @JoinColumn(name="pedido_id")
	private Pedido pedido_id;
	
	@Column(name="quantidade")
	private int quantidade;
	
	@Column(name="valor_unitario", precision = 10, scale = 2 )
	private BigDecimal valor_unitario;
	
	@Column(name="valor_total", precision = 10, scale = 2 )
	private BigDecimal valor_total;
	
	public PedidoProduto() {}
	
	public PedidoProduto(Produto produto_id, Pedido pedido_id, int quantidade, BigDecimal valor_unitario, BigDecimal valor_total)
	{
		this.produto_id = produto_id;
		this.pedido_id = pedido_id;
		this.quantidade = quantidade;
		this.valor_unitario = valor_unitario;
		this.valor_total = valor_total;
	}	
	
	// GETTERS E SETTERS
	public Produto getProduto_id()
	{
		return this.produto_id;
	}
	
	public void setProduto_id(Produto produto_id)
	{
		this.produto_id = produto_id;
	}
	
	
	public Pedido getPedido_id()
	{
		return this.pedido_id;
	}
	
	public void setPedido_id(Pedido pedido_id)
	{
		this.pedido_id = pedido_id;
	}
	
	
	public int getQuantidade()
	{
		return this.quantidade;
	}
	
	public void setQuantidade(int quantidade)
	{
		this.quantidade = quantidade;
	}
	
	
	public BigDecimal getValor_unitario()
	{
		return this.valor_unitario;
	}
	
	public void setValor_unitario(BigDecimal valor_unitario)
	{
		this.valor_unitario = valor_unitario;
	}
	
	
	public BigDecimal getValor_total()
	{
		return this.valor_total;
	}
	
	public void setValor_total(BigDecimal valor_total)
	{
		this.valor_total = valor_total;
	}
}
