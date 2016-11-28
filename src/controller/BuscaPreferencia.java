package controller;

/*
 * Descrição: Classe controller dos cookies, onde verifica a existencia de cookies e com base nas visitas das páginas dos produtos e categorias
 * grava as informações em sessão para que sejam usadas para uma consulta mais customizada para o usuário, com base em suas 
 * últimas visitas.
 * O máximo de produtos para que sejam reexibidos até 4 e o máximo de categoria até 1.
 * Após listar estes é exibida a lista de produtos normal para o usuário.
 * Autor: Carlos
 */

import model.Voto;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BuscaPreferencia {
	private HttpSession sessao;
	private ArrayList<Voto>votoProduto;
	private ArrayList<Voto>votoCategoria;
	
	// Construtor para quando se recebe um voto, nesse caso após a leitura da variável de sessao será necessário
	// apagar todos para poder atualizar o valor e gravar novamente
	public BuscaPreferencia(HttpServletRequest requestJsp) {		
		sessao = requestJsp.getSession();		
		votoProduto = new ArrayList<Voto>();
		votoCategoria = new ArrayList<Voto>();
		/*sessao.setAttribute("categoria0", null);
		sessao.setAttribute("votoCategoria0", null);
		sessao.setAttribute("categoria1", null);
		sessao.setAttribute("votoCategoria1", null);*/
		this.lerVariaveis();		
	}
	
	private void lerVariaveis(){
		Voto voto;		
		for(int i = 0; i < 4; i++)
			if(sessao.getAttribute("produto" + i) != null)
				if(sessao.getAttribute("votoProduto"+i) != null){
					voto = new Voto();
					voto.setId(Integer.parseInt(sessao.getAttribute("produto" + i).toString()));
					voto.setVoto(Integer.parseInt(sessao.getAttribute("votoProduto" + i).toString()));					
					votoProduto.add(voto);
				}
		
		for(int i = 0; i < 2; i++)
			if(sessao.getAttribute("categoria" + i) != null)
				if(sessao.getAttribute("votoCategoria"+i) != null){
					voto = new Voto();
					voto.setId(Integer.parseInt(sessao.getAttribute("categoria" + i).toString()));
					voto.setVoto(Integer.parseInt(sessao.getAttribute("votoCategoria" + i).toString()));
					votoCategoria.add(voto);
				}		
		
	}	
	/*
	public void gravarProduto(){
		int voto[] = new int[2];
		for(int i = 0; i < votoProduto.size(); i++){
			voto = votoProduto.get(i);
			sessao.setAttribute("produto" + i, voto[0]);
			sessao.setAttribute("votoProduto" + i, voto[1]);
		}
	}
	*/
	public void gravarCategoria(){
		Voto voto;
		for(int i = 0; i < votoCategoria.size(); i++){
			voto = new Voto();
			voto = votoCategoria.get(i);
			sessao.setAttribute("categoria" + i, voto.getId());			
			sessao.setAttribute("votoCategoria"+i, voto.getVoto());
			System.out.println("72 id: " + sessao.getAttribute("categoria"+i)+ " - voto: " + sessao.getAttribute("votoCategoria" + i));
		}
	}
	/*
	public void votarProduto(int id, int categoriaId) {
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
		
		//Após tudo grava a categoria do produto e dentro do método votarCategoria aciona a gravação do cookie
		this.votarCategoria(categoriaId);
	}
	*/
	public void votarCategoria(int id)	{
		Voto voto, voto2, temp;
		ArrayList<Voto>votos = new ArrayList<Voto>();	
		
		System.out.println("Size: " + votoCategoria.size());
		//Checa se já existe alguma categoria votada na classe
		if(votoCategoria.size() != 0){
			// Checa se a categoria em segundo lugar já existe, se não existir e o voto for diferente do primeiro id, 
			// insere-se na segunda coluna
			if(votoCategoria.size() > 1) {
				voto = new Voto();
				voto = votoCategoria.get(0);
				votos.add(0, voto);
				voto = new Voto();
				voto = votoCategoria.get(1);
				votos.add(1, voto);
				// Checa se o voto é para algum id já existente no array
				// o primeiro elemento é sempre o que será gravado na sessão, após alguma alteração na votação
				// é necessário recalcular para ver quem fica em primeiro
				if(id == votos.get(0).getId()){								
					votos.get(0).setVoto(votos.get(0).getVoto()+1);
				}
				else 
					if(id == votos.get(1).getId()){						
						votos.get(1).setVoto(votos.get(1).getVoto()+1);
						// Como o segundo elemento recebeu voto faz-se necessário verificar se ainda é o mais votado, se não troca-se o elemento
						if(votos.get(0).getVoto() < votos.get(1).getVoto()){
							// Swap
							voto = new Voto();
							voto = votos.get(0);		
							voto2 = votos.get(1);							
							votos.set(0, voto2);
							votos.set(1, voto);
							System.out.println("id1: " + votos.get(0).getId() + 
									"id2: " + votos.get(1).getId());
						}						
					}
				// Grava os dados no ArrayList
				votoCategoria.set(0, votos.get(0));
				votoCategoria.set(1, votos.get(1));	
				gravarCategoria();
				
			}
			else {				
				// Incrementa o voto se for para o id em primeiro
				if(id == votoCategoria.get(0).getId()) {
					System.out.println("175");
					voto = new Voto();
					voto.setId(votoCategoria.get(0).getId());
					voto.setVoto(votoCategoria.get(0).getVoto()+1);
					votoCategoria.set(0, voto);
					gravarCategoria();
				}
				else{
					// Cria o segundo elemento com o voto 1					
					voto = new Voto(id, 1);
					votoCategoria.add(voto);
					gravarCategoria();
				}		
			}	
		}
		else {
			// Caso seja o primeiro voto
			voto = new Voto(id, 1);
			votoCategoria.add(voto);
			gravarCategoria();
		}
	}
	
	// Verifica se tem algum produto no arraylist
	public boolean hasProduto() {
		return (votoProduto.size() > 0);
	}
	
	// Verifica se tem alguma categoria no arraylist
	public boolean hasCategoria() {
		return (votoCategoria.size() > 0);
	}
	
	// Getters e Setters
	
	public ArrayList<Voto> getVotoProduto() {
		return votoProduto;
	}

	public ArrayList<Voto> getVotoCategoria() {
		return votoCategoria;
	}
		
}
