/**
 * 
 * Miquel Angel Montero ActD 16/01/2022 
 * 
 */
import java.util.Hashtable;
import javax.swing.JOptionPane;
public class ActDApp {

	public static void main(String[] args) {
		
		//Variables aux
		int opcion = 0;
		int cont=0, iva = 0;
		double precioT = 0, cantidadPagada = 0, precioProd = 0, precioProdIva = 0, precioTIva = 0;
		
		//Definír diccionario de productos
		Hashtable<String, Double> tabla = new Hashtable<String, Double>();
		tabla.put("COD001", 10.00);
		tabla.put("COD002", 52.60);
		tabla.put("COD003", 3.50);
		tabla.put("COD004", 16.00);
		tabla.put("COD005", 13.00);
		
		//Diccionario de los precios de cada productos (precio sin iva, precio con iva)
		Hashtable<Double, Double> tablaPrecioP = new Hashtable<Double, Double>();
		
		//Utilizar el menu de la actividad 3 pero con una opcion más con la qual se podran añadir productos.
		do {
			String opcionS = JOptionPane.showInputDialog("Escoje una función:\n1.Añadir un producto nuevo \n2.Consultar un producto \n3.Consultar todos los productos\n4.Añadir Venta\n5.Salir");
			opcion = Integer.parseInt(opcionS);
			
			switch (opcion) {
				case 1:
					añadirProducto(tabla);
					break;
				case 2:
					String codigo = JOptionPane.showInputDialog("Introduce el codigo del producto que quieres mostrar");
					mostrarProducto(tabla, codigo);
					break;
				case 3:
					mostrarProductosAll(tabla);
					break;
				case 4:
					//Número de productos a procesar
					String numPS = JOptionPane.showInputDialog("Indica la cantidad de productos");
					int numP = Integer.parseInt(numPS);
					
					System.out.println("Productos Comprados:");
					System.out.println("---------------------------------------------------------------------------------");
					System.out.println("Codigo | Precio unitario | Precio con IVA");
					
					do {
						
						//Pedir datos del producto
						String codigoP = JOptionPane.showInputDialog("Introduce el codigo del producto");
						
						
						precioProd = obtenerPrecioProducto(tabla, codigoP);
						
						String ivaOp = JOptionPane.showInputDialog("Indica que IVA se le aplica al producto:\n1.21%\n2.4%");
						if(ivaOp.equals("1")) {
							iva=21;
						}else if(ivaOp.equals("2")) {
							iva=4;
						}else {
							iva=21;
							JOptionPane.showMessageDialog(null, "La opcion no es correcta, por defecto se aplicará el 21%");
						}
						
						precioProdIva = calcularPrecioProdIva(precioProd, iva);
						
						tablaPrecioP.put(precioProd, precioProdIva);
						
						mostrarFacturaProd(tabla, codigoP, precioProdIva);
						
						//Hacer que las ventas eliminen objetos del diccionario
						
						eliminarProducto(tabla, codigoP);
						
						cont++;			
						
					}while(cont != numP);	
					
					String cantidadPagadaS = JOptionPane.showInputDialog("Indica la cantidad pagada por el cliente");
					cantidadPagada = Double.parseDouble(cantidadPagadaS);
					
					precioT = calcularPrecioTotal(tablaPrecioP);
					precioTIva = calcularPrecioTotalIva(tablaPrecioP);
					double cambio = calcularCambio(cantidadPagada, precioTIva);
					
					mostrarFacturaFinal(precioT, precioTIva, cantidadPagada, cambio);
					
					break;
				case 5:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Escoje un número entre el 1 y el 4");
					break;
			}
		}while(opcion!=5);
		
		
	}
	
	//Ahora añado las funciones de las actividades anteriores
	//Act2
	//Obtiene el precio sin iva total de cada tipo de producto
	public static double obtenerPrecioProducto(Hashtable<String, Double> tabla, String codigo) {
		
		double precio = 0;
		
		for (String i : tabla.keySet()) {
			if(i.equals(codigo)) {
				precio = tabla.get(i);
			}
		}
		
		return precio;
	}
	
	//Calcula el precio con iva iva total de cada tipo de producto
	public static double calcularPrecioProdIva(double precioP, int iva) {
		
		double resultado = precioP+(precioP/iva);
		resultado = Math.round(resultado*100.0)/100.0;
		return resultado;
		
	}
	
	//Calcula el precio total sin iva de todos los productos
	public static double calcularPrecioTotal(Hashtable<Double, Double> tabla) {
		
		double suma = 0;
		
		for (Double i : tabla.keySet()) {
			suma = suma + i;
		}
		
		return suma;
	}
	
	//Calcula el precio total con iva de todos los productos
	public static double calcularPrecioTotalIva(Hashtable<Double, Double> tabla) {
		
		double suma = 0;
		
		for (Double i : tabla.keySet()) {
			suma = suma + tabla.get(i);
		}
		
		return suma;
	}
	
	//Muestra la parte ampliable de la factura
	public static void mostrarFacturaProd(Hashtable<String, Double> tabla, String codiP, double precioTIva) {

		for (String i : tabla.keySet()) {
			if(i.equals(codiP)) {
				System.out.println(i+" | "+tabla.get(i)+" | "+precioTIva);
			}
		}
		
	}
	
	//Mostrar la parte final de la factura
	public static void mostrarFacturaFinal(double precioT, double precioTIva, double dineroRecibido,double cambio) {
		
		System.out.println("Precio total bruto: "+precioT);
		System.out.println("Precio total con IVA: "+precioTIva);
		System.out.println("Dinero recibido: "+dineroRecibido);
		System.out.println("Cambio: "+cambio);
		
	}
	
	//Calcula el cambio que se le ha de dar al cliente
	public static double calcularCambio(double cantidadPagada, double precioT) {
		
		double resultado = cantidadPagada - precioT;
		return resultado;
		
	}	
	
	//Act3
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
	
	//Metodos de esta actividad
	//Metodo para eliminar un producto de la tabla
	public static void eliminarProducto(Hashtable<String, Double> tabla, String codigo) {
		
		int correcto = 0;
		String codigoC = "";
		
		for (String i : tabla.keySet()) {
			if (codigo.equals(i)) {
				codigoC = i;
				correcto = 1;
			}
		}
		
		
		if (correcto == 1) {
			tabla.remove(codigoC);
		}else {
			System.out.println("El codigo de producto no es correcto");
		}
		
	}
	
}
