import java.util.Scanner;

public class Rectangulo extends FiguraGeometrica implements Imprimible {
	
	public Rectangulo(int puntoX, int puntoY) {
		super(puntoX, puntoY);
		// TODO Auto-generated constructor stub
	}
	
	public Rectangulo(Scanner sc) {
		super(sc);
		System.out.println("Rectangulo");
		System.out.println("Introduce la altura");
		setAltura(sc.nextDouble());
		System.out.println("Introduce la base");
		setBase(sc.nextDouble());
	}
	
	private double altura, base;
	
	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}
	
	// Área
	@Override
	public Double calcularSuperficie() {
		//this.area = altura*base;
		//System.out.println("La superficie del rectangulo es: " + area);
		return altura*base;
	}
	
	// Perímetro
	@Override
	public Double calcularPerimetro() {
		//this.perimetro = altura*2 + base*2;
		//System.out.println("El perímetro del rectangulo es: " + perimetro);
		return altura*2 + base*2;
	}
	
	public void mostrarRectangulo() {
		System.out.println("Rectangulo posición X: " + getPuntoX() + " posición Y: " + getPuntoY() + " altura: " + altura + " base: " + base);
	}
	
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		System.out.println("\n	Rectangulo posición X: " + getPuntoX() + " posición Y: " + getPuntoY() + " altura: " + altura + " base: " + base + " superficie: " + calcularSuperficie() + " perimetro: " + calcularPerimetro());
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
