package conexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import varTypes.TipoMedicion;

public class DAOTipoMedicion {

	static public ArrayList<TipoMedicion> getTiposMedicion() {

		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<TipoMedicion> lista = null;
		TipoMedicion tipoMedicion;
		
		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT tipomedicion_id, nombreMedicion" + " FROM tipomedicion";
			result = stmt.executeQuery(strSQL);
		
			while (result.next()) {
				tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"), result.getString("nombreMedicion"));
				lista.add(tipoMedicion);
			}
			
			result.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	static public TipoMedicion buscarPorID(int idTipoMedicion) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoMedicion tipoMedicion;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT tipomedicion_id, nombreMedicion" + " FROM tipomedicion" + " WHERE tipomedicion_id=" + idTipoMedicion;
			result = stmt.executeQuery(strSQL);
		
			if (!result.next()) return null;
			tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"), result.getString("nombreMedicion"));
			result.close();
			
			return tipoMedicion;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	static public TipoMedicion buscarPorNombre(String nombre) {
	
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoMedicion tipoMedicion;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT tipomedicion_id, nombreMedicion" + " FROM tipomedicion" + " WHERE nombreMedicion='" + nombre + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next())	return null;
			
			tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"), result.getString("nombreMedicion"));
			result.close();
			
			return tipoMedicion;
			
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	static public boolean addTipoMedicion(TipoMedicion m) {

		Statement stmt;
		String strSQL;
		int result;

		try {
			
			if (buscarPorID(m.getTipoMedicion_id()) == null && buscarPorNombre(m.getNombreMedicion()) == null) {

				stmt = PoolConexiones.getConexion().createStatement();
				strSQL = "INSERT INTO TIPOMEDICION " + " VALUES (" + m.getTipoMedicion_id() + ",'"
						+ m.getNombreMedicion() + ")";
				result = stmt.executeUpdate(strSQL);
				return true;
			
			} else
				return false;
		}

		catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unused")
	public static boolean eliminarTipoMedicion(String nombre) {
		
		Statement stmt;
		int result;
		String strSQL;
		
		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "DELETE FROM  TIPOMEDICION " + " WHERE nombre = '" + nombre + "'";
			result = stmt.executeUpdate(strSQL);
			return true;

		} catch (SQLException e) {
			
			return false;
		}

	}

	@SuppressWarnings("unused")
	static public boolean updatePecera(TipoMedicion m) {
		
		Statement stmt;
		boolean ok = false;
		String strSQL;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "UPDATE TIPOMEDICION " + " SET nombreMedicion = '" + m.getNombreMedicion()
					+ "' WHERE tipomedicion_id=" + m.getTipoMedicion_id();
			return (stmt.executeUpdate(strSQL) > 0);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
}
