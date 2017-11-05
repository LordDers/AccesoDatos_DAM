import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class Principal {
	
	public static void leerFichero(String fichero) throws IOException {
		// Leer fichero
		//File fichero = new File("C:\\Users\\Usuario6i\\eclipse-workspace\\LecturaFicheros\\fich.txt");
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		
		String linea = br.readLine();
		
		while(linea != null) {
			System.out.println(linea);
			// Leemos la siguiente linea
			linea = br.readLine();
		}
		
		br.close();
	}
	
	public static void crearFichero(String fichero) throws IOException {
		FileWriter fw = new FileWriter(fichero, true);
		fw.write("sssssssssss");
		System.out.println("Archivo creado correctamente");
		fw.close();
	}
	
	public static void leerFicheros(String ficheros[]) throws IOException {
		
		String thisLine;
		
		//Loop across the arguments
	   for (int i=0; i < ficheros.length; i++) {
	 
	     //Open the file for reading
	     try {
	       BufferedReader br = new BufferedReader(new FileReader(ficheros[i]));
	       while ((thisLine = br.readLine()) != null) { // while loop begins here
	         System.out.println(thisLine);
	       } // end while 
	       System.out.println("---------------------");
	     } // end try
	     catch (IOException e) {
	       System.err.println("El archivo no existe");
	     }
	  } // end for
	}
	
	public static void main (String args[]) throws IOException {
		
		//leerFichero(args[0]);
		leerFicheros(args);
		//crearFichero("crear.txt");
	}
}
