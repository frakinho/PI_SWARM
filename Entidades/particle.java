package Entidades;

public class particle {
	private int id;
	private int x;
	private int y;
	private int type; //0 normal - 1 casa - 2  
	private String image;
	private double distCentro;
	
	public particle(int x,int y,int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public particle(int x, int y, int type, String image,double distCentro) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.image = image;
		this.distCentro = distCentro;
	}
	
	//Com todos os dados da BD
	public particle(int id, int x, int y, int type, String image,double distCentro) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		this.image = image;
		this.distCentro = distCentro;
	}

	//GET AND SET
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getDistCentro() {
		return distCentro;
	}

	public void setDistCentro(double distCentro) {
		this.distCentro = distCentro;
	}
	
	
	
	
	
}
