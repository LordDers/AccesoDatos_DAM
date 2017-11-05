import java.sql.*;
import java.util.Scanner;

import org.sqlite.SQLiteException;

public class RegistroUsuarios {

	// Constructor privado
	private void RegistroUsuario() {
		
	}
	
	public static void crearTabla() {
		
		Statement stmt = null;
		
		try {
			// Conexion
			Connection conn = ConexionSQLite.Conexion();
			
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n" +
					"dni VARCHAR(9),\n" +
					" nombre VARCHAR(30),\n " + 
					" apellido VARCHAR(30))";
			stmt.executeUpdate(sql);
			
			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Operation done successfully");
	}
	
	public static void mostrarUsuariosMySQL() throws UsuarioInexistente {
		
		Statement stmt = null;
		
		try {
			// Conexion
			Connection conn = ConexionSQLite.Conexion();
			conn.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql = "SELECT dni, nombre, apellido FROM usuarios";
			/*PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();*/
			ResultSet rs = stmt.executeQuery(sql);
			conn.commit();
			
			//STEP 5: Extract data from result set
			/*while(rs.next()) {
				//Retrieve by column name
				//rs.getInt
				String dni  = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				//Display values
				System.out.print("DNI: " + dni);
				System.out.print(", Nombre: " + nombre);
				System.out.print(", Apellido: " + apellido + "\n");
			}*/
			
			if (!rs.next()) {
				throw new UsuarioInexistente("No hay usuarios");
			} else {
				do {
					String dni  = rs.getString("dni");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					
					//Display values
					System.out.print("DNI: " + dni);
					System.out.print(", Nombre: " + nombre);
					System.out.print(", Apellido: " + apellido + "\n");
				} while (rs.next());
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLiteException se) {
			se.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Operation done successfully");
	}
	
	public static void anyadirUsuarioMySQL(String v_dni, String v_nombre, String v_apellido) throws UsuarioExistente {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = ConexionSQLite.Conexion();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String sqlSelect;
			//sqlSelect = "SELECT * FROM usuarios WHERE dni = '" + v_dni + "'";
			sqlSelect = "SELECT * FROM usuarios WHERE dni = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, v_dni);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			
			if (rs.next()) {
				throw new UsuarioExistente("El DNI introducido ya esta registrado");
			} else {
				String sqlInsert;
				//sqlInsert = "INSERT INTO usuarios(dni, nombre, apellido) VALUES('" + v_dni + "', '" + v_nombre + "', '" + v_apellido + "')";
				sqlInsert = "INSERT INTO usuarios(dni, nombre, apellido) VALUES(?, ?, ?)";
				pstmt = conn.prepareStatement(sqlInsert);
				pstmt.setString(1, v_dni);
	            pstmt.setString(2, v_nombre);
	            pstmt.setString(3, v_apellido);
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("Usuario anyadido correctamente");
				pstmt.close();
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			pstmt.close();
			conn.close();
		} catch (SQLiteException se) {
			se.printStackTrace();
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
	}
	
	public static void buscarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			// Conexion
			Connection conn = ConexionSQLite.Conexion();
			conn.setAutoCommit(false);
			
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, vdni);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			
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
		PreparedStatement pstmt = null;
		
		try {
			// Conexion
			conn = ConexionSQLite.Conexion();
			conn.setAutoCommit(false);
			
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, vdni);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			
			if (rs.next()) {
				String sqlDelete;
				sqlDelete = "DELETE FROM usuarios WHERE dni = ?";
				pstmt = conn.prepareStatement(sqlDelete);
				// set the corresponding param
	            pstmt.setString(1, vdni);
	            // execute the delete statement
	            pstmt.executeUpdate();
	            conn.commit();
				System.out.println("Usuario borrado correctamente");
			} else {
				throw new UsuarioInexistente("No existe el DNI introducido");
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			pstmt.close();
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
				if (pstmt != null) {
					conn.close();
				}
			} catch(SQLException se) {
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
		PreparedStatement pstmt = null;
		
		String slqUpdate;
		
		try {
			// Conexion
			conn = ConexionSQLite.Conexion();
			conn.setAutoCommit(false);
			
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			String sqlSelect;
			sqlSelect = "SELECT * FROM usuarios WHERE dni = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setString(1, vdni);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			
			if (rs.next()) {
				System.out.println("llego if rs next");
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
					
					slqUpdate = "UPDATE usuarios SET nombre = ? WHERE dni = ?";
					pstmt = conn.prepareStatement(slqUpdate);
					// set the corresponding param
		            pstmt.setString(1, nombre);
		            pstmt.setString(2, vdni);
		            
		            // update 
		            pstmt.executeUpdate();
		            conn.commit();
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				// Actualizar apellido
				case "apellido": {
					System.out.print("Nuevo apellido: ");
					String apellido = sc.next();
					slqUpdate = "UPDATE usuarios SET apellido = ? WHERE dni = ?";
					pstmt = conn.prepareStatement(slqUpdate);
					// set the corresponding param
		            pstmt.setString(1, apellido);
		            pstmt.setString(2, vdni);
		            
		            // update 
		            pstmt.executeUpdate();
		            conn.commit();
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				case "ambos": {
					System.out.print("Nuevo nombre: ");
					String nombre = sc.next();
					System.out.print("Nuevo apellido: ");
					String apellido = sc.next();
					slqUpdate = "UPDATE usuarios SET nombre = ?, apellido = ? WHERE dni = ?";
					pstmt = conn.prepareStatement(slqUpdate);
					// set the corresponding param
					pstmt.setString(1, nombre);
		            pstmt.setString(2, apellido);
		            pstmt.setString(3, vdni);
		            
		            // update 
		            pstmt.executeUpdate();
		            conn.commit();
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				default: {
					System.out.println();
					System.out.println("No has seleccionado una opcion valida.");
				}
			}			
			
			//STEP 6: Clean-up environment
			sc.close();
			rs.close();
			stmt.close();
			pstmt.close();
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
				if (pstmt != null) {
					conn.close();
				}
			} catch(SQLException se) {
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