import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class GestorInventario {
    private static final String RUTA_ARCHIVO = "productos.txt";

    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                double precio = Double.parseDouble(partes[1]);
                int cantidad = Integer.parseInt(partes[2]);
                productos.add(new Producto(nombre, precio, cantidad));
            }
        } catch (Exception e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
        return productos;
    }

    public static void guardarProductos(List<Producto> productos) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            for (Producto producto : productos) {
                writer.write(producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Producto> productos = cargarProductos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú de Inventario ---");
            System.out.println("1. Ver productos");
            System.out.println("2. Comprar producto");
            System.out.println("3. Agregar producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Ver productos
                    System.out.println("\n--- Lista de Productos ---");
                    for (Producto producto : productos) {
                        producto.mostrarDetalles();
                        System.out.println("-------------------------");
                    }
                    break;

                case 2:
                    // Comprar producto
                    System.out.print("\nIngrese el nombre del producto a comprar: ");
                    String nombreCompra = scanner.nextLine();
                    Producto productoCompra = buscarProducto(productos, nombreCompra);

                    if (productoCompra != null) {
                        System.out.print("Ingrese la cantidad a comprar: ");
                        int cantidadCompra = scanner.nextInt();

                        if (cantidadCompra > 0 && cantidadCompra <= productoCompra.getCantidad()) {
                            try {
                                productoCompra.actualizarCantidad(productoCompra.getCantidad() - cantidadCompra);
                                System.out.println("Compra realizada con éxito.");
                            } catch (CantidadInvalidaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Cantidad no válida o insuficiente en inventario.");
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    // Agregar producto
                    try {
                        System.out.print("\nIngrese el nombre del producto: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el precio del producto: ");
                        double precio = scanner.nextDouble();

                        System.out.print("Ingrese la cantidad en inventario: ");
                        int cantidad = scanner.nextInt();

                        Producto nuevoProducto = new Producto(nombre, precio, cantidad);
                        productos.add(nuevoProducto);
                        System.out.println("Producto agregado con éxito.");
                    } catch (ProductoInvalidoException | PrecioInvalidoException | CantidadInvalidaException e) {
                        System.out.println("Error al agregar producto: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Salir
                    guardarProductos(productos);
                    System.out.println("Inventario guardado. ¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static Producto buscarProducto(List<Producto> productos, String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}
