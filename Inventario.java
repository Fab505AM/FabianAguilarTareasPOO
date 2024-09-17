package Tarea7U2POOISC;

import java.util.ArrayList;

public class Inventario {
    
    public ArrayList<Producto> listaProductos = new ArrayList<Producto>();


    public void agregarProducto(Producto producto) {
        this.listaProductos.add(producto);
    }
    public void mostrarProducto(Producto producto) {
        System.out.println("Lista Productos");
        System.out.println("ID: " + producto.getID());
        System.out.println("Nombre: " + producto.nombre);
        System.out.println("Precio: " + producto.precio);
        System.out.println("Stock: " + producto.stock);
    
}
}