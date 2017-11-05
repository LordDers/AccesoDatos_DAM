import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RegistroUsuarios {
	
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	// Constructor privado
	private void RegistroUsuario() {
		
	}

	// Leer usuarios del fichero
	// Se añaden al ArrayList
	public static void lecturaUsuarios() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
			String linea = br.readLine();
			
			String [] camposSeparados = null;
			
			while(linea != null) {
				//System.out.println(linea);
				
				camposSeparados = linea.split(", ");
				
				Usuario usuario = new Usuario();
				
				usuario.setDni(camposSeparados[0]);
				usuario.setNombre(camposSeparados[1]);
				usuario.setApellido(camposSeparados[2]);
				
				usuarios.add(usuario);
				
				// Leemos la siguiente linea
				linea = br.readLine();
			}
			
			br.close();
			
		} catch(IOException e) {
			System.out.println("Error E/S: "+e);
		}
	}
	
	// Añadir usuario al fichero
	// Comprobando DNI desde el ArrayList
	public static void anyadirUsuario(String vdni, String vnombre, String vapellido) throws UsuarioExistente {

		boolean existe = false;
		
		for (int i=0; i<usuarios.size(); i++) {
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
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
			
			usuario.setDni(vdni);
			usuario.setNombre(vnombre);
			usuario.setApellido(vapellido);
			
			usuarios.add(usuario);
			
			System.out.println("Usuario añadido correctamente");
		}
	}
	
	public static void buscarUsuario(String vdni) throws UsuarioInexistente {
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
	
	// Mostrar usuarios
	// Leyendo del ArrayList
	public static void mostrarUsuarios() throws UsuarioInexistente {
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
	
	// Borrar usuario del ArrayList
	// Comprobar DNI desde ArrayList
	public static void borrarUsuario(String vdni) throws UsuarioInexistente {

		boolean existe = true;
		int indexBorrar = 0;

		if (usuarios.size() == 0) {
			throw new UsuarioInexistente("No hay usuarios");
		}
		
		for (int i=0; i<usuarios.size(); i++) {
			
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
				indexBorrar = i;
				existe = true;
				break;
			} else {
				existe = false;
			}
		}
		
		if (!existe) {
			throw new UsuarioInexistente("No existe el DNI introducido");
		} else {
			usuarios.remove(indexBorrar);
			System.out.println("Usuario borrado correctamente");
			System.out.println("");
		}
	}
	
	public static void guardarRegistro() {
		
		try {
			File TextFile = new File("usuarios.txt"); 
			FileWriter TextOut = new FileWriter(TextFile);
			for (int i=0; i<usuarios.size(); i++) {
				TextOut.write(usuarios.get(i).dni + ", ");
				TextOut.write(usuarios.get(i).nombre + ", ");
				TextOut.write(usuarios.get(i).apellido + "\n");
			}
			TextOut.close();
			
			System.out.println("Registro guardado correctamente");

		} catch (FileNotFoundException ex) {
            ex.printStackTrace();
		} catch(IOException e) {
			System.out.println("Error E/S: "+e);
		}
	}
}