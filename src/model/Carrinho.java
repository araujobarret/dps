package model;
import model.beans.ProdutoCarrinho;
import model.beans.Pedido;
import model.beans.Cliente;
import model.beans.Endereco;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Carrinho {
	
	private List<ProdutoCarrinho> produtos;
	private BigDecimal total;
	private BigDecimal frete;
	private Pedido pedido;
	private Endereco endereco;
	private Cliente cliente;
	
	public Carrinho()
	{
		total = new BigDecimal(0);
		frete = new BigDecimal(0);
		pedido = new Pedido();
		endereco = new Endereco();
		cliente = new Cliente();
		produtos = new ArrayList<ProdutoCarrinho>();
	}
	
	public BigDecimal getTotal()
	{
		return this.total;
	}
	
	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}
	
	public BigDecimal getFrete()
	{
		return this.frete;
	}
	
	public void setFrete(BigDecimal frete)
	{
		this.frete = frete;
	}
	
	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
	}
	
	public Pedido getPedido()
	{
		return this.pedido;
	}
	
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}
	
	public Cliente getCliente()
	{
		return this.cliente;
	}
		
	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}	
	
	public Endereco getEndereco()
	{
		return this.endereco;
	}	
	
	// A cada opera√ß√£o o total do carrinho deve ser atualizado
	private void atualizaTotal()
	{
		double soma = 0;		
		ProdutoCarrinho temp;
		for(int i = 0; i < this.produtos.size(); i++)
		{
			temp = this.produtos.get(i);
			soma += temp.getQuantidade_carrinho() * Double.parseDouble(temp.getPreco_venda().toString());			
		}	

		this.total = new BigDecimal(soma);
	}
	
	// Adiciona Item ao Carrinho
	public void addItem(ProdutoCarrinho produto)
	{
		if(pesquisaItem(produto))
		{
			this.produtos.add(produto);
			this.atualizaTotal();
		}
		
	}
	
	// Remove item do carrinho
	public void removeItem(int indice)
	{
		this.produtos.remove(indice);
		this.atualizaTotal();
	}
	
	// Get do produto espec√≠fico do carrinho
	public ProdutoCarrinho getProduto(int indice)
	{
		return produtos.get(indice);
	}
	
	// Retorna o tamanho da lista 
	public int size()
	{
		return this.produtos.size();
	}
	
	// Altera item do carrinho
	public void alteraItem(int indice, ProdutoCarrinho produto)
	{
		this.produtos.set(indice, produto);
		this.atualizaTotal();
	}
	
	//Confere se o produto j· est· no carrinho
	public boolean pesquisaItem(ProdutoCarrinho produto)
	{
		for(ProdutoCarrinho pc : produtos)
		{
			if(pc.getId() == produto.getId())
			{
				return false;
			}
				
		}
		
		return true;
	}
}
