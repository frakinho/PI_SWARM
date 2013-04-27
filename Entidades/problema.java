package Entidades;

public class problema {
	private int id;
	private int vizinho_CIMA;
	private int vizinho_BAIXO;
	private int vizinho_DIREITA;
	private int vizinho_ESQUERDA;
	
	
	public problema(int id, int vizinho_CIMA, int vizinho_BAIXO,
			int vizinho_DIREITA, int vizinho_ESQUERDA) {
		super();
		this.id = id;
		this.vizinho_CIMA = vizinho_CIMA;
		this.vizinho_BAIXO = vizinho_BAIXO;
		this.vizinho_DIREITA = vizinho_DIREITA;
		this.vizinho_ESQUERDA = vizinho_ESQUERDA;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVizinho_CIMA() {
		return vizinho_CIMA;
	}


	public void setVizinho_CIMA(int vizinho_CIMA) {
		this.vizinho_CIMA = vizinho_CIMA;
	}


	public int getVizinho_BAIXO() {
		return vizinho_BAIXO;
	}


	public void setVizinho_BAIXO(int vizinho_BAIXO) {
		this.vizinho_BAIXO = vizinho_BAIXO;
	}


	public int getVizinho_DIREITA() {
		return vizinho_DIREITA;
	}


	public void setVizinho_DIREITA(int vizinho_DIREITA) {
		this.vizinho_DIREITA = vizinho_DIREITA;
	}


	public int getVizinho_ESQUERDA() {
		return vizinho_ESQUERDA;
	}


	public void setVizinho_ESQUERDA(int vizinho_ESQUERDA) {
		this.vizinho_ESQUERDA = vizinho_ESQUERDA;
	}
	
	
	

}
