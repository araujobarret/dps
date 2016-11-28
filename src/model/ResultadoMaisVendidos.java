package model;

import java.math.BigDecimal;


public class ResultadoMaisVendidos {
	
	private int produto_id;
	private BigDecimal soma_produtos;
	
	public BigDecimal getSoma_produtos() {
		return soma_produtos;
	}
	public void setSoma_produtos(BigDecimal soma_produtos) {
		this.soma_produtos = soma_produtos;
	}
	public int getProduto_id() {
		return produto_id;
	}
	public void setProduto_id(int produto_id) {
		this.produto_id = produto_id;
	}

}
