package ConexionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VarTypes.Dueno;
import VarTypes.Pecera;
import VarTypes.Pez;

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
					" FROM pez";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				pez = new Pez(result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
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
					" WHERE pez_id='"+idPez+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			pez = new Pez(result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
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
					" WHERE nombrePez='"+nombre+"'";
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			pez = new Pez(result.getString("nombrePez"),result.getString("genero"),result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
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
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="INSERT INTO pez (nombrePez, genero, tipopez_id, dueno_id, pecera_id) "+
					" VALUES ('"+pez.getNombrePez()+"','"+pez.getGenero()+"',"+pez.getTipoPez_id()+","+pez.getDueno_id()+","+pez.getPecera_id()+")";
			System.out.println(strSQL);
			result = stmt.executeUpdate(strSQL);		      
			return true;

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

	static public boolean updatePez(Pez p, String nombreAnterior) throws Exception
	{    
		Statement stmt;
	
		boolean ok=false;
		String strSQL;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="UPDATE pez"+
					" SET nombrePez = '"+p.getNombrePez()+ "'" +
					" , genero = '"+p.getGenero()+ "'" +
					" , tipopez_id = "+p.getTipoPez_id()+ 
					" , dueno_id = "+p.getDueno_id()+ 
					" , pecera_id = "+p.getPecera_id()+
					" WHERE nombrePez ='"+nombreAnterior+"'";
			
			System.out.println(strSQL);
			return (stmt.executeUpdate(strSQL)>0);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	static public ArrayList<Pez> getPecesPeceraDueno (Pecera p, Dueno d) throws Exception{
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<Pez> lista = null;
		Pez pez;
		try
		{

			lista = new ArrayList<>();
			stmt=PoolConexiones.getConexion().createStatement();

			strSQL="SELECT pe.nombrePez, pe.genero, pe.tipoPez_id, pe.dueno_id, pe.pecera_id"+
					" FROM (PECERA p JOIN pez pe on p.pecera_id=pe.pecera_id) JOIN dueno d on pe.dueno_id=d.dueno_id"+
					" WHERE d.nombreDueno = '"+d.getNombreDueno()+"' and p.nombrePecera = '" + p.getNombre() + "'";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(result.getDate("horacomida"));

				pez=new Pez(result.getString("nombrePez"),result.getString("genero"),
						result.getInt("tipopez_id"),result.getInt("dueno_id"),result.getInt("pecera_id"));
				lista.add(pez);
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return lista;
	}
}
