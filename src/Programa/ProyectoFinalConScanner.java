package Programa;

import java.util.Scanner;

//Proyecto Final Agrovet - Fundamentos de la Programacion 1

//Profesor: Lucas Agustín Fajardo Montes

//Sección: D15A

//Grupo: 1

//Integrantes:
//-Arellano Bastidas, Raquel Consuelo
//-Cachicatari Calderon, Edyson Romel
//-Mamani Villon, Miguel Alexander
//-Mollinedo Limachi, Antonio Salomon
//-Robles Mejía, Acxell Eduardo
//-Yarasca Luna, Cristhian Jesus

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
                    break;
                case 3:
                    agregarStock(nombresProductos, stock, scanner);
                    break;
                case 4:
                    if (diaActual < 30) {
                        diaActual++;
                        terminarDiaTrabajo(nombresProductos, ventasDiarias, precios, diaActual, ventasMensuales);
                    } else {
                        System.out.println("Ya se han registrado los 30 días de trabajo. Puede terminar el mes.");
                    }
                    break;
                case 5:
                    if (diaActual == 30) {
                        terminarMes(nombresProductos, ventasMensuales, precios, diaActual);
                        diaActual = 0; // Resetear dia trabajados
                        // Reiniciar las ventas mensuales
                        for (int i = 0; i < ventasMensuales.length; i++) {
                            ventasMensuales[i] = 0;
                        }
                    } else {
                        System.out.println("No puede terminar el mes hasta haber completado 30 días.");
                    }
                    break;
                case 6:
                    renunciar(nombresProductos, ventasMensuales, precios, diaActual);
                    diaActual = 0; // Resetear días trabajados
                    continuar = false; // Salir del sistema
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    // Metodo para aumentar stock por prodycto
    private static void agregarStock(String[] nombresProductos, int[] stock, Scanner scanner) {
        System.out.println("\n--- Agregar Stock ---");
        for (int i = 0; i < nombresProductos.length; i++) {
            System.out.println((i + 1) + ". " + nombresProductos[i] + " - Stock actual: " + stock[i]);
        }
        System.out.print("Seleccione el producto al que desea agregar stock: ");
        int productoSeleccionado = scanner.nextInt() - 1;

        if (productoSeleccionado >= 0 && productoSeleccionado < nombresProductos.length) {
            System.out.print("Ingrese la cantidad de stock a agregar: ");
            int cantidadAgregar = scanner.nextInt();
            if (cantidadAgregar > 0) {
                stock[productoSeleccionado] += cantidadAgregar;
                System.out.println("Stock actualizado. Nuevo stock de " + nombresProductos[productoSeleccionado] + ": " + stock[productoSeleccionado]);
            } else {
                System.out.println("Cantidad no válida. No se agregó stock.");
            }
        } else {
            System.out.println("Producto no válido.");
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

    // Metodo para terminar el dia de trabajo
    private static void terminarDiaTrabajo(String[] nombresProductos, int[] ventasDiarias, double[] precios, int diaActual, int[] ventasMensuales) {
        System.out.println("\n--- Terminar Día de Trabajo: Día " + diaActual + " ---");
        generarReporteDiario(nombresProductos, ventasDiarias, precios);

        // Acumular la venta del vida para la venta del mes
        for (int i = 0; i < ventasDiarias.length; i++) {
            ventasMensuales[i] += ventasDiarias[i];
        }

        // Reiniciar ventas a 0 para el siguiente dia
        for (int i = 0; i < ventasDiarias.length; i++) {
            ventasDiarias[i] = 0;
        }
    }

    // Metodo para generar el reporte del dia
    private static void generarReporteDiario(String[] nombresProductos, int[] ventasDiarias, double[] precios) {
        double totalGanancias = 0;
        for (int i = 0; i < nombresProductos.length; i++) {
            double ganancias = ventasDiarias[i] * precios[i];
            totalGanancias += ganancias;
            System.out.println(nombresProductos[i] + ": Vendido " + ventasDiarias[i] + " unidades - Ganancias: S/." + ganancias);
        }
        System.out.println("Ganancias totales del día: S/." + totalGanancias);
    }

    // Metodo para el termino del mes y reporte mensual
    private static void terminarMes(String[] nombresProductos, int[] ventasMensuales, double[] precios, int diasTrabajados) {
        System.out.println("\n--- Terminar Mes ---");
        double totalGanancias = 0;
        for (int i = 0; i < nombresProductos.length; i++) {
            double ganancias = ventasMensuales[i] * precios[i];
            totalGanancias += ganancias;
            System.out.println(nombresProductos[i] + ": Vendido " + ventasMensuales[i] + " unidades - Ganancias: S/." + ganancias);
        }
        System.out.println("Días trabajados: " + diasTrabajados);
        System.out.println("Ganancias totales del mes: S/." + totalGanancias);
    }

    // Metodo para renunciar xd
    private static void renunciar(String[] nombresProductos, int[] ventasMensuales, double[] precios, int diasTrabajados) {
        System.out.println("\n--- ¡Renunciar! ---");
        terminarMes(nombresProductos, ventasMensuales, precios, diasTrabajados);
        double liquidacion = calcularLiquidacion(ventasMensuales, precios);
        System.out.println("Tu liquidación: S/." + liquidacion);
        System.out.println("¡Renuncias, al fin!");
    }

    // Metodo para calcular la liqui
    private static double calcularLiquidacion(int[] ventasMensuales, double[] precios) {
        double totalGanancias = 0;
        for (int i = 0; i < ventasMensuales.length; i++) {
            totalGanancias += ventasMensuales[i] * precios[i];
        }
        return totalGanancias * 0.1; // Supongamos que la liquidación es el 10% de las ganancias
    }

    // Metodo para actualizar stock por producto
    private static int actualizarStock(int stockActual, int cantidadVendida) {
        int nuevoStock = stockActual - cantidadVendida;
        if (nuevoStock < 0) {
            nuevoStock = 0;
        }
        return nuevoStock;
    }

    // Metodo que muestra el inventario actualizado
    private static void mostrarInventario(String[] productos, String[] nombresProductos, int[] stock, double[] precios) {
        System.out.println("\n--- Inventario Actual ---");
        for (int i = 0; i < productos.length; i++) {
            System.out.println(productos[i] + " - " + nombresProductos[i] + ": " + stock[i] + " unidades - Precio: S/." + precios[i]);
        }
    }
}