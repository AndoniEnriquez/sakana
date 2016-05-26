package conexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import varTypes.Comida;

public class DAOComida {

	static public ArrayList<Comida> getComidas() {

		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Comida> lista = null;
		Comida Comida;

		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT comida_id,nombreComida,descripcionComida,tipocomida_id" + "FROM Comida";
			result = stmt.executeQuery(strSQL);

			while (result.next()) {

				Comida = new Comida(result.getString("nombreComida"), result.getString("descripcionComida"),
						result.getInt("tipocomida_id"));
				lista.add(Comida);
			}

			result.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lista;
	}

	static public ArrayList<String> getComidasParaComboBox() {

		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<String> lista = null;

		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT nombreComida" + " FROM Comida";
			result = stmt.executeQuery(strSQL);

			while (result.next()) {

				lista.add(result.getString("nombreComida"));
			}

			result.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lista;
	}

	static public Comida buscarPorID(int idComida) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		Comida p;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT comida_id,nombreComida,descripcionComida,tipocomida_id" + " FROM Comida"
					+ " WHERE Comida_id='" + idComida + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;

			p = new Comida(result.getString("nombreComida"), result.getString("descripcionComida"),
					result.getInt("tipocomida_id"));
			
			result.close();
			return p;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	static public Comida buscarPorNombre(String nombreComida) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		Comida p;

		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT nombreComida,descripcionComida,tipocomida_id" + " FROM Comida" + " WHERE nombreComida='"
					+ nombreComida + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;

			p = new Comida(result.getString("nombreComida"), result.getString("descripcionComida"),
					result.getInt("tipocomida_id"));
			result.close();
			return p;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	static public boolean addComida(Comida p) {

		Statement stmt;
		String strSQL;
		int result;

		try {
			
			if (buscarPorID(p.getComida_id()) == null && buscarPorNombre(p.getNombreComida()) == null) {
				stmt = PoolConexiones.getConexion().createStatement();
				strSQL = "INSERT INTO Comida (nombreComida, descripcionComida, tipoComida_id)" + " VALUES ("
						+ p.getNombreComida() + "','" + p.getDescripcionComida() + "','" + p.getTipocomida_id() + ")";
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
	public static boolean eliminarComida(String nombre) {
		
		Statement stmt;
		int result;
		String strSQL;
		
		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "DELETE FROM  Comida " + " WHERE nombreComida = '" + nombre + "'";
			result = stmt.executeUpdate(strSQL);
			return true;
			
		} catch (SQLException e) {
			return false;
		}

	}

	@SuppressWarnings("unused")
	static public boolean updateComida(Comida p) throws Exception {
		
		Statement stmt;
		boolean ok = false;
		String strSQL;

		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "UPDATE Comida " + " SET nombreComida = '" + p.getNombreComida() + "', descripcionComida   = '"
					+ p.getDescripcionComida() + "', tipocomida_id   = '" + p.getTipocomida_id()
					+ "' WHERE nombreComida='" + p.getNombreComida() + "'";
			return (stmt.executeUpdate(strSQL) > 0);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
}
