package conexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import varTypes.TipoPez;

public class DAOTipoPez {
	
	static public ArrayList<TipoPez> getTiposPez() {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<TipoPez> lista = null;
		TipoPez TipoPez;
		
		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT TipoPez_id, descripcion, phMin, phMax, tempMin, tempMax"
					+ " FROM TipoPez order by tipoPez_id";
			result = stmt.executeQuery(strSQL);
			
			while (result.next()) {
				TipoPez = new TipoPez(result.getInt("TipoPez_id"), result.getString("descripcion"),
						result.getFloat("phMin"), result.getFloat("phMax"), result.getFloat("tempMin"),
						result.getFloat("tempMax"));
				lista.add(TipoPez);
			}
			
			result.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	static public TipoPez buscarPorID(int idTipoPez) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoPez TipoPez;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT TipoPez_id, descripcion, phMin, phMax, tempMin, tempMax" + " FROM TipoPez"
					+ " WHERE TipoPez_id='" + idTipoPez + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;
			
			TipoPez = new TipoPez(result.getInt("TipoPez_id"), result.getString("descripcion"),
					result.getFloat("phMin"), result.getFloat("phMax"), result.getFloat("tempMin"),
					result.getFloat("tempMax"));
			result.close();
			return TipoPez;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	static public TipoPez buscarPorDescripcion(String descripcion) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoPez TipoPez;

		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT TipoPez_id, descripcion, phMin, phMax, tempMin, tempMax" + " FROM TipoPez"
					+ " WHERE descripcion='" + descripcion + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next())	return null;
			
			TipoPez = new TipoPez(result.getInt("TipoPez_id"), result.getString("descripcion"),
					result.getFloat("phMin"), result.getFloat("phMax"), result.getFloat("tempMin"),
					result.getFloat("tempMax"));
			result.close();
			return TipoPez;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	static public boolean addTipoPez(TipoPez m) {

		Statement stmt;
		String strSQL;
		int result;

		try {
			if (buscarPorID(m.getTipopez_id()) == null && buscarPorDescripcion(m.getDescripcion()) == null) {

				stmt = PoolConexiones.getConexion().createStatement();
				strSQL = "INSERT INTO TipoPez (descripcion, phMin, phMax, tempMin, tempMax)" + " VALUES ('"
						+ m.getDescripcion() + "', " + m.getPhMin() + "," + m.getPhMax() + "," + m.getTemMin() + ","
						+ m.getTemMax() + ")";
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
	public static boolean eliminarTipoPez(String descripcion) {
		
		Statement stmt;
		int result;
		String strSQL;
	
		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "DELETE FROM  TipoPez " + " WHERE nombre = '" + descripcion + "'";
			result = stmt.executeUpdate(strSQL);
			return true;
			
		} catch (SQLException e) {
			
			return false;
		}

	}

	@SuppressWarnings("unused")
	static public boolean updatePecera(TipoPez m) {
		
		Statement stmt;
		boolean ok = false;
		String strSQL;

		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "UPDATE TipoPez " + " SET descripcion = '" + m.getDescripcion() + "'" + " , phMin = "
					+ m.getPhMin() + " , phMax = " + m.getPhMax() + " , tempMin = " + m.getTemMin() + " , tempMax = "
					+ m.getTemMax() + " WHERE TipoPez_id='" + m.getTipopez_id() + "'";
			return (stmt.executeUpdate(strSQL) > 0);
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
	}
}
