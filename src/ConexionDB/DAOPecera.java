package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import VarTypes.Dueno;
import VarTypes.Pecera;

public class DAOPecera {

	static public ArrayList<Pecera> getPeceras() throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pecera> lista = null;
		Pecera pecera;
		Date horacomida;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id, IP,Capacidad,NombrePecera,horacomida"+
					" FROM PECERA";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));
				
				Date date = simpleDateFormat.parse(result.getString("horacomida"));

				pecera = new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("NombrePecera"),
						result.getInt("Capacidad"));
				pecera.setHoracomida(date);
				lista.add(pecera);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
	
	static public float[] getPhporPecera(Pecera p) throws Exception {
		Statement stmt;
		ResultSet result;
		String strSQL;
		float[] ph = new float[2];
		try
		{

			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="select MAX(phmin), MIN(phMax)"+
					" from tipopez"+
					" where tipopez_id IN (select tipopez_id"+
					" from pez join pecera on pez.pecera_id = pecera.pecera_id"+
					" where pecera.pecera_id ="+p.getID()+")";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				ph[0] = result.getFloat("MAX(phmin)");
				ph[1] = result.getFloat("MAX(phMax)");
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return ph;
	}
	
	static public float[] getTempporPecera(Pecera p) throws Exception {
		Statement stmt;
		ResultSet result;
		String strSQL;
		float[] temperatura = new float[2];
		try
		{

			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="select MAX(tempMin), MIN(tempMax)"+
					" from tipopez"+
					" where tipopez_id IN (select tipopez_id"+
					" from pez join pecera on pez.pecera_id = pecera.pecera_id"+
					" where pecera.pecera_id ="+p.getID()+")";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				temperatura[0] = result.getFloat("MAX(tempMin)");
				temperatura[1] = result.getFloat("MIN(tempMax)");
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return temperatura;
	}

	static public void getIdPorNombre(Pecera p) throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		try
		{

			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT pecera_id"+
					"FROM pecera"+
					"WHERE nombrePecera='"+p.getNombre()+"'";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));

				p.setID(result.getInt("pecera_id"));

			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
	}


	static public ArrayList<Pecera> getPecerasPorDueno(Dueno d) throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pecera> lista = null;
		Pecera pecera;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT p.pecera_id, p.IP,p.Capacidad,p.NombrePecera,p.HoraComida"+
					" FROM (PECERA p JOIN pez pe on p.pecera_id=pe.pecera_id) JOIN dueno d on pe.dueno_id=d.dueno_id"+
					" WHERE d.nombreDueno='"+d.getNombreDueno()+"'"+
					" GROUP BY p.pecera_id";
			System.out.println(strSQL);
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));

				pecera=new Pecera(result.getInt("pecera_id"),result.getString("IP"),result.getString("NombrePecera"),
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
			strSQL="SELECT IP, Capacidad, NombrePecera , Horacomida"+
					" FROM PECERA"+
					" WHERE pecera_id='"+idPecera+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;

			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("horacomida"));
			p=new Pecera(result.getString("IP"),result.getString("NombrePecera"),
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
			strSQL="SELECT pecera_id, IP, Capacidad, NombrePecera, HoraComida"+
					" FROM PECERA"+
					" WHERE IP='"+IPPecera+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;

			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("horacomida"));
			p=new Pecera(result.getString("IP"),result.getString("NombrePecera"),
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


	@SuppressWarnings("deprecation")
	static public boolean addPecera(Pecera p) throws Exception{

		Statement stmt;
		String strSQL;
		int result;
		String horacomida = p.getHoracomida().getHours()+":"+p.getHoracomida().getMinutes();
		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="INSERT INTO PECERA (nombrePecera,horacomida,ip,capacidad)"+
					" VALUES ('"+p.getNombre()+"','"+horacomida+"','"+p.getIP()+"','"+p.getCapacidad()+"')";
			result = stmt.executeUpdate(strSQL);		      
			return true;

		}

		catch(SQLException e)
		{
			e.printStackTrace();;
			return false;
		}
	}

	public static void eliminarPecera(Pecera p) throws SQLException {
	
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		
		strSQL=" DELETE FROM PECERA "+
				" WHERE IP = '"+p.getIP()+"'";
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
					"' WHERE nombre='"+p.getNombre()+"'";
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
