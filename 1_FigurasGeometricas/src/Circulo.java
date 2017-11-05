import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

public class Circulo extends FiguraGeometrica implements Imprimible {
	
	public Circulo(int puntoX, int puntoY) {
		super(puntoX, puntoY);
		// TODO Auto-generated constructor stub
	}

	//PRUEBAS
	public Circulo(Scanner sc) {
		// TODO Auto-generated constructor stub
		super(sc);
		/*System.out.println("Circulo");
		System.out.println("Introduce la superficie");
		setArea(sc.nextInt());
		System.out.println("Introduce el perímetro");
		setCircunferencia(sc.nextInt());*/
		
		
		/*System.out.println("Introduce el radio:");
		setRadio(sc.nextInt());*/
		
		int num = 0;
		boolean loop = true;
		while (loop) {
            try {
                System.out.println("Introduce el radio:");
                setRadio(sc.nextInt());

                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid value!");
                sc.next();
            } 
        }
		
		/*calcularSuperficie();
		calcularPerimetro();*/
	}

	private double area;
	private double circunferencia = 0;
	private double radio;

	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		
		// Mostrar únicamente dos decimales --> df.format(number)
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println("\n	Círculo posición X: " + getPuntoX() + " posición Y: " + getPuntoY() + " radio: " + radio + " superficie: " + df.format(calcularSuperficie()) + " perimetro: " + calcularPerimetro());
		System.out.println("-------------------------------------");
	}

	@Override
	public void darFormato() {
		// TODO Auto-generated method stub
		
	}

	public double getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public double getCircunferencia() {
		return circunferencia;
	}

	public void setCircunferencia(int circunferencia) {
		this.circunferencia = circunferencia;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	// Área
	@Override
	public Double calcularSuperficie() {
		this.area = 3.14f * radio * radio;
		//System.out.println("La superficie del círculo es: " + area);
		return area;
	}
	
	// Circunferencia
	@Override
	public Double calcularPerimetro() {
		this.circunferencia = 2 * 3.1416f * radio;
		//System.out.println("El perímetro del círculo es: " + circunferencia);
		return circunferencia;
	}
	
	public void mostrarCirculo() {
		System.out.println("Círculo posición X: " + getPuntoX() + " posición Y: " + getPuntoY() + " radio: " + radio);
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
