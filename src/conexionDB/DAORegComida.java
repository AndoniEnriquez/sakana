package conexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import varTypes.Pecera;
import varTypes.RegComida;

public class DAORegComida {

	static public ArrayList<RegComida> getRegistros() {
	
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<RegComida> lista = null;
		RegComida registro;
		
		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT comida_id, pecera_id, datetime" + " FROM regcomida";
			result = stmt.executeQuery(strSQL);
		
			while (result.next()) {
				registro = new RegComida(result.getInt("comida_id"), result.getInt("pecera_id"),
						result.getDate("datetime"));
				lista.add(registro);
			}
			
			result.close();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return lista;
	}

	static public ArrayList<RegComida> getRegistrosPorPecera(Pecera p) {
		
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<RegComida> lista = null;
		RegComida registro;
		Date dateTime;
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		
		try {

			lista = new ArrayList<>();
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "SELECT comida_id, pecera_id, datetime" + " FROM regcomida" + " WHERE pecera_id='" + p.getID() + "'";
			result = stmt.executeQuery(strSQL);

			while (result.next()) {

				String tiempo = result.getString("datetime");
				dateTime = simpleDateFormat.parse(tiempo);
				registro = new RegComida(result.getInt("comida_id"), result.getInt("pecera_id"), dateTime);
				lista.add(registro);
			}
			
			result.close();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return lista;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	static public boolean addRegistro(RegComida r) {

		Statement stmt;
		String strSQL;
		int result;
		String horacomida = r.getDatetime().getHours() + ":" + r.getDatetime().getMinutes();
		
		try {
		
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = "INSERT INTO regcomida (comida_id,pecera_id,datetime)" + " VALUES ('" + r.getComida_id() + "','"
					+ r.getPecera_id() + "','" + horacomida + "')";
			result = stmt.executeUpdate(strSQL);
			return true;
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean eliminarPecera(RegComida r) {

		Statement stmt;
		int result;
		String strSQL;
		String horacomida = r.getDatetime().getHours() + ":" + r.getDatetime().getMinutes();
		
		try {
			
			stmt = PoolConexiones.getConexion().createStatement();
			strSQL = " DELETE FROM regcomida " + " WHERE datetime = '" + horacomida + "'";
			result = stmt.executeUpdate(strSQL);
			return true;
		
		} catch (SQLException e) {
			
			return false;
		}

	}
}
