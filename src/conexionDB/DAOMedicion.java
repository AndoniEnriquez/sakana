package conexionDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import varTypes.Medicion;

public class DAOMedicion {

	static public ArrayList<Medicion> getMediciones() {

		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Medicion> lista = null;
		Medicion medicion;

		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT medicion_id, valor, datetimeMedicion, tipomedicion_id, pecera_id" + " FROM MEDICION";
			result = stmt.executeQuery(strSQL);
			
			while (result.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(result.getDate("datetimeMedicion"));
				medicion = new Medicion(result.getFloat("valor"), cal, result.getInt("tipomedicion_id"), result.getInt("pecera_id"));
				lista.add(medicion);
			}
			
			result.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	static public Medicion buscarPorID(int idMedicion) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		Medicion m;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT medicion_id, nombre, datetimeMedicion, tipomedicion_id, pecera_id" + " FROM MEDICION"
					+ " WHERE medicion_id=" + idMedicion;
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;

			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("datetimeMedicion"));
			m = new Medicion(result.getFloat("valor"), cal, result.getInt("tipomedicion_id"), result.getInt("pecera_id"));
			result.close();
			return m;
		}

		catch (SQLException e) {
		
			e.printStackTrace();
			return null;
		}
	}

	static public Medicion buscarPorNombre(String nombre) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		Medicion m;

		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT medicion_id, valor, datetimeMedicion, tipomedicion_id, pecera_id" + " FROM MEDICION"
					+ " WHERE nombre= '" + nombre + "'";
			result = stmt.executeQuery(strSQL);
			
			if (!result.next()) return null;

			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("datetimeMedicion"));
			m = new Medicion(result.getFloat("valor"), cal, result.getInt("tipomedicion_id"), result.getInt("pecera_id"));
			result.close();
			return m;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	static public boolean addMedicion(Medicion m) {

		Statement stmt;
		String strSQL;
		int result;

		try {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			String timeMedicion = simpleDateFormat.format(m.getDatetime().getTime());

			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "INSERT INTO MEDICION (valor, datetimeMedicion, tipomedicion_id, pecera_id) " + " VALUES ("
					+ m.getValor() + ",'" + timeMedicion + "'," + m.getTipoMedicion_id() + "," + m.getPecera_id()+ ")";
			result = stmt.executeUpdate(strSQL);
			return true;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unused")
	public static boolean eliminarMedicion(String nombre) {
		
		Statement stmt;
		int result;
		String strSQL;
		
		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "DELETE FROM  MEDICION " + " WHERE nombre = '" + nombre + "'";
			result = stmt.executeUpdate(strSQL);
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	@SuppressWarnings("unused")
	static public boolean updateMedicion(Medicion m) {
		
		Statement stmt;
		boolean ok = false;
		String strSQL;

		try {
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "UPDATE MEDICION " + " SET valor = '" + m.getValor() + "', datetimeMedicion   = '"
					+ m.getDatetime().getTime() + "', tipomedicion_id   = '" + m.getTipoMedicion_id()
					+ "', pecera_id   = '" + m.getPecera_id() + "' WHERE medicion_id='" + m.getID() + "'";
			return (stmt.executeUpdate(strSQL) > 0);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
}
