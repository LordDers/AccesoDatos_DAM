import java.util.Scanner;

public class Punto extends FiguraGeometrica implements Imprimible {

	public Punto(int puntoX, int puntoY) {
		super(puntoX, puntoY);
		// TODO Auto-generated constructor stub
	}
	
	public Punto(Scanner sc) {
		super(sc);
	}

	// �rea
	@Override
	public Double calcularSuperficie() {
		//System.out.println("La superficie del punto es: " + area);
		return 0.0;
	}
	
	// Per�metro
	@Override
	public Double calcularPerimetro() {
		//System.out.println("El per�metro del punto es: " + circunferencia);
		return 0.0;
	}
	
	public void mostrarCirculo() {
		System.out.println("Punto posici�n X: " + getPuntoX() + " posici�n Y: " + getPuntoY());
	}
	
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		System.out.println("\n	Punto posici�n X: " + getPuntoX() + " posici�n Y: " + getPuntoY() + " superficie: " + calcularSuperficie() + " perimetro: " + calcularPerimetro());
		System.out.println("-------------------------------------");
	}
	
	@Override
	public void darFormato() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double superficie() {
		// TODO Auto-generated method stub
		return 0;
	}*/
}
