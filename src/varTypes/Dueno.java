package varTypes;

public class Dueno {

	int id;
	String nombreDueno, password;

	public Dueno(String nombreDueno, String password) {

		this.nombreDueno = nombreDueno;
		this.password = password;
	}

	public Dueno(int id, String nombreDueno, String password) {

		this.id = id;
		this.nombreDueno = nombreDueno;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
