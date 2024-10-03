package Programa;

import java.util.Scanner;

public class ProyectoFinalConScanner {

    public static void main(String[] args) {
        // Entradas: Productos iniciales, precios y stock
        String[] productos = {"Medicinas", "Vitaminas", "Alimentos"}; //categorias de productos
        String[] nombresProductos = {"Antibiótico", "Vitamina C", "Croquetas"}; //productos
        int[] stock = {50, 30, 100}; //stock principal por producto
        double[] precios = {25.5, 15.0, 5.75}; // Precios de los productos
        int[] ventasDiarias = new int[productos.length]; // ventas diarias por producto
        int[] ventasMensuales = new int[productos.length]; // ventas mensuales por producto

        int diaActual = 0; // dia en el que se esta trabajando, se inicia en el dia 1

        //Implementacion de Scanner para solicitar datos
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        //Menu de opciones
        while (continuar) {
            System.out.println("------AGROVET SA.------");
            System.out.println("--- Menú de Opciones---");
            System.out.println("1. Ver inventario");
            System.out.println("2. Registrar venta");
            System.out.println("3. Agregar stock por producto");
            System.out.println("4. Terminar día de trabajo");
            if (diaActual == 30) {
                System.out.println("5. Terminar mes");
            }
            System.out.println("6. Renunciar");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de linea al presionar enter

            switch (opcion) {
                case 1:
                    mostrarInventario(productos, nombresProductos, stock, precios);
                    break;
                case 2:
                    registrarVenta(productos, nombresProductos, stock, precios, ventasDiarias, scanner);
                    break;}
        }
    }

    // Metodo que muestra el inventario actualizado
    private static void mostrarInventario(String[] productos, String[] nombresProductos, int[] stock, double[] precios) {
        System.out.println("\n--- Inventario Actual ---");
        for (int i = 0; i < productos.length; i++) {
            System.out.println(productos[i] + " - " + nombresProductos[i] + ": " + stock[i] + " unidades - Precio: S/." + precios[i]);
        }
    }

    // Metodo para registrar venta
    private static void registrarVenta(String[] productos, String[] nombresProductos, int[] stock, double[] precios, int[] ventasDiarias, Scanner scanner) {
        System.out.println("\n--- Registrar Venta ---");
        for (int i = 0; i < productos.length; i++) {
            System.out.println(i + 1 + ". " + productos[i] + " - " + nombresProductos[i] + " - Stock: " + stock[i] + " - Precio: S/." + precios[i]);
        }
        System.out.print("Seleccione el producto: ");
        int productoSeleccionado = scanner.nextInt() - 1;

        if (productoSeleccionado >= 0 && productoSeleccionado < productos.length) {
            System.out.print("Ingrese la cantidad vendida: ");
            int cantidadVendida = scanner.nextInt();

            if (cantidadVendida <= stock[productoSeleccionado]) {
                stock[productoSeleccionado] = actualizarStock(stock[productoSeleccionado], cantidadVendida);
                ventasDiarias[productoSeleccionado] += cantidadVendida; // Registrar venta del dia
                System.out.println("Venta registrada. Nuevo stock de " + nombresProductos[productoSeleccionado] + ": " + stock[productoSeleccionado]);
            } else {
                System.out.println("Stock insuficiente. Venta no realizada.");
            }
        } else {
            System.out.println("Producto no válido.");
        }
    }


    // Metodo para actualizar stock por producto
    private static int actualizarStock(int stockActual, int cantidadVendida) {
        int nuevoStock = stockActual - cantidadVendida;
        if (nuevoStock < 0) {
            nuevoStock = 0;
        }
        return nuevoStock;
}
}