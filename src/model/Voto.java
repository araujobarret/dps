package model;

public class Voto {
	int id, voto;

	public Voto(){
		
	}
	
	public Voto(int id, int voto){
		this.id =  id;
		this.voto = voto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
	

}
