import java.sql.*;
import java.util.Scanner;

public class RegistroUsuarios {

	// Constructor privado
	private void RegistroUsuario() {
		
	}	
	
	public static void mostrarUsuariosMySQL() throws UsuarioInexistente {
		
		Statement stmt = null;
		
		try {
			// Conexion
			Connection conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM usuarios";
			ResultSet rs = stmt.executeQuery(sql);
			
			// Comprobar si hay algún dato
			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			} else {
				throw new UsuarioInexistente("No hay usuarios");
			}
			
			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				//rs.getInt
				String dni  = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				//Display values
				System.out.print("DNI: " + dni);
				System.out.print(", Nombre: " + nombre);
				System.out.print(", Apellido: " + apellido + "\n");
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static void anyadirUsuarioMySQL(String v_dni, String v_nombre, String v_apellido) throws UsuarioExistente {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Conexion
			conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = '" + v_dni + "'";
			ResultSet rs = stmt.executeQuery(sqlSelect);
			
			if (rs.next()) {
				throw new UsuarioExistente("El DNI introducido ya esta registrado");
			} else {
				String sqlInsert;
				sqlInsert = "INSERT INTO usuarios(dni, nombre, apellido) VALUES('" + v_dni + "', '" + v_nombre + "', '" + v_apellido + "')";
				stmt.executeUpdate(sqlInsert);
				System.out.println("Usuario anyadido correctamente");
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void buscarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		Statement stmt = null;
		
		try {
			// Conexion
			Connection conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = '" + vdni + "'";
			ResultSet rs = stmt.executeQuery(sqlSelect);
			
			if (rs.next()) {
				String dni  = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				//Display values
				System.out.print("DNI: " + dni);
				System.out.print(", Nombre: " + nombre);
				System.out.print(", Apellido: " + apellido + "\n");
			} else {
				throw new UsuarioInexistente("No existe el DNI introducido");
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static void borrarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Conexion
			conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = '" + vdni + "'";
			ResultSet rs = stmt.executeQuery(sqlSelect);
			
			if (rs.next()) {
				String sqlDelete;
				sqlDelete = "DELETE FROM usuarios WHERE dni = '" + vdni + "'";
				stmt.executeUpdate(sqlDelete);
				System.out.println("Usuario borrado correctamente");
			} else {
				throw new UsuarioInexistente("No existe el DNI introducido");
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	public static void actualizarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		Connection conn = null;
		Statement stmt = null;
		String slqUpdate;
		
		try {
			// Conexion
			conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = '" + vdni + "'";
			ResultSet rs = stmt.executeQuery(sqlSelect);
			
			if (rs.next()) {
				String dni  = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				//Display values
				System.out.print("DNI: " + dni);
				System.out.print(", Nombre: " + nombre);
				System.out.print(", Apellido: " + apellido + "\n");
			} else {
				throw new UsuarioInexistente("No existe el DNI introducido");
			}
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("¿Que dato desea actualizar? (nombre || apellido || ambos)");
			String dato = sc.next();
			
			switch(dato) {
				// Actualizar nombre
				case "nombre": {
					System.out.print("Nuevo nombre: ");
					String nombre = sc.next();
					
					slqUpdate = "UPDATE usuarios SET nombre = '" + nombre + "' WHERE dni = '" + vdni + "'";
					stmt.executeUpdate(slqUpdate);
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				// Actualizar apellido
				case "apellido": {
					System.out.print("Nuevo apellido: ");
					String apellido = sc.next();
					slqUpdate = "UPDATE usuarios SET apellido = '" + apellido + "' WHERE dni = '" + vdni + "'";
					stmt.executeUpdate(slqUpdate);
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				case "ambos": {
					System.out.print("Nuevo nombre: ");
					String nombre = sc.next();
					System.out.print("Nuevo apellido: ");
					String apellido = sc.next();
					slqUpdate = "UPDATE usuarios SET nombre = '" + nombre + "', apellido = '" + apellido + "' WHERE dni = '" + vdni + "'";
					stmt.executeUpdate(slqUpdate);
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				default: {
					System.out.println();
					System.out.println("No has seleccionado una opcion valida.");
				}
			}			
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch (SQLException se) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}