package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import VarTypes.Pecera;
import VarTypes.RegComida;

public class DAORegComida {
	
	static public ArrayList<RegComida> getRegistros(){
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
	
	static public ArrayList<RegComida> getRegistrosPorPecera(Pecera p){
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<RegComida> lista = null;
		RegComida registro;
		Date dateTime;
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
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
				String tiempo = result.getString("datetime");
				System.out.println(tiempo);
				dateTime = simpleDateFormat.parse(tiempo);
				System.out.println(simpleDateFormat.format(dateTime));
				
				registro = new RegComida(result.getInt("comida_id"),result.getInt("pecera_id"),dateTime);
				lista.add(registro);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	@SuppressWarnings("deprecation")
	static public boolean addRegistro(RegComida r){

		Statement stmt;
		String strSQL;
		int result;
		String horacomida = r.getDatetime().getHours() + ":" + r.getDatetime().getMinutes();
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
	public static boolean eliminarPecera(RegComida r){
		
		Statement stmt;
		int result;
		String strSQL;
		String horacomida = r.getDatetime().getHours()+":"+r.getDatetime().getMinutes();
		try {
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL=" DELETE FROM regcomida "+
					" WHERE datetime = '"+horacomida+"'";
			result = stmt.executeUpdate(strSQL);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
		
	}
}
