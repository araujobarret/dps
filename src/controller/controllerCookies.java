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
import javax.servlet.http.HttpServletResponse;


public class controllerCookies {
	private ArrayList<Cookie>cookies;
	private ArrayList<int[]>votoProduto;
	private ArrayList<int[]>votoCategoria;
	
	
	// Construtor para somente ler os cookies e deixar os arraylist's prontos para sua extra��o
	public controllerCookies(Cookie[] requestCookies)
	{
		cookies = new ArrayList<Cookie>();
		votoProduto = new ArrayList<int[]>();
		votoCategoria = new ArrayList<int[]>();
		this.lerCookies(requestCookies);
	}
	
	// Construtor para quando se recebe um voto, nesse caso ap�s a leitura do cookie ser� necess�rio
	// apagar todos para poder atualizar o valor e gravar novamente
	public controllerCookies(Cookie[] requestCookies, HttpServletResponse responseJsp) {
		cookies = new ArrayList<Cookie>();
		votoProduto = new ArrayList<int[]>();
		votoCategoria = new ArrayList<int[]>();

		this.lerCookies(requestCookies);
		
		// Ap�s trazer os dados para a mem�ria limpa os cookies para a atualiza�o caso for necess�rio
		this.limparCookies(requestCookies, responseJsp);
	}
	
	private void lerCookies(Cookie[] requestCookies){
		int voto[][] = new int[6][2];
		Cookie cookie;
		int sliceProduto, sliceCategoria;
		
		// Zerar o array tempor�rio dos votos
		for(int i = 0; i < 6; i++){
			voto[i][0] = 0;
			voto[i][1] = 0;
		}
		
		// Falta implementar a verifica��o de cookies j� existentes e preencher as vari�veis com eles
		// Ler os cookies de produtos se existirem
		if(requestCookies != null){
			for(int i = 0; i < requestCookies.length; i++){
				cookie = requestCookies[i];
				if(cookie.getDomain() != null) {
					if(cookie.getDomain().equals("localhost.lojaufscar")){
						// Se o cookie for relacionado com o produto
						if(cookie.getName().startsWith("produto")){
							// Pega o final do nome do cookie com o n�mero associado a ele
							sliceProduto = Integer.parseInt(cookie.getName().substring(cookie.getName().length()-1));
							if(cookie.getName().contains("voto")) {
								voto[sliceProduto][1] = Integer.parseInt(cookie.getValue());
							}
							else{
								voto[sliceProduto][0] = Integer.parseInt(cookie.getValue());
							}					
						}
						else {
							// Considera cookie de voto da categoria
							if(cookie.getName().equals("categoria"))
								voto[4][0] = Integer.parseInt(cookie.getValue());
							else{
								voto[4][1] = Integer.parseInt(cookie.getValue());
							}						
						}					
					}
				}
			}
			
			// Escrever os votos nos arrayList dos produtos
			for(int i = 0; i < 4; i++) {
				if(voto[i][0] != 0) {
					votoProduto.add(voto[i]);
				}					
			}
			
			// Escreve o voto do arraylist da categoria
			if(voto[4][0] != 0)
				votoCategoria.add(voto[4]);
		}
	}
	
	// M�todo que limpa os cookies existentes previamente para poder atualizar seus valores
	// Este m�todo s� ser� executado depois da leitura dos cookies a atribui��o para os Arrays espec�ficos
	private boolean limparCookies(Cookie[] requestCookies, HttpServletResponse responseJsp){
		Cookie cookie;		
		if(requestCookies != null)
		{
			for(int i = 0; i < requestCookies.length; i++) {
				cookie = requestCookies[i];
				cookie.setMaxAge(0);
				responseJsp.addCookie(cookie);
			}
			return true;
		}
		else
			return false;
	}
	
	// M�todo interno para gravar os cookies a cada opera��o
	private boolean gravarCookies(HttpServletResponse responseJsp) {
		Cookie produto, categoria, cookie;
		int i, votos[] = new int[2];
		
		try{				
			for(i = 0; i < votoProduto.size(); i++) {
				votos = votoProduto.get(i);			
				produto = new Cookie("produto" + i, String.valueOf(votos[0]));
				produto.setDomain("localhost.lojaufscar");
				produto.setMaxAge(60*60);
				// Grava o cookie com o valor de sua ID
				cookies.add(produto);
				produto = new Cookie("produtoVoto" + i, String.valueOf(votos[1]));
				produto.setDomain("localhost.lojaufscar");
				produto.setMaxAge(60*60);
				// Grava o cookie com o numero de votos referente a gravada ID anteriormente
				cookies.add(produto);
			}
			for(i = 0; i < votoCategoria.size(); i++) {				
				votos = votoCategoria.get(i);
				categoria = new Cookie("categoria" + i, String.valueOf(votos[0]));
				categoria.setDomain("localhost.lojaufscar");
				categoria.setMaxAge(60*60);				
				// Grava o cookie com a id da categoria
				cookies.add(categoria);
				categoria = new Cookie("categoriaVoto" + i, String.valueOf(votos[1]));
				categoria.setDomain("localhost.lojaufscar");				
				categoria.setMaxAge(60*60);
				// Grava o n�mero de votos para a categoria espec�fica
				cookies.add(categoria);
			}
			
			//Array para preencher o array de cookies
			for(i = 0; i < cookies.size();i++) {
				cookie = cookies.get(i);				
				responseJsp.addCookie(cookie);
			}
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Cookie> getCookies() {
		return cookies;
	}

	public void votarProduto(int id, int categoriaId, HttpServletResponse responseJsp) {
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
		
		//Ap�s tudo grava a categoria do produto e dentro do m�todo votarCategoria aciona a grava��o do cookie
		this.votarCategoria(categoriaId, responseJsp);
	}
	
	public void votarCategoria(int id, HttpServletResponse responseJsp)	{
		int idVoto[][] = new int [3][2];		

		//Checa se j� existe alguma categoria votada na classe
		if(votoCategoria.size() != 0){
			System.out.println("216 - size:" + votoCategoria.size());
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
			gravarCookies(responseJsp);
		}
		else {
			// Caso seja o primeiro voto
			idVoto[0][0] = id;
			idVoto[0][1] = 1;
			votoCategoria.add(idVoto[0]);
			gravarCookies(responseJsp);
		}
	}
	
	// Getters e Setters
	
	public ArrayList<int[]> getVotoProduto() {
		return votoProduto;
	}

	public ArrayList<int[]> getVotoCategoria() {
		return votoCategoria;
	}
		
}
