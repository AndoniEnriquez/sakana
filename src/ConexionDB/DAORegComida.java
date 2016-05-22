package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import VarTypes.Pecera;
import VarTypes.RegComida;

public class DAORegComida {
	
	static public ArrayList<RegComida> getRegistros() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<RegComida> lista = null;
		RegComida registro;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT comida_id, pecera_id, datetime"+
					" FROM regcomida";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				registro = new RegComida(result.getInt("comida_id"),result.getInt("pecera_id"),result.getDate("datetime"));
				lista.add(registro);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	
	static public ArrayList<RegComida> getRegistrosPorPecera(Pecera p) throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<RegComida> lista = null;
		RegComida registro;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT comida_id, pecera_id, datetime"+
					" FROM regcomida"+
					" WHERE pecera_id='"+p.getID()+"'";
			System.out.println(strSQL);
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));

				registro = new RegComida(result.getInt("comida_id"),result.getInt("pecera_id"),result.getDate("datetime"));
				lista.add(registro);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	@SuppressWarnings("deprecation")
	static public boolean addRegistro(RegComida r) throws Exception{

		Statement stmt;
		String strSQL;
		int result;
		String horacomida = r.getDatetime().getHours()+":"+r.getDatetime().getMinutes();
		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="INSERT INTO regcomida (comida_id,pecera_id,datetime)"+
					" VALUES ('"+r.getComida_id()+"','"+r.getPecera_id()+"','"+horacomida+"')";
			result = stmt.executeUpdate(strSQL);		      
			return true;

		}

		catch(SQLException e)
		{
			e.printStackTrace();;
			return false;
		}
	}
	@SuppressWarnings("deprecation")
	public static void eliminarPecera(RegComida r) throws SQLException {
		
		Statement stmt;
		int result;
		String strSQL;
		String horacomida = r.getDatetime().getHours()+":"+r.getDatetime().getMinutes();
		stmt=PoolConexiones.getConexion().createStatement();
		
		strSQL=" DELETE FROM regcomida "+
				" WHERE datetime = '"+horacomida+"'";
		result = stmt.executeUpdate(strSQL);
	}
}
