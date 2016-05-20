package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import VarTypes.*;

public class DAODueno {

	static public Dueno buscarPorNombre(String username) throws Exception {

		Statement stmt;
		ResultSet result;
		String strSQL;
		Dueno d;

		try {
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT dueno_id, nombreDueno, password"+
					" FROM DUENO"+
					" WHERE nombreDueno='"+username+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			d = new Dueno(result.getString("nombreDueno"),
					result.getString("password"));
			result.close();
			return d;
		}

		catch(SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

}
