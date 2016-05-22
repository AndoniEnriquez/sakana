package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VarTypes.TipoComida;

public class DAOTipoComida {

	static public ArrayList<TipoComida> getTiposMedicion() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<TipoComida> lista = null;
		TipoComida TipoComida;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoComida_id, descripcionTipoComida"+
					" FROM TipoComida";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				TipoComida = new TipoComida(result.getInt("TipoComida_id"),result.getString("descripcionTipoComida"));
				lista.add(TipoComida);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	static public TipoComida buscarPorID(int idTipoComida) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoComida TipoComida;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoComida_id, descripcionTipoComida"+
					" FROM TipoComida"+
					" WHERE TipoComida_id="+idTipoComida;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			TipoComida = new TipoComida(result.getInt("TipoComida_id"),result.getString("descripcionTipoComida"));
			result.close();
			return TipoComida;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	static public TipoComida buscarPorDescripcion(String descripcion) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoComida TipoComida;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoComida_id, descripcionTipoComida"+
					" FROM TipoComida"+
					" WHERE descripcionTipoComida='"+descripcion+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			TipoComida = new TipoComida(result.getInt("TipoComida_id"),result.getString("descripcionTipoComida"));
			result.close();
			return TipoComida;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static public boolean addTipoComida(TipoComida m) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(m.getTipocomida_id())==null && buscarPorDescripcion(m.getDescripcionTipoComida()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO TipoComida "+
						" VALUES ("+m.getTipocomida_id()+",'"+m.getDescripcionTipoComida()+")";
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

	public static void eliminarTipoComida(String descripcion) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  TipoComida "+
				" WHERE nombre = '"+descripcion+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updatePecera(TipoComida m) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE TipoComida "+
					" SET descripcionTipoComida = '"+m.getDescripcionTipoComida()+
					"' WHERE TipoComida_id='"+m.getTipocomida_id()+"'";
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
