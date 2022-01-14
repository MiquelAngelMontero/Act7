/**
 * 
 * Miquel Angel Montero ActA 14/01/2022 
 * 
 */
import java.util.Hashtable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ActAApp {


	public static void main(String[] args) {

		int opcion = 0;
		Hashtable<String, Double> tabla = new Hashtable<String, Double>();
		
		
		//bucle del menú
		while(opcion!=3) {
			
			String opcionS = JOptionPane.showInputDialog("Que funcion quieres realizar:\n1.Añadir alumno\n2.Mostrar alumnos\n3.Salir");
			opcion = Integer.parseInt(opcionS);
			
			switch(opcion) {
			
			case 1:
				añadirAlumno(tabla);
				break;
			case 2:
				mostrarAlumnos(tabla);
				break;
			case 3:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Introduce un numero del 1 al 3");
			
			}	
			
		}

	}
	
	public static void añadirAlumno(Hashtable<String, Double> tabla) {	
		double nota, media, suma = 0;
		String notaS;
		ArrayList<Double> notas = new ArrayList<>();//Array para guardar las notas
		String nombre = JOptionPane.showInputDialog("Introduce el nombre del alumno:");
		
		//Pedir notas y guardarlas en la array
		do {
			
			notaS = JOptionPane.showInputDialog("Introduce una nota (Escribe 0 para salir)");
			nota = Double.parseDouble(notaS);
			notas.add(nota);
					
		}while(nota != 0);
				
		//Calcular media
		for(int i=0; i<notas.size();i++) {
			suma = suma + notas.get(i);
		}
				
		media = suma / (notas.size()-1);
		
		media = Math.round(media*100)/100;
		
		tabla.put(nombre, media);
					
	}
	
	
	public static void mostrarAlumnos(Hashtable<String, Double> tabla) {
		
		System.out.println("Nombre / Nota media");
		
		//Assignamos la key(String nombre) a i, seguidamente hacemos un get de i para obtener el valor de la clave.
		for (String i : tabla.keySet()) {
			System.out.println(i+" / "+tabla.get(i));
		}
		
	}

}
