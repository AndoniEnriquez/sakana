package VarTypes;

public class Comida {

	int comida_id, tipocomida_id;
	String nombreComida, descripcionComida;
	
	public Comida(int comida_id, String nombreComida, String descripcionComida, int tipocomida_id){
		this.comida_id = comida_id;
		this.nombreComida=nombreComida;
		this.descripcionComida=descripcionComida;
		this.tipocomida_id=tipocomida_id;
	}

	public int getComida_id() {
		return comida_id;
	}

	public void setComida_id(int comida_id) {
		this.comida_id = comida_id;
	}

	public int getTipocomida_id() {
		return tipocomida_id;
	}

	public void setTipocomida_id(int tipocomida_id) {
		this.tipocomida_id = tipocomida_id;
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
