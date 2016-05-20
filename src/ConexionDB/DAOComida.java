package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import VarTypes.*;

public class DAOComida {



	static public ArrayList<Comida> getComidas() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Comida> lista = null;
		Comida Comida;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT comida_id,nombreComida,descripcionComida,tipocomida_id"+
					"FROM Comida";
			result = stmt.executeQuery(strSQL);
			while (result.next()){

				Comida = new Comida(result.getString("nombreComida"),result.getString("descripcionComida"),
						result.getInt("tipocomida_id"));
				lista.add(Comida);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}


	static public Comida buscarPorID(int idComida) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Comida p;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT comida_id,nombreComida,descripcionComida,tipocomida_id"+
					" FROM Comida"+
					" WHERE Comida_id='"+idComida+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;

			p=new Comida(result.getString("nombreComida"),result.getString("descripcionComida"),
					result.getInt("tipocomida_id"));
			result.close();
			return p;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static public Comida buscarPorNombre(String nombreComida) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Comida p;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT nombreComida,descripcionComida,tipocomida_id"+
					" FROM Comida"+
					" WHERE nombreComida='"+nombreComida+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;

			p=new Comida(result.getString("nombreComida"),result.getString("descripcionComida"),
					result.getInt("tipocomida_id"));
			result.close();
			return p;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}


	static public boolean addComida(Comida p) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(p.getComida_id())==null && buscarPorNombre(p.getNombreComida()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO Comida (nombreComida, descripcionComida, tipoComida_id)"+
						" VALUES ("+p.getNombreComida()+"','"+p.getDescripcionComida()+"','"+p.getTipocomida_id()+")";
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

	public static void eliminarComida(String nombre) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  Comida "+
				" WHERE nombreComida = '"+nombre+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updateComida(Comida p) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE Comida "+
					" SET nombreComida = '"+p.getNombreComida()+
					"', descripcionComida   = '"+p.getDescripcionComida()+
					"', tipocomida_id   = '"+p.getTipocomida_id()+
					"' WHERE nombreComida='"+p.getNombreComida()+"'";
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
