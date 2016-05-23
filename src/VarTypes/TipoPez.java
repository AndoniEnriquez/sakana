package VarTypes;

public class TipoPez {


	int tipopez_id;
	String descripcion;
	float phMin, phMax, temMin, temMax;

	public TipoPez(int tipopez_id, String descripcion, float phMin, float phMax, float temMin, float temMax){
		this.tipopez_id = tipopez_id;
		this.descripcion = descripcion;
		this.phMin = phMin;
		this.phMax = phMax;
		this.temMin = temMin;
		this.temMax = temMax;
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

	public float getPhMin() {
		return phMin;
	}

	public void setPhMin(float phMin) {
		this.phMin = phMin;
	}

	public float getPhMax() {
		return phMax;
	}

	public void setPhMax(float phMax) {
		this.phMax = phMax;
	}

	public float getTemMin() {
		return temMin;
	}

	public void setTemMin(float temMin) {
		this.temMin = temMin;
	}

	public float getTemMax() {
		return temMax;
	}

	public void setTemMax(float temMax) {
		this.temMax = temMax;
	}

}
