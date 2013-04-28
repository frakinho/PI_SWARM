package Entidades;

public class particle {
	private int id;
	private int x;
	private int y;
	private int type; //0 normal - 1 casa - 2 flores  
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

	@Override
	public String toString() {
		return "particle [id=" + id + ", x=" + x + ", y=" + y + ", type="
				+ type + ", image=" + image + ", distCentro=" + distCentro
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + type;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		particle other = (particle) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
	
}
