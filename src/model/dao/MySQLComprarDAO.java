package model.dao;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import model.Carrinho;
import model.beans.Pedido;
import model.dao.interfaces.ComprarDAO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MySQLComprarDAO implements ComprarDAO 
{
	// Método que faz a inserção do pedido e também dos produtos na tabela pedido_produto
	@Override
	public boolean save(Carrinho carrinho) 
	{	
		Pedido pedido = new Pedido();
		
		Locale locale = Locale.forLanguageTag("pt-br");
		TimeZone timeZone = TimeZone.getTimeZone("GMT");
		Calendar cal = Calendar.getInstance(timeZone, locale);
		
		pedido.setData_pedido(cal.toString());
		
		return false;
	}

}
