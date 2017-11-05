import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroUsuariosArrayList {
	
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static ArrayList<Usuario> nuevosUsuarios = new ArrayList<Usuario>();
	private static ArrayList<Usuario> borrarUsuarios = new ArrayList<Usuario>();
	private static ArrayList<Usuario> actualizarUsuarios = new ArrayList<Usuario>();
	
	// Constructor privado
	private void RegistroUsuariosArrayList() {
		
	}
	
	public static void lecturaUsuarios() {
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
			
			//STEP 5: Extract data from result set

			while(rs.next()) {
				//Retrieve by column name
				//rs.getInt
				String dni  = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				Usuario usuario = new Usuario();
				
				usuario.setDni(dni);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				
				usuarios.add(usuario);
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
		boolean existe = false;
		
		for (int i=0; i<usuarios.size(); i++) {
			if (usuarios.get(i).dni.equalsIgnoreCase(v_dni)) {
				existe = true;
				
				if (existe) {
					break;
				}
			} else {
				existe = false;
			}
		}
		
		if (existe) {
			throw new UsuarioExistente("El DNI introducido ya está registrado");
		} else {
			
			Usuario usuario = new Usuario();
			
			usuario.setDni(v_dni);
			usuario.setNombre(v_nombre);
			usuario.setApellido(v_apellido);
			
			usuarios.add(usuario);
			nuevosUsuarios.add(usuario);
			
			System.out.println("Usuario añadido correctamente");
		}
	}
	
	public static void buscarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		boolean existe = true;
		if (usuarios.size() == 0) {
			throw new UsuarioInexistente("No hay usuarios");
		}
		
		for (int i=0; i<usuarios.size(); i++) {
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
				System.out.print("DNI: " + usuarios.get(i).dni);
				System.out.print("\nNombre: " + usuarios.get(i).nombre);
				System.out.print("\nApellido: " + usuarios.get(i).apellido + "\n");
				existe = true;
				break;
			} else {
				existe = false;
			}
		}
		
		if (!existe) {
			throw new UsuarioInexistente("No existe el DNI introducido");
		}
	}
	
	public static void mostrarUsuariosMySQL() throws UsuarioInexistente {

		
		if (usuarios.size() == 0) {
			//System.out.println("No hay usuarios");
			throw new UsuarioInexistente("No hay usuarios");
		}
	
		System.out.print("Usuarios:\n");
		for (int i=0; i<usuarios.size(); i++) {
			System.out.print("DNI: " + usuarios.get(i).dni);
			System.out.print("\nNombre: " + usuarios.get(i).nombre);
			System.out.print("\nApellido: " + usuarios.get(i).apellido);
			System.out.print("\n-------");
			System.out.print("\n");
		}
	}
	
	public static void borrarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		boolean existe = true;
		int indexBorrar = 0;

		if (usuarios.size() == 0) {
			throw new UsuarioInexistente("No hay usuarios");
		}
		
		for (int i=0; i<usuarios.size(); i++) {
			
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
				indexBorrar = i;
				existe = true;
				borrarUsuarios.add(usuarios.get(i));
				break;
			} else {
				existe = false;
			}
		}
		
		if (!existe) {
			throw new UsuarioInexistente("No existe el DNI introducido");
		} else {
			usuarios.remove(indexBorrar);
			for (int i=0; i<nuevosUsuarios.size(); i++) {
				if (nuevosUsuarios.get(i).getDni().equals(vdni)) {
					nuevosUsuarios.remove(i);
				}
			}
			
			System.out.println("Usuario borrado correctamente");
			System.out.println("");
		}
	}
	
	public static void actualizarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		
		boolean existe = true;
		String nombreUsuario = "";
		String apellidoUsuario = "";
		int index = 0;
		
		for (int i=0; i<usuarios.size(); i++) {
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
				System.out.print("DNI: " + usuarios.get(i).dni);
				System.out.print(", Nombre: " + usuarios.get(i).nombre);
				System.out.print(", Apellido: " + usuarios.get(i).apellido + "\n");
				nombreUsuario = usuarios.get(i).nombre;
				apellidoUsuario = usuarios.get(i).apellido;
				existe = true;
				index = i;
				break;
			} else {
				existe = false;
			}
		}
		
		if (!existe) {
			throw new UsuarioInexistente("No existe el DNI introducido");
		} else {
			usuarios.remove(index);
			
			for (int i=0; i<nuevosUsuarios.size(); i++) {
				if (nuevosUsuarios.get(i).getDni().equals(vdni)) {
					nuevosUsuarios.remove(i);
				}
			}
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("ArrayList ¿Que dato desea actualizar? (nombre || apellido || ambos)");
			String dato = sc.next();
			
			switch(dato) {
				// Actualizar nombre
				case "nombre": {
					System.out.print("Nuevo nombre: ");
					String nuevoNombre = sc.next();
					
					Usuario usuario = new Usuario();
					usuario.setDni(vdni);
					usuario.setNombre(nuevoNombre);
					usuario.setApellido(apellidoUsuario);
					actualizarUsuarios.add(usuario);
					usuarios.add(usuario);
					
					System.out.println("Usuario actualizado correctamente");
					
					break;
				}
				
				// Actualizar apellido
				case "apellido": {
					System.out.print("Nuevo apellido: ");
					String nuevoApellido = sc.next();
					
					Usuario usuario = new Usuario();
					usuario.setDni(vdni);
					usuario.setNombre(nombreUsuario);
					usuario.setApellido(nuevoApellido);
					actualizarUsuarios.add(usuario);
					usuarios.add(usuario);
					
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				// Actualizado nombre y apellido
				case "ambos": {
					System.out.print("Nuevo nombre: ");
					String nuevoNombre = sc.next();
					System.out.print("Nuevo apellido: ");
					String nuevoApellido = sc.next();
					
					Usuario usuario = new Usuario();
					usuario.setDni(vdni);
					usuario.setNombre(nuevoNombre);
					usuario.setApellido(nuevoApellido);
					actualizarUsuarios.add(usuario);
					usuarios.add(usuario);
					
					System.out.println("Usuario actualizado correctamente");
					break;
				}
				
				default: {
					System.out.println();
					System.out.println("No has seleccionado una opcion valida.");
				}
			}
		}
	}
	
	public static void guardarRegistro() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Conexion
			conn = Conexion.ConexionMySQL();
			
			//STEP 4: Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();

			
			for (int i=0; i<borrarUsuarios.size(); i++) {
				System.out.println("*** GUARDAR REGISTRO *** DELETE");
				String sqlDelete;
				sqlDelete = "DELETE FROM usuarios WHERE dni = '" + borrarUsuarios.get(i).dni + "'";
				stmt.executeUpdate(sqlDelete);
				System.out.print("DNI: " + borrarUsuarios.get(i).dni);
				System.out.print(", Nombre: " + borrarUsuarios.get(i).nombre);
				System.out.print(", Apellido: " + borrarUsuarios.get(i).apellido + "\n");
				//borrarUsuarios.remove(i);
			}

			for (int i=0; i<nuevosUsuarios.size(); i++) {
				System.out.println("*** GUARDAR REGISTRO *** INSERTAR");
				String sqlInsert;
				sqlInsert = "INSERT INTO usuarios(dni, nombre, apellido) VALUES('" + nuevosUsuarios.get(i).dni + "', '" + nuevosUsuarios.get(i).nombre + "', '" + nuevosUsuarios.get(i).apellido + "')";
				stmt.executeUpdate(sqlInsert);
				System.out.print("DNI: " + nuevosUsuarios.get(i).dni);
				System.out.print(", Nombre: " + nuevosUsuarios.get(i).nombre);
				System.out.print(", Apellido: " + nuevosUsuarios.get(i).apellido + "\n");
				//nuevosUsuarios.remove(i);
			}

			for (int i=0; i<actualizarUsuarios.size(); i++) {
				System.out.println("*** GUARDAR REGISTRO *** UPDATE");
				String slqUpdate;
				slqUpdate = "UPDATE usuarios SET nombre = '" + actualizarUsuarios.get(i).nombre + "', apellido = '" + actualizarUsuarios.get(i).apellido + "' WHERE dni = '" + actualizarUsuarios.get(i).dni + "'";
				stmt.executeUpdate(slqUpdate);
				System.out.print("DNI: " + actualizarUsuarios.get(i).dni);
				System.out.print(", Nombre: " + actualizarUsuarios.get(i).nombre);
				System.out.print(", Apellido: " + actualizarUsuarios.get(i).apellido + "\n");
				//actualizarUsuarios.remove(i);
			}
			
			
			// *** FUNCIONA -- FORMA INCORRECTA ***
			/*String sqlDelete;
			sqlDelete = "DELETE FROM usuarios";
			stmt.executeUpdate(sqlDelete);
			
			for (int i=0; i<usuarios.size(); i++) {
				String sqlInsert;
				sqlInsert = "INSERT INTO usuarios(dni, nombre, apellido) VALUES('" + usuarios.get(i).dni + "', '" + usuarios.get(i).nombre + "', '" + usuarios.get(i).apellido + "')";
				stmt.executeUpdate(sqlInsert);
			}*/			
				
			
			System.out.println("Registro guardado correctamente");
			
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
	
	public static void leerRegistro() {
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
}
