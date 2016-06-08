package varTypes;

public class Comida {

	int comida_id;
	String nombreComida, descripcionComida;

	public Comida(String nombreComida, String descripcionComida) {

		this.nombreComida = nombreComida;
		this.descripcionComida = descripcionComida;
	}

	public int getComida_id() {
		return comida_id;
	}

	public void setComida_id(int comida_id) {
		this.comida_id = comida_id;
	}

	public String getNombreComida() {
		return nombreComida;
	}

	public void setNombreComida(String nombreComida) {
		this.nombreComida = nombreComida;
	}

	public String getDescripcionComida() {
		return descripcionComida;
	}

	public void setDescripcionComida(String descripcionComida) {
		this.descripcionComida = descripcionComida;
	}

}
