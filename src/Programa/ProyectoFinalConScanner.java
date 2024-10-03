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
}