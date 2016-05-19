package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import VarTypes.*;

public class DAOMedicion {
	
	static public ArrayList<Medicion> getMediciones() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Medicion> lista = null;
		Medicion medicion;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT medicion_id, nombre, datatimeMedicion, tipomedicion_id, pecera_id"+
					" FROM MEDICION";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				Calendar cal = Calendar.getInstance();
				cal.setTime(result.getDate("datatimeMedicion"));
				medicion = new Medicion(result.getInt("medicion_id"),result.getString("nombre"),cal,
						result.getInt("tipomedicion_id"),result.getInt("pecera_id"));
				lista.add(medicion);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	static public Medicion buscarPorID(int idMedicion) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Medicion m;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT medicion_id, nombre, datatimeMedicion, tipomedicion_id, pecera_id"+
					" FROM MEDICION"+
					" WHERE medicion_id="+idMedicion;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("datatimeMedicion"));
			m = new Medicion(result.getInt("medicion_id"),result.getString("nombre"),cal,
					result.getInt("tipomedicion_id"),result.getInt("pecera_id"));
			result.close();
			return m;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	static public Medicion buscarPorNombre(String nombre) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Medicion m;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT medicion_id, nombre, datatimeMedicion, tipomedicion_id, pecera_id"+
					" FROM MEDICION"+
					" WHERE nombre="+nombre;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("datatimeMedicion"));
			m = new Medicion(result.getInt("medicion_id"),result.getString("nombre"),cal,
					result.getInt("tipomedicion_id"),result.getInt("pecera_id"));
			result.close();
			return m;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	static public boolean addMedicion(Medicion m) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(m.getID())==null && buscarPorNombre(m.getNombre()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO MEDICION "+
						" VALUES ("+m.getID()+",'"+m.getNombre()+"','"+m.getDatetime().getTime()+"','"+m.getTipoMedicion_id()+"','"+m.getPecera_id()+")";
				result = stmt.executeUpdate(strSQL);		      
				return true;
			}
			else return false;
		}

		catch(SQLException e)
		{
			e.printStackTrace();;
			return false;
		}
	}

	public static void eliminarMedicion(String nombre) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  MEDICION "+
				" WHERE nombre = '"+nombre+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updateMedicion(Medicion m) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE MEDICION "+
					" SET nombre = '"+m.getNombre()+
					"', datetimeMedicion   = '"+m.getDatetime().getTime()+
					"', tipomedicion_id   = '"+m.getTipoMedicion_id()+
					"', pecera_id   = '"+m.getPecera_id()+
					"' WHERE medicion_id="+m.getID();
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}


