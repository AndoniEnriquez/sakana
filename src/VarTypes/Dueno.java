package VarTypes;

public class Dueno {


	int dueno_id;
	String nombreDueno, password;

	public Dueno(int dueno_id, String nombreDueno, String password){
		this.dueno_id = dueno_id;
		this.nombreDueno = nombreDueno;
		this.password = password;
	}

	public int getDueno_id() {
		return dueno_id;
	}

	public void setDueno_id(int dueno_id) {
		this.dueno_id = dueno_id;
	}

	public String getNombreDueno() {
		return nombreDueno;
	}

	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
