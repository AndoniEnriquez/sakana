package ConexionDB;

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
			connectionProps.put("user", "root");
			connectionProps.put("password", "1234");

			instance = DriverManager.getConnection(SGBDURL, connectionProps);
		}
		
		return instance;
	}
}
