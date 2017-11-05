import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrincipalArrayList {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = -1;
		
		RegistroUsuariosArrayList.lecturaUsuarios();
		
		do {
			try {
				System.out.println("****** MENU MySQL ARRAYLIST******:");
				System.out.println("1. Anyadir Usuario");
				System.out.println("2. Consultar Usuario");
				System.out.println("3. Mostrar Usuarios");
				System.out.println("4. Eliminar Usuario");
				System.out.println("5. Actualizar Usuario");
				System.out.println("6. Guardar Registro");
				System.out.println("7. Leer Registro");
				System.out.println("0. Terminar");
				
				System.out.print("\nIntroduce una opcion: ");
				opcion = sc.nextInt();
				
				switch(opcion) {
					// Anyadir Usuario
					case 1: {
						System.out.println("*** Anyadiendo Usuario ***");
						System.out.print("DNI del usuario: ");
						String dni = sc.next();
						System.out.print("Nombre del usuario: ");
						String nombre = sc.next();
						System.out.print("Apellido del usuario: ");
						String apellido = sc.next();

						try {
							RegistroUsuariosArrayList.anyadirUsuarioMySQL(dni, nombre, apellido);
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
						try {
							RegistroUsuariosArrayList.buscarUsuarioMySQL(dni);
						} catch(UsuarioInexistente e) {
							//e.getMessage();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Mostrar usuarios
					case 3: {
						System.out.println("*** Mostrando Usuarios ***");
						try {
							RegistroUsuariosArrayList.mostrarUsuariosMySQL();
						} catch(UsuarioInexistente e) {
							//e.getMessage();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					// Eliminar Usuario
					case 4: {
						System.out.println("*** Eliminando Usuario ***");
						System.out.print("DNI del usuario a BORRAR: ");
						String dni = sc.next();
						try {
							RegistroUsuariosArrayList.borrarUsuarioMySQL(dni);
						} catch (UsuarioInexistente e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							System.out.println(e.getMessage());
						}
						break;
					}
					
					case 5: {
						System.out.println("*** Actualizando Usuario ***");
						System.out.print("DNI del usuario a ACTUALIZAR: ");
						String dni = sc.next();
						try {
							RegistroUsuariosArrayList.actualizarUsuarioMySQL(dni);
						} catch (UsuarioInexistente e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
						break;
					}
					
					case 6: {
						RegistroUsuariosArrayList.guardarRegistro();
						break;
					}
					
					case 7: {
						RegistroUsuariosArrayList.leerRegistro();
						break;
					}
					
					// Terminar
					case 0: {
						break;
					}
					
					default: {
						System.out.println();
						System.out.println("No has seleccionado una opcion valida.");
					}
				}
			} catch (InputMismatchException e) {
				 System.out.println("Debes introducir un numero.\n");
				 sc.nextLine();
			}
		} while (opcion != 0);
		sc.close();
	}

}