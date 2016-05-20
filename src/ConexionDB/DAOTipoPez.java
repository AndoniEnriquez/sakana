package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VarTypes.TipoPez;

public class DAOTipoPez {
	static public ArrayList<TipoPez> getTiposPez() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<TipoPez> lista = null;
		TipoPez TipoPez;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoPez_id, descripcion"+
					" FROM TipoPez";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				TipoPez = new TipoPez(result.getInt("TipoPez_id"),result.getString("descripcion"));
				lista.add(TipoPez);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	static public TipoPez buscarPorID(int idTipoPez) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoPez TipoPez;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoPez_id, descripcion"+
					" FROM TipoPez"+
					" WHERE TipoPez_id='"+idTipoPez+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			TipoPez = new TipoPez(result.getInt("TipoPez_id"),result.getString("descripcion"));
			result.close();
			return TipoPez;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	static public TipoPez buscarPorDescripcion(String descripcion) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		TipoPez TipoPez;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT TipoPez_id, descripcion"+
					" FROM TipoPez"+
					" WHERE descripcion='"+descripcion+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			TipoPez = new TipoPez(result.getInt("TipoPez_id"),result.getString("descripcion"));
			result.close();
			return TipoPez;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static public boolean addTipoPez(TipoPez m) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(m.getTipopez_id())==null && buscarPorDescripcion(m.getDescripcion()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO TipoPez "+
						" VALUES ("+m.getDescripcion()+",'"+m.getDescripcion()+")";
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

	public static void eliminarTipoPez(String descripcion) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  TipoPez "+
				" WHERE nombre = '"+descripcion+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updatePecera(TipoPez m) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE TipoPez "+
					" SET descripcion = '"+m.getDescripcion()+
					"' WHERE TipoPez_id='"+m.getTipopez_id()+"'";
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
