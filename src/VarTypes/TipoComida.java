package VarTypes;

public class TipoComida {

	int tipocomida_id;
	String descripcionTipoComida;

	public TipoComida(int tipocomida_id, String descripcionTipoComida){
		this.tipocomida_id = tipocomida_id;
		this.descripcionTipoComida = descripcionTipoComida;
	}

	public int getTipocomida_id() {
		return tipocomida_id;
	}

	public void setTipocomida_id(int tipocomida_id) {
		this.tipocomida_id = tipocomida_id;
	}

	public String getDescripcionTipoComida() {
		return descripcionTipoComida;
	}

	public void setDescripcionTipoComida(String descripcionTipoComida) {
		this.descripcionTipoComida = descripcionTipoComida;
	}
}
