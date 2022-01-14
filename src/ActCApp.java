/**
 * 
 * Miquel Angel Montero ActB 14/01/2022 
 * 
 */
import java.util.Hashtable;
import javax.swing.JOptionPane;
public class ActCApp {

	public static void main(String[] args) {
		
		//Variables aux
		int opcion = 0;
		
		//Crear diccionario
		Hashtable<String, Double> tablaProductos = new Hashtable<String, Double>();
		tablaProductos.put("PR1", 10.00);
		tablaProductos.put("PR2", 52.60);
		tablaProductos.put("PR3", 3.50);
		tablaProductos.put("PR4", 16.00);
		tablaProductos.put("PR5", 13.00);
		
		//Menú
		do {
			String opcionS = JOptionPane.showInputDialog("Escoje una función:\n1.Añadir un producto nuevo \n2.Consultar un producto \n3.Consultar todos los productos\n4.Salir");
			opcion = Integer.parseInt(opcionS);
			
			switch (opcion) {
				case 1:
					añadirProducto(tablaProductos);
					break;
				case 2:
					String codigo = JOptionPane.showInputDialog("Introduce el codigo del producto que quieres mostrar");
					mostrarProducto(tablaProductos, codigo);
					break;
				case 3:
					mostrarProductosAll(tablaProductos);
					break;
				case 4:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Escoje un número entre el 1 y el 4");
					break;
			}
		}while(opcion!=4);
	}
	
	
	//Metodo para añadir productos
	public static void añadirProducto(Hashtable<String, Double> tabla) {
		
		String codigo = JOptionPane.showInputDialog("Instera el codigo del producto");
		String precioS = JOptionPane.showInputDialog("Inserta el precio del producto");
		double precio = Double.parseDouble(precioS);
		
		tabla.put(codigo, precio);		
	}
	
	//Metodo para mostrar un producto
	public static void mostrarProducto(Hashtable<String, Double> tabla, String codigo) {
		
		int correcto = 0;
		
		for (String i : tabla.keySet()) {
			if(i.equals(codigo)) {
				System.out.println("El producto seleccionador es: "+i+" y vale "+tabla.get(i)+"€");
				correcto = 1;
			}
		}
		if (correcto == 1) {
			
		}else {
			System.out.println("El producto introducido no se ha encontrado");
		}
		
	}
	
	//Metodo para mostrar todos los productos
	public static void mostrarProductosAll(Hashtable<String, Double> tabla) {
		
		System.out.println("Codigo | Precio");
		
		for (String i : tabla.keySet()) {
			System.out.println(i+" | "+tabla.get(i));
		}
		
	}
	
}
