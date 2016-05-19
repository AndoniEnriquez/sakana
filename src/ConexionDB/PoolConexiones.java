package ConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//import java.util.*;
//import java.io.*;

class PoolConexiones
{
  //protected static String nombreBBDD="jdbc:odbc:gestionMaterial_01";
	final static String SGBDURL = "jdbc:mysql://localhost:3306/pecera";
	private static Connection instance;
 
  /* versi�n basada en JODBC
   * 
   *
  public static Connection getConexion() throws Exception
  {
    if(instance==null)
    {
      try
      {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        instance = DriverManager.getConnection(nombreBBDD);
      }
      catch(ClassNotFoundException e)
      {
        throw new Exception("Driver");
      }
      catch(SQLException e)
      {
        throw new Exception("fuentes ODBC");
      }
    }
    return instance;
  }
  */
  public static Connection getConexion() throws SQLException {

	  if (instance == null){
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "123");

	   instance = DriverManager.getConnection(SGBDURL, connectionProps);
	  }
	  return instance;
	}
} 






