import java.util.ArrayList;
import java.util.Scanner;

// Excepciones personalizadas
class HabitacionNoDisponibleException extends Exception {
    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

class NumeroDeNochesInvalidoException extends Exception {
    public NumeroDeNochesInvalidoException(String mensaje) {
        super(mensaje);
    }
}

// Clase Habitacion
class Habitacion {
    private String tipo;
    private double precioPorNoche;
    private boolean disponible;

    public Habitacion(String tipo, double precioPorNoche) {
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponible = true;
    }

    public void reservar(int noches) throws HabitacionNoDisponibleException, NumeroDeNochesInvalidoException {
        if (!disponible) {
            throw new HabitacionNoDisponibleException("La habitación no está disponible.");
        }
        if (noches < 1) {
            throw new NumeroDeNochesInvalidoException("El número de noches debe ser al menos 1.");
        }
        disponible = false;
        System.out.println("Reserva exitosa para " + noches + " noche(s).");
        System.out.println("Costo total de la reserva: $" + (noches * precioPorNoche));
    }

    public void liberar() {
        disponible = true;
        System.out.println("La habitación ha sido liberada y está disponible nuevamente.");
    }

    public void mostrarDetalles() {
        System.out.println("Tipo de habitación: " + tipo);
        System.out.println("Precio por noche: $" + precioPorNoche);
        System.out.println("Disponibilidad: " + (disponible ? "Disponible" : "Ocupada"));
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public boolean isDisponible() {
        return disponible;
    }
}

// Clase principal
public class SistemaReservas {
    private ArrayList<Habitacion> habitaciones;

    public SistemaReservas() {
        habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void mostrarHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            habitacion.mostrarDetalles();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SistemaReservas sistema = new SistemaReservas();
        Scanner scanner = new Scanner(System.in);

        sistema.agregarHabitacion(new Habitacion("Individual", 50.0));
        sistema.agregarHabitacion(new Habitacion("Doble", 75.0));
        sistema.agregarHabitacion(new Habitacion("Suite", 150.0));

        while (true) {
            System.out.println("\n--- Sistema de Reservas de Habitaciones ---");
            System.out.println("1. Mostrar detalles de las habitaciones");
            System.out.println("2. Realizar una reserva");
            System.out.println("3. Liberar una habitación");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpia el buffer
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer después de leer un número

            switch (opcion) {
                case 1:
                    sistema.mostrarHabitaciones();
                    break;

                case 2:
                    System.out.print("\nIngrese el número de habitación (1: Individual, 2: Doble, 3: Suite): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Error: Ingrese un número válido.");
                        scanner.nextLine();
                        continue;
                    }
                    int numHabitacion = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer

                    System.out.print("Ingrese el número de noches a reservar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Error: Ingrese un número válido.");
                        scanner.nextLine();
                        continue;
                    }
                    int noches = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer

                    try {
                        Habitacion habitacion = sistema.habitaciones.get(numHabitacion - 1);
                        habitacion.reservar(noches);
                    } catch (HabitacionNoDisponibleException | NumeroDeNochesInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Número de habitación inválido.");
                    }
                    break;

                case 3:
                    System.out.print("\nIngrese el número de habitación para liberar (1: Individual, 2: Doble, 3: Suite): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Error: Ingrese un número válido.");
                        scanner.nextLine();
                        continue;
                    }
                    int numLiberar = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer

                    try {
                        Habitacion habitacion = sistema.habitaciones.get(numLiberar - 1);
                        habitacion.liberar();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Número de habitación inválido.");
                    }
                    break;

                case 4:
                    System.out.println("Gracias por usar el Sistema de Reservas de Habitaciones. ¡Hasta pronto!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
