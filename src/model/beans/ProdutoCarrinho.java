package model.beans;

public class ProdutoCarrinho extends Produto 
{
	private int quantidade_carrinho;
	
	public ProdutoCarrinho(){}
	
	public ProdutoCarrinho(Produto produto)
	{
		super.setId(produto.getId());
		super.setPreco_venda(produto.getPreco_venda());
		super.setDescricao(produto.getDescricao());
		super.setQuantidade_estoque(produto.getQuantidade_estoque());
	}
	
	public int getQuantidade_carrinho()
	{
		return this.quantidade_carrinho;
	}
		
	public void setQuantidade_carrinho(int quantidade_carrinho)
	{
		this.quantidade_carrinho = quantidade_carrinho;
	}
}
