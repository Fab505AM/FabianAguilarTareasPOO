import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Boolean seguir = true;

        while (seguir) {
            System.out.println("\nMenú:");
            System.out.println("1. Escribir tarea");
            System.out.println("2. Leer tareas");
            System.out.println("3. Terminar");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la tarea: ");
                    String tarea = scanner.nextLine();

                    try (FileWriter a3 = new FileWriter("tarea.txt", true);
                         BufferedWriter otro = new BufferedWriter(a3)) {
                        otro.write(tarea);
                        otro.newLine();
                        System.out.println("Tarea guardada.");
                    } catch (Exception e) {
                        System.out.println("Error al escribir la tarea: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Tareas guardadas:");
                    try (BufferedReader leer = new BufferedReader(new FileReader("tarea.txt"))) {
                        String lectura;
                        while ((lectura = leer.readLine()) != null) {
                            System.out.println(lectura);
                        }
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Programa terminado.");
                    seguir = false;
                    break;

                default:
                    System.out.println("Opción invalida. Por favor, intente nuevamente.");
            }
        }
        scanner.close();
    }
}
