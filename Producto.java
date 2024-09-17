package Tarea7U2POOISC;
import java.util.Random;
public class Producto {
    
public int cantidad;
    public int ID;
public String nombre;
public double precio;
public int stock;
public Random random =new Random();

public Producto ( String nombre, double precio, int stock){
    this.ID = this.random.nextInt(1, 10000000);
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
    }
public int getID() {return ID;}
public static void agregarProducto(Producto product) {
   
    System.out.println("Producto añadido: " + product.nombre + ", ID: " + product.ID);
}


}
