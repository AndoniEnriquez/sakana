package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class PoolConexiones {

	final static String SGBDURL = "jdbc:mysql://localhost:3306/pecera";
	private static Connection instance;

	public static Connection getConexion() throws SQLException {

		if (instance == null) {
			Properties connectionProps = new Properties();
			connectionProps.put("user", "Sakana");
			connectionProps.put("password", "Sakana2016");

			instance = DriverManager.getConnection(SGBDURL, connectionProps);
		}
		
		return instance;
	}
}
