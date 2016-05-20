package VarTypes;

public class TipoPez {


	int tipopez_id;
	String descripcion;

	public TipoPez(int tipopez_id, String descripcion){
		this.tipopez_id = tipopez_id;
		this.descripcion = descripcion;
	}

	public int getTipopez_id() {
		return tipopez_id;
	}

	public void setTipopez_id(int tipopez_id) {
		this.tipopez_id = tipopez_id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
