package config;

public class config{
	private double velocidade;
	private int nrParticle;
	private int tolerancia;
	private int width = 400;
	private int heigth = 400;
	
	public config() {
		
	}
	
	public config(double velocidade, int nrParticle, int tolerancia) {
		super();
		this.velocidade = velocidade;
		this.nrParticle = nrParticle;
		this.tolerancia = tolerancia;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public int getNrParticle() {
		return nrParticle;
	}

	public void setNrParticle(int nrParticle) {
		this.nrParticle = nrParticle;
	}

	public int getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(int tolerancia) {
		this.tolerancia = tolerancia;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	
}
