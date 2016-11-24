package controller;

/*
 * Descrição: Classe controller dos cookies, onde verifica a existencia de cookies e com base nas visitas das páginas dos produtos e categorias
 * grava as informações nos cookies para que sejam usadas para uma consulta mais customizada para o usuário, com base em suas 
 * últimas visitas.
 * O máximo de produtos para que sejam reexibidos é 4 e o máximo de categoria é 1 por cookie.
 * Após listar estes é exibida a lista de produtos normal para o usuário.
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
		
		// Falta implementar a verificação de cookies já existentes e preencher as variáveis com eles
		
	}
	
	/// Método interno para gravar os cookies a cada operação
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
		
		// Verifica situação do array, até onde foi preenchido
		while(i < votoProduto.size()){
			idVoto[i] = votoProduto.get(i);
			i++;
		}
		
		// Zerar o array temporário
		for(i = 0; i < 5; i++) {
			idVoto[i][0] = 0;
			idVoto[i][1] = 0;
		}
		
		// Verifica se existem produtos disponíveis ou se é um voto para um produto já votado
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
		
		// Ordenação do vetor pelo bubbleSort
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
		
		//Após tudo grava o arrayList no cookie
		gravarCookies();
	}
	
	public void votarCategoria(int id)	{
		int idVoto[][] = new int [3][2];		
		
		//Checa se já existe alguma categoria votada na classe
		if(votoCategoria.size() != 0){
			// Checa se a categoria em segundo lugar já existe, se não existir e o voto for diferente do primeiro id, 
			// insere-se na segunda coluna
			if(votoCategoria.size() > 1) {
				idVoto[0] = votoCategoria.get(0);
				idVoto[1] = votoCategoria.get(1);
				
				// Checa se o voto é para algum id já existente no array
				// o primeiro elemento é sempre o que será gravado no cookie, após alguma alteração na votação
				// é necessário recalcular para ver quem fica em primeiro
				if(id == idVoto[0][0]){								
					idVoto[0][1]++;		
				}
				else 
					if(id == idVoto[1][0]){						
						idVoto[1][1]++;
						// Como o segundo elemento recebeu voto faz-se necessário verificar se ainda é o mais votado, se não troca-se o elemento
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
			// Após todos os procedimentos grava as operações nos cookies
			gravarCookies();
		}
	}
	
}
