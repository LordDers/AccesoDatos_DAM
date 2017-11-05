import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {	
	
	public static Connection Conexion() {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "org.sqlite.JDBC";
		
		Connection conn = null;
		
		try {
			//STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");
			
			//STEP 3: Open a connection
			//System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlite:registrousuarios.db");
			//conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Usuario6i/eclipse-workspace/RegistroUsuariosSQLite.registrousuarios.db");
		} catch(SQLException ex) {
			System.err.println("No se ha podido conectar a la base de datos");
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return conn;
	}
}
