import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RegistroUsuarios {
	
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	// Constructor privado
	private void RegistroUsuario() {
		
	}
	
	// Leer usuarios del fichero
	// Se añaden al ArrayList
	public static void lecturaUsuarios() {
		usuarios.clear();
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
	
	// Añadir usuario al fichero
	// Comprobando DNI desde el ArrayList
	public static void anyadirUsuario(String vdni, String vnombre, String vapellido) throws UsuarioExistente {
		try {
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
				File TextFile = new File("usuarios.txt"); 
				FileWriter TextOut = new FileWriter(TextFile, true);
				
				TextOut.write(vdni + ", ");
				TextOut.write(vnombre + ", ");
				TextOut.write(vapellido + "\n");
				TextOut.close();
				
				Usuario usuario = new Usuario();
				
				usuario.setDni(vdni);
				usuario.setNombre(vnombre);
				usuario.setApellido(vapellido);
				
				usuarios.add(usuario);
				
				System.out.println("Usuario añadido correctamente");
			}
		} catch(IOException e) {
			System.out.println("Error E/S: "+e);
		}
	}
	
	// Borrar usuario al fichero
	// Creando un fichero temporal
	// Comprobar DNI desde ArrayList
	// http://www.javadb.com/remove-a-line-from-a-text-file/
	public static void borrarUsuario(String vdni) throws UsuarioInexistente {

		String dni = null, nombre = null, apellido = null;
		boolean existe = true;

		if (usuarios.size() == 0) {
			throw new UsuarioInexistente("No hay usuarios");
		}
		
		for (int i=0; i<usuarios.size(); i++) {
			
			if (usuarios.get(i).dni.equalsIgnoreCase(vdni)) {
				dni = usuarios.get(i).dni;
				nombre = usuarios.get(i).nombre;
				apellido = usuarios.get(i).apellido;
				
				existe = true;
				break;
			} else {
				existe = false;
			}
		}
		
		if (!existe) {
			throw new UsuarioInexistente("No existe el DNI introducido");
		} else {
			try {
				// Variable para borrar línea del fichero
				String contenido = dni + ", " + nombre + ", " + apellido;
				
				// Nombre del fichero
				String file = "usuarios.txt";
	            File inFile = new File(file);
	            if (!inFile.isFile()) {
	                System.out.println("Parameter is not an existing file");
	                return;
	            }
	            
	            //Construct the new file that will later be renamed to the original filename. 
	            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	            
	            String line;
	            //Read from the original file and write to the new 
	            //unless content matches data to be removed.
	            while ((line = br.readLine()) != null) {
	                if (!line.trim().equals(contenido)) {
	                    pw.print(line + "\n");
	                    pw.flush();
	                }
	            }
	            pw.close();
	            br.close();
	            System.out.println("Usuario borrado correctamente");
				System.out.println("");
	 
	            //Delete the original file
	            if (!inFile.delete()) {
	                System.out.println("Could not delete file");
	                return;
	            }
	            //Rename the new file to the filename the original file had.
	            if (!tempFile.renameTo(inFile))
	                System.out.println("Could not rename file");
	 
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		}
	}
}