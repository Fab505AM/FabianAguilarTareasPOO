import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaDeTareas {

    private static final String NOMBRE_ARCHIVO = "tareas.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Escribir tarea");
            System.out.println("2. Leer tareas");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Terminar");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer después de leer el número

            switch (opcion) {
                case 1:
                    escribirTarea(scanner);
                    break;
                case 2:
                    leerTareas();
                    break;
                case 3:
                    eliminarTarea(scanner);
                    break;
                case 4:
                    System.out.println("Programa terminado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void escribirTarea(Scanner scanner) {
        System.out.print("Ingrese la tarea: ");
        String tarea = scanner.nextLine();

        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO, true)) { // "true" para agregar al archivo
            writer.write(tarea + System.lineSeparator());
            System.out.println("Tarea guardada.");
        } catch (IOException e) {
            System.out.println("Error al guardar la tarea: " + e.getMessage());
        }
    }

    private static void leerTareas() {
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No hay tareas guardadas.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            System.out.println("Tareas guardadas:");
            String linea;
            int numero = 1;
            while ((linea = reader.readLine()) != null) {
                System.out.println(numero + ". " + linea);
                numero++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer las tareas: " + e.getMessage());
        }
    }

    private static void eliminarTarea(Scanner scanner) {
        List<String> tareas = cargarTareas();

        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        System.out.println("Tareas guardadas:");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }

        System.out.print("Ingrese el número de la tarea que desea eliminar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Número inválido.");
            scanner.nextLine();
            return;
        }

        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer

        if (numero < 1 || numero > tareas.size()) {
            System.out.println("Número fuera de rango.");
            return;
        }

        tareas.remove(numero - 1);
        guardarTareas(tareas);
        System.out.println("Tarea eliminada.");
    }

    private static List<String> cargarTareas() {
        List<String> tareas = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            return tareas; // Devuelve una lista vacía si el archivo no existe
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                tareas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las tareas: " + e.getMessage());
        }

        return tareas;
    }

    private static void guardarTareas(List<String> tareas) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO)) { // Sobrescribe el archivo
            for (String tarea : tareas) {
                writer.write(tarea + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }
}
