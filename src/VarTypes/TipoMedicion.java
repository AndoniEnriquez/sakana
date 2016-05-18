package VarTypes;

public class TipoMedicion {

	int tipoMedicion_id;
	String nombreMedicion;
	
	public TipoMedicion(int tipoMedicion_id, String nombreMedicion){
		this.tipoMedicion_id=tipoMedicion_id;
		this.nombreMedicion=nombreMedicion;
	}

	public int getTipoMedicion_id() {
		return tipoMedicion_id;
	}

	public void setTipoMedicion_id(int tipoMedicion_id) {
		this.tipoMedicion_id = tipoMedicion_id;
	}

	public String getNombreMedicion() {
		return nombreMedicion;
	}

	public void setNombreMedicion(String nombreMedicion) {
		this.nombreMedicion = nombreMedicion;
	}
	
}
