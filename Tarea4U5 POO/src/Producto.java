import java.io.FileWriter;
import java.io.IOException;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad)
            throws ProductoInvalidoException, PrecioInvalidoException, CantidadInvalidaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoInvalidoException("El nombre del producto no puede estar vacío o ser nulo.");
        }
        if (precio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero.");
        }
        if (cantidad < 0) {
            throw new CantidadInvalidaException("La cantidad no puede ser negativa.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public double calcularValorTotal() {
        return precio * cantidad;
    }

    public void mostrarDetalles() {
        System.out.println("Producto: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Valor total: $" + calcularValorTotal());
    }

    public void actualizarCantidad(int nuevaCantidad) throws CantidadInvalidaException {
        if (nuevaCantidad < 0) {
            throw new CantidadInvalidaException("La cantidad no puede ser negativa.");
        }
        this.cantidad = nuevaCantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void guardarEnArchivo(String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo, true)) { // `true` para añadir al archivo
            writer.write(nombre + "," + precio + "," + cantidad + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
        }
    }
}
