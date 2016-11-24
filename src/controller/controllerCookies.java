package controller;

/*
 * Descri��o: Classe controller dos cookies, onde verifica a existencia de cookies e com base nas visitas das p�ginas dos produtos e categorias
 * grava as informa��es nos cookies para que sejam usadas para uma consulta mais customizada para o usu�rio, com base em suas 
 * �ltimas visitas.
 * O m�ximo de produtos para que sejam reexibidos � 4 e o m�ximo de categoria � 1 por cookie.
 * Ap�s listar estes � exibida a lista de produtos normal para o usu�rio.
 * Autor: Carlos
 */

import java.util.ArrayList;
import javax.servlet.http.Cookie;


public class controllerCookies {
	private ArrayList<Cookie>cookies;
	private ArrayList<int[]>votoProduto;
	private ArrayList<int[]>votoCategoria;
	private static final int EXP_TIME = 60*60;	
	
	public controllerCookies() {
		votoProduto = new ArrayList<int[]>();
		votoCategoria = new ArrayList<int[]>();
		
		// Falta implementar a verifica��o de cookies j� existentes e preencher as vari�veis com eles
		
	}
	
	/// M�todo interno para gravar os cookies a cada opera��o
	private boolean gravarCookies() {
		Cookie produto, categoria;
		int i, votos[] = new int[2];
		
		for(i = 0; i < votoProduto.size(); i++) {
			votos = votoProduto.get(i);			
			produto = new Cookie("id_produto" + i, String.valueOf(votos[1]));
			produto.setMaxAge(60*60);
		}
		
		return false;
	}
	
	public void votarProduto(int id) {
		int i = 0, j, idVoto[][] = new int[5][2], temp[];
		boolean flag;
		
		// Verifica situa��o do array, at� onde foi preenchido
		while(i < votoProduto.size()){
			idVoto[i] = votoProduto.get(i);
			i++;
		}
		
		// Zerar o array tempor�rio
		for(i = 0; i < 5; i++) {
			idVoto[i][0] = 0;
			idVoto[i][1] = 0;
		}
		
		// Verifica se existem produtos dispon�veis ou se � um voto para um produto j� votado
		for(j = 0; j < votoProduto.size(); j++) {
			if(idVoto[j][0] != 0) {
				if(id == idVoto[i][0])
				{
					idVoto[i][1]++;
					flag = true;
					break;
				}				
			}
			else {
				idVoto[j][0] = id;
				idVoto[j][1] = 1;
				break;
			}				
		}
		
		// Ordena��o do vetor pelo bubbleSort
		do {
			flag = false;
			for(i = 0; i < 4; i++) {
				if(idVoto[i][1] > idVoto[i+1][1]) {
					temp = idVoto[i];
					idVoto[i] = idVoto[i+1];
					idVoto[i+1] = temp;
					flag = true;
				}
			}				
		} while(flag);
		
		// Grava os dados no arrayList
		for(i = 0; i < votoProduto.size(); i++)
			if(idVoto[i][0] != 0)
				votoProduto.add(i, idVoto[i]);
			else
				break;
		
		//Ap�s tudo grava o arrayList no cookie
		gravarCookies();
	}
	
	public void votarCategoria(int id)	{
		int idVoto[][] = new int [3][2];		
		
		//Checa se j� existe alguma categoria votada na classe
		if(votoCategoria.size() != 0){
			// Checa se a categoria em segundo lugar j� existe, se n�o existir e o voto for diferente do primeiro id, 
			// insere-se na segunda coluna
			if(votoCategoria.size() > 1) {
				idVoto[0] = votoCategoria.get(0);
				idVoto[1] = votoCategoria.get(1);
				
				// Checa se o voto � para algum id j� existente no array
				// o primeiro elemento � sempre o que ser� gravado no cookie, ap�s alguma altera��o na vota��o
				// � necess�rio recalcular para ver quem fica em primeiro
				if(id == idVoto[0][0]){								
					idVoto[0][1]++;		
				}
				else 
					if(id == idVoto[1][0]){						
						idVoto[1][1]++;
						// Como o segundo elemento recebeu voto faz-se necess�rio verificar se ainda � o mais votado, se n�o troca-se o elemento
						if(idVoto[0][1] < idVoto[1][1]){
							// Swap
							idVoto[2] = idVoto[0];
							idVoto[0] = idVoto[1];
							idVoto[1] = idVoto[2];
						}						
					}
				// Grava os dados no ArrayList
				votoCategoria.set(0, idVoto[0]);
				votoCategoria.set(1, idVoto[1]);			
				
			}
			else {
				idVoto[0] = votoCategoria.get(0);
				// Incrementa o voto se for para o id em primeiro
				if(id == idVoto[0][0]) {
					idVoto[0][1]++;
					votoCategoria.set(0, idVoto[0]);
				}
				else{
					// Cria o segundo elemento com o voto 1
					idVoto[1][0] = id;
					idVoto[1][1] = 1;
					votoCategoria.add(idVoto[1]);
				}					
			}	
			// Ap�s todos os procedimentos grava as opera��es nos cookies
			gravarCookies();
		}
	}
	
}
