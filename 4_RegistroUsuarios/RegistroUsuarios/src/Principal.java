import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = -1;
		
		//RegistroUsuarios.lecturaUsuarios();
		
		do {
			try {
				System.out.println("****** MENÚ ******:");
				System.out.println("1. Añadir Usuario");
				System.out.println("2. Consultar Usuario");
				System.out.println("3. Mostrar Usuarios");
				System.out.println("4. Eliminar Usuario");
				//System.out.println("5. Guardar Registro Usuarios");
				System.out.println("0. Terminar");
				
				System.out.print("\nIntroduce una opcion: ");
				opcion = sc.nextInt();
				
				switch(opcion) {
					// Añadir Usuario
					case 1: {
						System.out.println("*** Añadiendo Usuario ***");
						System.out.print("DNI del usuario: ");
						String dni = sc.next();
						System.out.print("Nombre del usuario: ");
						String nombre = sc.next();
						System.out.print("Apellido del usuario: ");
						String apellido = sc.next();
						
						RegistroUsuarios.lecturaUsuarios();
						try {
							RegistroUsuarios.anyadirUsuario(dni, nombre, apellido);
						} catch(UsuarioExistente e) {
							//e.getMessage();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Consultar Usuario
					case 2: {
						System.out.println("*** Consultando Usuario ***");
						System.out.print("DNI del usuario: ");
						String dni = sc.next();
						RegistroUsuarios.lecturaUsuarios();
						try {
							RegistroUsuarios.buscarUsuario(dni);
						} catch(UsuarioInexistente e) {
							//e.getMessage();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Mostrar usuarios
					case 3: {
						System.out.println("*** Mostrando Usuarios ***");
						RegistroUsuarios.lecturaUsuarios();
						try {
							RegistroUsuarios.mostrarUsuarios();
						} catch(UsuarioInexistente e) {
							//e.getMessage();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Eliminar Usuario
					case 4: {
						System.out.println("*** Eliminando Usuario ***");
						RegistroUsuarios.lecturaUsuarios();
						System.out.print("DNI del usuario a BORRAR: ");
						String dni = sc.next();
						try {
							RegistroUsuarios.borrarUsuario(dni);
						} catch (UsuarioInexistente e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Guardar Registro Usuario
					/*case 5: {
						break;
					}*/
					
					// Terminar
					case 0: {
						break;
					}
					
					default: {
						System.out.println();
						System.out.println("No has seleccionado una opción válida.");
					}
				}
			} catch (InputMismatchException e) {
				 System.out.println("Debes introducir un número.\n");
				 sc.nextLine();
			}
		} while (opcion != 0);
		sc.close();
		
		
	}

}
