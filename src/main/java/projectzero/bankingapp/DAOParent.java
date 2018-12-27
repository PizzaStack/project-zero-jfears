package projectzero.bankingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOParent {
	private static String url = "jdbc:postgresql://baasu.db.elephantsql.com:5432/kdtfqpak";
	private static String username = "kdtfqpak";
	private static String password = "ygtJn5063jq6e7Xikf8t1fj8iT_G7G3y";
	protected static Connection connection = createConnection();
	
	private static Connection createConnection() {
		 try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		 return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
