import java.util.Scanner;

public abstract class FiguraGeometrica implements Imprimible {

	//Base
	private int puntoX;
	//Altura
	private int puntoY;
	
	/*public abstract int perimetro();
	public abstract int superficie();*/
	
	public FiguraGeometrica(Scanner sc) {
		System.out.println("Figura Geometrica");
		System.out.println("Introduce la posición X:");
		setPuntoX(sc.nextInt());
		System.out.println("Introduce la posición Y:");
		setPuntoY(sc.nextInt());
	}
	
	public Double calcularSuperficie() {
		return 0.0;
	}
	
	public Double calcularPerimetro() {
		return 0.0;
	}

	public double getPuntoX() {
		return puntoX;
	}

	public void setPuntoX(int puntoX) {
		this.puntoX = puntoX;
	}

	public double getPuntoY() {
		return puntoY;
	}

	public void setPuntoY(int puntoY) {
		this.puntoY = puntoY;
	}
	
	public FiguraGeometrica(int puntoX, int puntoY) {
		this.setPuntoX(puntoX);
		this.setPuntoY(puntoY);
	}
	
	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void darFormato() {
		// TODO Auto-generated method stub
		
	}
}
