import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

public class Principal {
	
	public static void main (String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList <FiguraGeometrica> alFiguras = new ArrayList <FiguraGeometrica> ();
		
		int opcion = -1;
		
		do {
			try {
				System.out.println("****** MENÚ ******:");
				System.out.println("1. Añadir Círculo");
				System.out.println("2. Añadir Rectangulo");
				System.out.println("3. Añadir Punto");
				System.out.println("4. Sumar Superficie");
				System.out.println("5. Sumar Perímetro");
				System.out.println("6. Mostrar Figuras");
				System.out.println("7. Eliminar Figuras");
				System.out.println("0. Terminar");
				
				System.out.print("\nIntroduce una opcion: ");
				opcion = sc.nextInt();
				
				switch(opcion) {
					// Añadir Círculo
					case 1: {
						System.out.println("Añadir circulo");
						Circulo circulo = new Circulo(sc);
						alFiguras.add(circulo);
						System.out.println("Circulo añadido");
						break;
					}
					
					// Añadir Rectangulo
					case 2: {
						System.out.println("Añadir rectangulo");
						Rectangulo rectangulo = new Rectangulo(sc);
						//Rectangulo rectangulo = new Rectangulo(sc.nextInt(), sc.nextInt());
						alFiguras.add(rectangulo);
						System.out.println("Rectangulo añadido");
						break;
					}
					
					// Añadir Punto
					case 3: {
						System.out.println("Añadir punto");
						Punto punto = new Punto(sc);
						//Punto punto = new Punto(sc.nextInt(), sc.nextInt());
						alFiguras.add(punto);
						System.out.println("Punto añadido");
						break;
					}
					
					// Sumar Superficie
					case 4: {
						System.out.println("Sumar superficie");
						double sumaTotalSuperficie = 0;
			
						for (int i=0; i<alFiguras.size(); i++) {
							sumaTotalSuperficie += alFiguras.get(i).calcularSuperficie();
						}
						
						// Mostrar únicamente dos decimales --> df.format(number)
						DecimalFormat df = new DecimalFormat("#.00");
						System.out.println("Suma de la superficie: " + df.format(sumaTotalSuperficie));
						break;
					}
					
					// Sumar Perímetro
					case 5: {
						System.out.println("Sumar perimetro");
						double sumaTotalPerimetro = 0;
			
						for (int i=0; i<alFiguras.size(); i++) {
							sumaTotalPerimetro += alFiguras.get(i).calcularPerimetro();
						}
						System.out.println("Suma del perimetro: " + sumaTotalPerimetro);
						break;
					}
					
					// Mostrar Figuras
					case 6: {
						System.out.println("Mostrar figuras");
						
						if (alFiguras.size() <= 0) {
							System.out.println("No hay figuras guardadas");
						} else {
							for (int i=0; i<alFiguras.size(); i++) {
								//System.out.println(i+1 +": " + alFiguras.get(i).imprimir());
								System.out.print("Posición: " + i + ": ");
								alFiguras.get(i).imprimir();
							}
						}
						break;
					}
					
					// Eliminar Figuras
					case 7: {
						System.out.println("Eliminar figuras");
						
						if (alFiguras.size() <= 0) {
							System.out.println("No hay figuras guardadas");
						} else {
							System.out.print("Indica la posición a eliminar: ");
							int posicionEliminar = sc.nextInt();
							
							if (posicionEliminar <= 0 || posicionEliminar > alFiguras.size()) {
								System.out.println("Error. No existe la posición indicada");
							} else {
								alFiguras.remove(posicionEliminar);
							}
						}
						break;
					}
					
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
