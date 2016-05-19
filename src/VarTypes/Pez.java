package VarTypes;

public class Pez {
	
	int pez_id, tipoPez_id, dueno_id, pecera_id;
	String nombrePez, genero;

	
	public Pez(int pez_id, String nombrePez, String genero, int tipoPez_id, int dueno_id, int pecera_id){
		this.pez_id = pez_id;
		this.nombrePez = nombrePez;
		this.genero = genero;
		this.tipoPez_id = tipoPez_id;
		this.dueno_id = dueno_id;
		this.pecera_id = pecera_id;
	}


	public int getPez_id() {
		return pez_id;
	}


	public void setPez_id(int pez_id) {
		this.pez_id = pez_id;
	}


	public int getTipoPez_id() {
		return tipoPez_id;
	}


	public void setTipoPez_id(int tipoPez_id) {
		this.tipoPez_id = tipoPez_id;
	}


	public int getDueno_id() {
		return dueno_id;
	}


	public void setDueno_id(int dueno_id) {
		this.dueno_id = dueno_id;
	}


	public int getPecera_id() {
		return pecera_id;
	}


	public void setPecera_id(int pecera_id) {
		this.pecera_id = pecera_id;
	}


	public String getNombrePez() {
		return nombrePez;
	}


	public void setNombrePez(String nombrePez) {
		this.nombrePez = nombrePez;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
