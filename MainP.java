package Tarea7U2POOISC;
import java.util.Scanner;
public class MainP {
    int cantidad = 0;
    
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        
        
        int opc = 0;
        while (opc != 4) {
            System.out.println("TIENDA");
            System.out.println("Seleccione una opcion--------------------------------------");
            System.out.println("1. Ver productos");
            System.out.println("2. Comprar producto");
            System.out.println("3. Agregar producto");
            System.out.println("4. Salir");
            System.out.println("------------------------------------------------------------");
            System.out.print("Ingrese su opcion: ");
            opc = sc.nextInt();
        }
            switch (opc) {
                case 1:
                    System.out.println("Productos:");
                   
                    break;

                
                case 2:
                    System.out.println("Ingrese el codigo del producto:");
                    int ID = sc.nextInt();
                    System.out.println("Ingrese la cantidad:");
                    int cantidad = sc.nextInt();
                    break;
                    
                case 3:
                    System.out.println("Ingrese los datos del producto:");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.println("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.println("Stock: ");
                    int stock = sc.nextInt();

                    Producto product = new Producto(nombre, precio, stock);
                    Producto.agregarProducto(product);
                    System.out.println("Producto registrado exitosamente :D");
                    break;
                
                case 4:
                    System.out.println("Gracias por su compra!");
                    break;
                default:
                    System.out.println("Opcion invalida!");
            }
        }

        }
        
           
        
            

