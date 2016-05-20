package VarTypes;

public class Dueno {


	String nombreDueno, password;

	public Dueno(String nombreDueno, String password){

		this.nombreDueno = nombreDueno;
		this.password = password;
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
