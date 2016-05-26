package Login;

import VarTypes.Dueno;

public class Sesion {

	protected Dueno p;

	private Sesion() {
	}

	private static Sesion instance;

	public static Sesion getInstance() {

		if (instance == null)

			instance = new Sesion();
		return instance;

	}

	public void setUsuario(Dueno p) {
		this.p = p;
	}

	public Dueno getUsuario() {
		return p;
	}

}
