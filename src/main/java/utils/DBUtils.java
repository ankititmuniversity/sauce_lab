package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static final String URL = "jdbc:mysql://localhost:3306/countrydb";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private DBUtils() {}

	// Create a new connection each time (safe for CI/CD)
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

/*
 * package utils;
 * 
 * import java.sql.*;
 * 
 * public class DBUtils {
 * 
 * private static final String URL = "jdbc:mysql://localhost:3306/countrydb";
 * private static final String USER = "root"; private static final String
 * PASSWORD = "root";
 * 
 * private DBUtils() {}
 * 
 * public static Connection getConnection() throws SQLException { return
 * DriverManager.getConnection(URL, USER, PASSWORD); }
 * 
 * // Generic read method public static ResultSet executeQuery(String query,
 * Object... params) throws SQLException { Connection con = getConnection();
 * PreparedStatement ps = con.prepareStatement(query); for (int i = 0; i <
 * params.length; i++) { ps.setObject(i + 1, params[i]); } return
 * ps.executeQuery(); }
 * 
 * // Generic write method public static int executeUpdate(String query,
 * Object... params) throws SQLException { try (Connection con =
 * getConnection(); PreparedStatement ps = con.prepareStatement(query)) { for
 * (int i = 0; i < params.length; i++) { ps.setObject(i + 1, params[i]); }
 * return ps.executeUpdate(); } } }
 */
