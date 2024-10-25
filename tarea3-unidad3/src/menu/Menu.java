package menu;

import consultas.Consulta;
import consultorios.Consultorio;
import hospital.Hospital;
import usuarios.Usuario;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;
import usuarios.administradores.Administrador;
import usuarios.utils.Rol;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Hospital hospital = new Hospital();

    public void login(){

        int intentosMAXIMOS = 3, intentosUsuario = 0;

        System.out.println("BIENVENIDO");
        System.out.println("Inicia sesion para continuar");


        while(intentosUsuario < intentosMAXIMOS) {

            System.out.println("Ingresa el usuario: ");
            String usuario = scanner.nextLine();

            System.out.println("Ingresa la contrasenia: ");
            String contrasenia = scanner.nextLine();

            Usuario usuarioEnSesion = hospital.validarInisioSesion(usuario, contrasenia);

            if (usuarioEnSesion instanceof Usuario) {
                if (usuarioEnSesion.getRol() == Rol.PACIENTE) {
                    Paciente pacienteEnSesion = (Paciente) usuarioEnSesion;
                    //this.mostrarMenuPaciente(pacienteEnSesion);
                    MenuPaciente menuPaciente = new MenuPaciente();
                    int opcion = 0;
                    while (opcion != 4) {
                        opcion = menuPaciente.mostrarMenu();
                        menuPaciente.procesarDatosMenu(opcion, hospital, pacienteEnSesion);
                    }
                    intentosUsuario = 0;
                } else if (usuarioEnSesion.getRol() == Rol.MEDICO) {
                    Medico medicoEnSesion = (Medico) usuarioEnSesion;
                    MenuMedico menuMedico = new MenuMedico();
                    int opcion = 0;
                    while (opcion != 7) {
                        opcion = menuMedico.mostrarMenu();
                        menuMedico.procesarDatosMenu(opcion, hospital, medicoEnSesion);
                    }
                    intentosUsuario = 0;
                } else {
                    Administrador administradorEnSesion = (Administrador) usuarioEnSesion;
                    MenuAdministrador menuAdministrador = new MenuAdministrador();
                    int opcion = 0;
                    while (opcion != 15) {
                        opcion = menuAdministrador.mostrarMenu();
                        menuAdministrador.procesaeDatosMenu(opcion, hospital, administradorEnSesion);
                    }
                    intentosUsuario = 0;
                }
            } else {
                intentosUsuario = mostrarErrorInicioSesion(intentosUsuario);
            }
        }
    }

    private int mostrarErrorInicioSesion(int intentosUsuarios) {
        System.out.println("\nUsuario o Contraseña incorrectos, Intenta de nuevo");
        return intentosUsuarios + 1;
    }
}