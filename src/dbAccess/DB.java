package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	/**
	 * Used to get a database connection with appropriate schema
	 */
	public static Connection getConnection() {

		Connection con = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/banking?autoReconnect=true&useSSL=false", "root", "am uitat");
		} catch (Exception e) {

			System.out.println(e);
		}
		return con;
	}
}
