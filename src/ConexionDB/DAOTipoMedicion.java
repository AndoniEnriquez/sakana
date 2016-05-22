package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VarTypes.*;

public class DAOTipoMedicion {


	static public ArrayList<TipoMedicion> getTiposMedicion() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<TipoMedicion> lista = null;
		TipoMedicion tipoMedicion;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT tipomedicion_id, nombreMedicion"+
					" FROM tipomedicion";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"),result.getString("nombreMedicion"));
				lista.add(tipoMedicion);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	static public TipoMedicion buscarPorID(int idTipoMedicion) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoMedicion tipoMedicion;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT tipomedicion_id, nombreMedicion"+
					" FROM tipomedicion"+
					" WHERE tipomedicion_id="+idTipoMedicion;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"),result.getString("nombreMedicion"));
			result.close();
			return tipoMedicion;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	static public TipoMedicion buscarPorNombre(String nombre) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoMedicion tipoMedicion;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT tipomedicion_id, nombreMedicion"+
					" FROM tipomedicion"+
					" WHERE nombreMedicion='"+nombre+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			tipoMedicion = new TipoMedicion(result.getInt("tipomedicion_id"),result.getString("nombreMedicion"));
			result.close();
			return tipoMedicion;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static public boolean addTipoMedicion(TipoMedicion m) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(m.getTipoMedicion_id())==null && buscarPorNombre(m.getNombreMedicion()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO TIPOMEDICION "+
						" VALUES ("+m.getTipoMedicion_id()+",'"+m.getNombreMedicion()+")";
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

	public static void eliminarTipoMedicion(String nombre) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  TIPOMEDICION "+
				" WHERE nombre = '"+nombre+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updatePecera(TipoMedicion m) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE TIPOMEDICION "+
					" SET nombreMedicion = '"+m.getNombreMedicion()+
					"' WHERE tipomedicion_id="+m.getTipoMedicion_id();
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
