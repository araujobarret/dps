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


/**
 * JavaBean que representa a tabela Produto do BD
 * no sistema Loja UFSCar
 * 
 * @author Carlos
**/

@Entity
@Table(name="produto")
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="caracteristicas")
	private String caracteristicas;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria_id;
	
	@Column(name="quantidade_estoque")
	private int quantidade_estoque;
	
	@Column(name="preco_custo", precision = 10, scale = 2 )
	private BigDecimal preco_custo;
	
	@Column(name="preco_venda", precision = 10, scale = 2 )
	private BigDecimal preco_venda;
	
	@Column(name="status")
	private short status;

	public Produto(){
		
	}

	public Produto(int id, String descricao, String caracteristicas, Categoria categoria_id, int quantidade_estoque
			, BigDecimal preco_custo, BigDecimal preco_venda, short status){
		this.id = id;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.categoria_id = categoria_id;
		this.quantidade_estoque = quantidade_estoque;
		this.preco_custo = preco_custo;
		this.preco_venda = preco_venda;
		this.status = status;
	}
			
	// GETTERS & SETTERS
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
	
	
	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	public Categoria getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Categoria categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	
	public int getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public void setQuantidade_estoque(int quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}
	
	
	public BigDecimal getPreco_custo() {
		return preco_custo;
	}

	public void setPreco_custo(BigDecimal preco_custo) {
		this.preco_custo = preco_custo;
	}
	
	
	public BigDecimal getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}
	
	
	public short getStatus(){
		return status;
	}
	
	public void setStatus(short status){
		this.status = status;
	}
		
}
