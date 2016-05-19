package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import VarTypes.Pecera;

public class DAOPecera {

	static public ArrayList<Pecera> getPeceras() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pecera> lista = null;
		Pecera pecera;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id,IP,Capacidad,HoraComida"+
					" FROM PECERA";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));
					
				pecera = new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("HoraComida"),
						result.getInt("Capacidad"),0,0);
				lista.add(pecera);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}

	static public ArrayList<Pecera> getPecerasPorDueno() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pecera> lista = null;
		Pecera pecera;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id,IP,Capacidad,HoraComida"+
					" FROM PECERA";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));
					
				pecera=new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("Nombre"),
						result.getInt("Capacidad"));
				lista.add(pecera);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	
	static public Pecera buscarPorID(int idPecera) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Pecera p;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id, IP, Capacidad, horacomida"+
					" FROM PECERA"+
					" WHERE pecera_id='"+idPecera+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("horacomida"));
			p=new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("Nombre"),
					result.getInt("Capacidad"));
			result.close();
			return p;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static public Pecera buscarPorIP(String IPPecera) throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		Pecera p;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id, IP, Capacidad, horacomida"+
					" FROM PECERA"+
					" WHERE IP='"+IPPecera+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("horacomida"));
			p=new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("Nombre"),
					result.getInt("Capacidad"));
			result.close();
			return p;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}


	static public boolean addPecera(Pecera p) throws Exception{

		Statement stmt;
		String strSQL;
		int result;

		try
		{
			if(buscarPorID(p.getID())==null && buscarPorIP(p.getIP()) == null)
			{

				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="INSERT INTO PECERA "+
						" VALUES ("+p.getID()+",'"+p.getIP()+"','"+p.getCapacidad()+"','"+p.getHoracomida().getTime()+")";
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

	public static void eliminarPecera(String IP) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  PECERA "+
				" WHERE IP = '"+IP+"'";
		result = stmt.executeUpdate(strSQL);
	}

	static public boolean updatePecera(Pecera p) throws Exception
	{    
		Statement stmt;
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE PECERA "+
					" SET IP = '"+p.getIP()+
					"', Capacidad   = '"+p.getCapacidad()+
					"', nombre   = '"+p.getNombre()+
					"', horacomida   = '"+p.getHoracomida().getTime()+
					"' WHERE pecera_id='"+p.getID()+"'";
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
