/**
 * 
 * Miquel Angel Montero ActB 14/01/2022 
 * 
 */
import java.util.Hashtable;
import javax.swing.JOptionPane;
public class ActBApp {

	public static void main(String[] args) {
		
		//Variables aux
		int cont=0;
		double precioT = 0, cantidadPagada = 0, precioTProd = 0, precioTProdIva = 0, precioTIva = 0, iva = 0;
		
		//Definír diccionario de productos
		Hashtable<String, Double> tabla = new Hashtable<String, Double>();
		tabla.put("COD001", 10.00);
		tabla.put("COD002", 52.60);
		tabla.put("COD003", 3.50);
		tabla.put("COD004", 16.00);
		tabla.put("COD005", 13.00);
		
		//Diccionario de los precios de cada productos (precio sin iva, precio con iva)
		Hashtable<Double, Double> tablaPrecioP = new Hashtable<Double, Double>();
		
		//Número de productos a procesar
		String numPS = JOptionPane.showInputDialog("Indica la cantidad de productos");
		int numP = Integer.parseInt(numPS);
		
		System.out.println("Productos Comprados:");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Codigo | Precio unitario | Cantidad | Precio total sin IVA | Precio total con IVA");
		
		do {
			
			//Pedir datos del producto
			String codigoP = JOptionPane.showInputDialog("Introduce el codigo del producto");
			
			
			String cantidadPS = JOptionPane.showInputDialog("Introduce la cantidad del producto");
			double cantidadP = Double.parseDouble(cantidadPS);
			
			precioTProd = calcularPrecioProducto(tabla, codigoP, cantidadP);
			
			String ivaOp = JOptionPane.showInputDialog("Indica que IVA se le aplica al producto:\n1.21%\n2.4%");
			if(ivaOp.equals("1")) {
				iva=0.21;
			}else if(ivaOp.equals("2")) {
				iva=0.4;
			}else {
				iva=21;
				JOptionPane.showMessageDialog(null, "La opcion no es correcta, por defecto se aplicará el 21%");
			}
			
			precioTProdIva = calcularPrecioProdIva(precioTProd, iva);
			
			tablaPrecioP.put(precioTProd, precioTProdIva);
			
			mostrarFacturaProd(tabla, codigoP, cantidadP, precioTProd, precioTProdIva);
			
			cont++;
			
		}while(cont != numP);	
		
		String cantidadPagadaS = JOptionPane.showInputDialog("Indica la cantidad pagada por el cliente");
		cantidadPagada = Double.parseDouble(cantidadPagadaS);
		
		precioT = calcularPrecioTotal(tablaPrecioP);
		precioTIva = calcularPrecioTotalIva(tablaPrecioP);
		double cambio = calcularCambio(cantidadPagada, precioTIva);
		
		mostrarFacturaFinal(precioT, precioTIva, cantidadPagada, cambio);
		
	}
	
	
	//Calcula el precio sin iva total de cada tipo de producto
	public static double calcularPrecioProducto(Hashtable<String, Double> tabla, String codigo, double cantidadP) {
		
		double precio = 0;
		
		for (String i : tabla.keySet()) {
			if(i.equals(codigo)) {
				precio = tabla.get(i);
			}
		}
		
		precio = precio * cantidadP;
		
		return precio;
	}
	
	//Calcula el precio con iva iva total de cada tipo de producto
	public static double calcularPrecioProdIva(double precioP, double iva) {
		
		double resultado = precioP+(precioP*iva);
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
	public static void mostrarFacturaProd(Hashtable<String, Double> tabla, String codiP, double cant, double precioT, double precioTIva) {

		for (String i : tabla.keySet()) {
			if(i.equals(codiP)) {
				System.out.println(i+" | "+tabla.get(i)+" | "+cant+" | "+precioT+" | "+precioTIva);
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
}
