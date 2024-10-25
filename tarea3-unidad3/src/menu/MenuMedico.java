package menu;

import hospital.Hospital;
import usuarios.medicos.Medico;

import java.util.Scanner;

public class MenuMedico {
    private Scanner scanner = new  Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n****BIENVENIDO****");
        System.out.println("1. Ver consultas");
        System.out.println("2. Ver mis pacientes");
        System.out.println("3. Ver mis datos");
        System.out.println("4. Ver consultar paciente");
        System.out.println("5. Consultar expediente de paciente");
        System.out.println("6. Completar contulta");
        System.out.println("7. Salir");


        System.out.println("Selecciona una opcion: ");
        int opcion = scanner.nextInt();
        return opcion;
    }

    public void procesarDatosMenu(int opcion, Hospital hospital, Medico medico) {
        switch (opcion) {
            case 1:
                System.out.println("\n--Seleccionaste la opción de ver consultas--");
                hospital.mostrarConsultasPorMedico(medico.getId());
                break;
            case 2:
                System.out.println("\n--Seleccionaste la opción de ver mis pacientes--");
                System.out.println(hospital.obtenerNombresPacientesPorMedico(medico.getId()));
                break;
            case 3:
                System.out.println("\n--Seleccionaste la opción de ver mis datos--");
                hospital.mostrarMedicoPorId(medico.getId());
            case 7:
                System.out.println("Saliendo del sistema");
                break;
            default:
                System.out.println("Numero no esta dentro de las opciones");
        }
    }
}