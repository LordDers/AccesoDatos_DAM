import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection ConexionMySQL() {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost/registrousuarios";
		
		//  Database credentials
		final String USER = "root";
		final String PASS = "";
		
		Connection conn = null;
		
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 3: Open a connection
			//System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
		  //Handle errors for Class.forName
			e.printStackTrace();
		}//end try
		return conn;
	}
}
