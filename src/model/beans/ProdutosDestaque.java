package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JavaBean que representa a tabela ProdutosDestaque do BD
 * no sistema Loja UFSCar
 * 
 * @author Carlos
**/

@Entity
@Table(name="produtos_destaque")
public class ProdutosDestaque {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_produto1")
	private int id_produto1;	

	@Column(name="id_produto2")
	private int id_produto2;
	
	@Column(name="id_produto3")
	private int id_produto3;
	
	@Column(name="id_produto4")
	private int id_produto4;
	
	public ProdutosDestaque(){}
	
	public ProdutosDestaque(int id, int produto1, int produto2, int produto3, int produto4){
		this.id = id;
		this.id_produto1 = produto1;
		this.id_produto2 = produto2;
		this.id_produto3 = produto3;
		this.id_produto4 = produto4;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_produto1() {
		return id_produto1;
	}

	public void setId_produto1(int id_produto1) {
		this.id_produto1 = id_produto1;
	}

	public int getId_produto2() {
		return id_produto2;
	}

	public void setId_produto2(int id_produto2) {
		this.id_produto2 = id_produto2;
	}

	public int getId_produto3() {
		return id_produto3;
	}

	public void setId_produto3(int id_produto3) {
		this.id_produto3 = id_produto3;
	}

	public int getId_produto4() {
		return id_produto4;
	}

	public void setId_produto4(int id_produto4) {
		this.id_produto4 = id_produto4;
	}	
}
