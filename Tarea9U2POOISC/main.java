package Tarea9U2POOISC.Tarea9U2POOISC;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n*** BIENVENIDO ***");
            System.out.println("1. Solicitar cita");
            System.out.println("2. Cancelar cita");
            System.out.println("3. Consultar horarios ");
        

            System.out.println("Selecciona una opción: ");
            opcion = sc.nextInt();


}
    }
}