package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrinho;
import model.beans.Endereco;
import model.beans.FormaPagamento;
import model.beans.Pedido;
import model.beans.PedidoProduto;
import model.beans.Produto;
import model.beans.ProdutoCarrinho;
import model.dao.MySQLLojaUfscarDAOFactory;
import model.dao.interfaces.PedidoDAO;
import model.dao.interfaces.PedidoProdutoDAO;


/**
 * Servlet implementation class ServletComprar
 */
@WebServlet("/ServletComprar")
public class ServletComprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComprar() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{

        response.setContentType("text/html;charset=UTF-8");
        Pedido pedido = new Pedido();
        Carrinho carrinho;
        try
        {
        	PedidoDAO pedidoDAO;
        	PedidoProdutoDAO pedidoProdutoDAO;        	
        	Produto produto;
        	ProdutoCarrinho produtoCarrinho;
        	PedidoProduto temp;
        	Endereco endereco = new Endereco();
        	FormaPagamento forma = new FormaPagamento();
        	
        	double total;
        	
        	endereco.setId(Integer.parseInt(request.getParameter("endereco")));
        	forma.setId(Integer.parseInt(request.getParameter("forma_pagamento")));
        	
        	HttpSession sessao = request.getSession();
        	String msg;
        	if(sessao.getAttribute("carrinho") == null)
        		response.sendRedirect("index.jsp");
        	else
        	{
        		carrinho = (Carrinho)sessao.getAttribute("carrinho");
        		        		
        		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        		Date data = new Date();
        		
        		pedido.setCliente_cpf(carrinho.getCliente());
        		pedido.setData_pedido(formato.format(data));
        		pedido.setFrete(carrinho.getFrete());
        		pedido.setTotal(carrinho.getTotal());
        		pedido.setEndereco_entrega_id(endereco);
        		pedido.setForma_pagameto_id(forma);
        		
        		if(sessao.getAttribute("cpf") == null)
        		{
        			msg = "Para efetuar a compra é necessário efetuar o login.";
        			response.sendRedirect("login.jsp?comprar=1&mensagem=" + msg);
        		}
        		else
        		{
        			pedidoDAO = MySQLLojaUfscarDAOFactory.getPedidoDAO();
        			pedido = pedidoDAO.save(pedido);
        			
        			for(int i = 0; i < carrinho.size(); i++)
        			{        				
        				produto = new Produto();
        				temp = new PedidoProduto();        				
        				produtoCarrinho = carrinho.getProduto(i);       				
       				     				        				
        				temp.setPedido_id(pedido);
        				temp.setValor_unitario(produtoCarrinho.getPreco_venda());        				
        				      				      				
        				temp.setQuantidade(produtoCarrinho.getQuantidade_carrinho());
        				total = Double.parseDouble(produtoCarrinho.getPreco_venda().toString()) * produtoCarrinho.getQuantidade_carrinho();
        				temp.setValor_total(new BigDecimal(total));
        				//temp.setValor_unitario(produto.getPreco_venda());
        				        				
        				pedidoProdutoDAO = MySQLLojaUfscarDAOFactory.getPedidoProdutoDAO();
        				produto.setId(produtoCarrinho.getId());
        				temp.setProduto_id(produto);
        				//System.out.println("ID " + produto.getId());
        				pedidoProdutoDAO.save(temp);
        				
        			}
        			
        			msg = "Compra efetuada, aguardando confirmacao de pagamento.";
        			response.sendRedirect("index.jsp?mensagem=" + msg);
        		}
        	}	
        }
        catch(Exception e)
        {
        	
        }
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
