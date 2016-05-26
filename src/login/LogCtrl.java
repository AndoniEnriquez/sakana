package login;

import conexionDB.DAODueno;
import varTypes.Dueno;

public class LogCtrl {

	public boolean validarUser(String nombre, String password) {

		Dueno p;

		try {

			p = DAODueno.buscarPorNombre(nombre);

			if ((p == null) || (p.getPassword() == password))
				return false;

			Sesion.getInstance().setUsuario(p);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
