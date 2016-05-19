package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VarTypes.*;

public class DAOPez {
	static public ArrayList<Pez> getPeces() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pez> lista = null;
		Pez pez;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pez_id, nombrePez, genero, tipopez_id, dueno_id, pecera_id"+
					"FROM pez";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				pez = new Pez(result.getInt("pez_id"),result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
				lista.add(pez);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	static public Pez buscarPorID(int idPez) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Pez pez;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pez_id, nombrePez, genero, tipopez_id, dueno_id, pecera_id"+
					" FROM pez"+
					" WHERE pez_id="+idPez;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			pez = new Pez(result.getInt("pez_id"),result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
			result.close();
			return pez;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	static public Pez buscarPorNombre(String nombre) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Pez pez;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pez_id, nombrePez, genero, tipopez_id, dueno_id, pecera_id"+
					" FROM pez"+
					" WHERE nombrePez="+nombre;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			pez = new Pez(result.getInt("pez_id"),result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
			result.close();
			return pez;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	static public boolean addPez(Pez pez) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(pez.getPez_id())==null && buscarPorNombre(pez.getNombrePez()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO pez "+
						" VALUES ("+pez.getPez_id()+",'"+pez.getNombrePez()+",'"+pez.getGenero()+",'"+pez.getTipoPez_id()+",'"+pez.getDueno_id()+",'"+pez.getPecera_id()+")";
				result = stmt.executeUpdate(strSQL);		      
				return true;
			}
			else return false;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public static void eliminarPez(String nombre) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  pez "+
				" WHERE nombrePez = '"+nombre+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updatePecera(Pez p) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE pez "+
					" SET nombrePez = '"+p.getNombrePez()+
					" SET genero = '"+p.getGenero()+
					" SET tipopez_id = '"+p.getTipoPez_id()+
					" SET dueno_id = '"+p.getDueno_id()+
					" SET pecera_id = '"+p.getPecera_id()+
					"' WHERE pez_id="+p.getPez_id();
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
