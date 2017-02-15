import java.sql.*;

/*
 * This class for connect to database.
 * 
 * @author  Mahdi Madani
 * @version 1.0
 * @since   2017-01-22
 * 
 * 
 */

public class Connect {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/paint";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static Connection conn = null;
	static Statement stmt = null;

	static String status;

	/*
	 * This is constructor method for this class and allow connect to database.
	 */

	public Connect() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			status = "Connecting to database...";
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		} catch (Exception e) {
			status = "There was an error connecting to the database.";
		}
	}

	public static void disconnect() {
		try {
			stmt.close();
			conn.close();
			status = "Disconnecting to database.";
			System.out.println(status);
		} catch (Exception e) {
			status = "There was an error disconnecting to database.";
			System.out.println(status);
		}

	}
}
