package conexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import varTypes.Dueno;

public class DAODueno {

	static public Dueno buscarPorNombre(String username) {

		Statement stmt;
		ResultSet result;
		String strSQL;
		Dueno d;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT dueno_id, nombreDueno, password" + " FROM DUENO" + " WHERE nombreDueno='" + username + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;
			
			d = new Dueno(result.getInt("dueno_id"), result.getString("nombreDueno"), result.getString("password"));
			result.close();
			return d;
		}

		catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	static public boolean addDueno(Dueno d) {

		Statement stmt;
		String strSQL;
		int result;
		
		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "INSERT INTO DUENO (nombreDueno,password)" + " VALUES ('" + d.getNombreDueno() + "','"
					+ d.getPassword() + "')";
			result = stmt.executeUpdate(strSQL);
			return true;

		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
