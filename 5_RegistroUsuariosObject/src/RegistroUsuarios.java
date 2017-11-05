import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RegistroUsuarios {
	
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	// Constructor privado
	private void RegistroUsuario() {
		
	}

	// Leer usuarios del fichero
	// Se anyaden al ArrayList
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
	
	// Anyadir usuario al fichero
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
			throw new UsuarioExistente("El DNI introducido ya esta registrado");
		} else {
			
			Usuario usuario = new Usuario();
			
			usuario.setDni(vdni);
			usuario.setNombre(vnombre);
			usuario.setApellido(vapellido);
			
			usuarios.add(usuario);
			
			System.out.println("Usuario anyadido correctamente");
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
	
	// *** OBJECT ***
	// https://beginnersbook.com/2013/12/how-to-serialize-arraylist-in-java
	
	// Guardar ArrayList en fichero binario
	public static void guardarRegistro() {
		try {
			FileOutputStream fos = new FileOutputStream("usuariosObject.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(usuarios);
			oos.close();
			fos.close();
			System.out.println("Registro binario guardado correctamente");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Leer archivo binario
	public static void leerRegistro() {
		ArrayList <Usuario> alUsuario = new ArrayList<Usuario>();
		try {
			FileInputStream fis = new FileInputStream("usuariosObject.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			alUsuario = (ArrayList)ois.readObject();
			ois.close();
			fis.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch(ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
		
		System.out.print("Usuarios Binarios:\n");
		for (Usuario tmp: alUsuario) {
			System.out.print("DNI: " + tmp.getDni());
			System.out.print("\nNombre: " + tmp.getNombre());
			System.out.print("\nApellido: " + tmp.getApellido());
			System.out.print("\n-------");
			System.out.print("\n");
		}
	}


}