package menu;

import hospital.Hospital;
import usuarios.pacientes.Paciente;

import java.util.Scanner;

public class MenuPaciente {
    private Scanner scanner = new  Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n****BIENVENIDO****");
        System.out.println("1. Ver consultas");
        System.out.println("2. Ver mis datos");
        System.out.println("3. Ver mi expediente");
        System.out.println("4. Salir");

        System.out.println("Selecciona una opcion: ");
        int opcion = scanner.nextInt();
        return opcion;
    }

    public void procesarDatosMenu(int opcion, Hospital hospital, Paciente paciente) {
        switch (opcion) {
            case 1:
                System.out.println("\n--Seleccionaste la opción de ver consultas--");
                hospital.mostrarConsultasPorPaciente(paciente.getId());
                break;
            case 2:
                System.out.println("\n--Seleccionaste la opción de ver mis datos--");
                hospital.mostrarPacienteID(paciente.getId());
                break;
            case 3:
                break;
            case 4:
                System.out.println("Saliendo del sistema");
                scanner.nextLine();
                break;
            default:
                System.out.println("Numero no esta dentro de las opciones");
        }
    }
}