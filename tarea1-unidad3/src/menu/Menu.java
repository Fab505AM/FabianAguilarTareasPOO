package menu;

import consultas.Consulta;
import consultorios.Consultorio;
import hospital.Hospital;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;
import usuarios.administradores.Administrador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Hospital hospital = new Hospital();
    private final String USUARIO_PACIENTE = "juan123";
    private final String CONTRASENIA_PACIENTE = "12345*";
    private final String USUARIO_MEDICO = "ale123";
    private final String CONTRASENIA_MEDICO = "54321*";
    private final String USUARIO_ADMIN = "admin";
    private final String CONTRASENIA_ADMIN = "admin1";

    public void login(){

        int intentosMAXIMOS = 3, intentosUsuario = 0;
        System.out.println("BIENVENIDO");
        System.out.println("Inicia sesion para continuar");

        while(intentosUsuario < intentosMAXIMOS) {

            System.out.println("Ingresa el  usuario: ");
            String usuario = scanner.nextLine();

            System.out.println("Ingresa la contrasenia: ");
            String contrasenia = scanner.nextLine();

            if(usuario.equals(this.USUARIO_PACIENTE)) {
                if(contrasenia.equals(this.CONTRASENIA_PACIENTE)) {
                    this.mostrarMenuPaciente();
                    intentosUsuario = 0;
                }
            } else if(usuario.equals(this.USUARIO_MEDICO)){
                if(contrasenia.equals(this.CONTRASENIA_MEDICO)) {
                    this.mostrarMenuMedico();
                    intentosUsuario = 0;
                }
            } else if(usuario.equals(this.USUARIO_ADMIN)){
                if(contrasenia.equals(this.CONTRASENIA_ADMIN)) {
                    this.mostrarMenu();
                    intentosUsuario = 0;
                }
            } else {

                mostrarErrorInicioSesion(intentosUsuario);
                intentosUsuario++;
                System.out.println(intentosUsuario);
                if (intentosUsuario == intentosMAXIMOS) {
                    System.out.println("\nHas excedido el numero de intentos permitidos\n");
                    break;
                }
            }
        }
    }

    private int mostrarErrorInicioSesion(int intentosUsuarios) {
        System.out.println("\nUsuario o Contraseña incorrectos, Intenta de nuevo");
        return intentosUsuarios + 1;
    }

    private void mostrarMenuPaciente(){
        System.out.print("Ingresa tu Id: ");
        String id = scanner.nextLine();

        int opcion = 0;
        while(opcion !=2) {
            System.out.println("\n****BIENVENIDO****");
            System.out.println("1. Ver consultas");
            System.out.println("2. Salir");

            System.out.println("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    //ver consultas
                    System.out.println("\n--Seleccionaste la opción de ver consultas--");
                    hospital.mostrarConsultasPorPaciente(id);
                    break;
                case 2:
                    System.out.println("Saliendo del sistema");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Numero no esta dentro de las opciones");
            }
        }
        scanner.nextLine();
    }

    private void mostrarMenuMedico(){
        System.out.print("Ingresa tu Id: ");
        String id = scanner.nextLine();
        int opcion2 = 0;
        while(opcion2 !=3) {
            System.out.println("\n****BIENVENIDO****");
            System.out.println("1. Ver consultas");
            System.out.println("2. Ver mis usuarios.pacientes");
            System.out.println("3. Consultar Paciente");
            System.out.println("4. Salir");

            System.out.println("Selecciona una opcion: ");
            opcion2 = scanner.nextInt();
            switch (opcion2) {
                case 1:
                    //ver consultas
                    System.out.println("\n--Seleccionaste la opción de ver consultas--");
                    hospital.mostrarConsultasPorMedico(id);
                    break;
                case 2:
                    System.out.println(hospital.obtenerNombresPacientesPorMedico(id));
                    break;
                case 3:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Numero no esta dentro de las opciones");
            }
        }
        scanner.nextLine();
    }

    private void mostrarMenu() {
        while (true) {
            System.out.println("\n*** SISTEMA HOSPITAL ***");
            System.out.println("\n** Menu **");
            System.out.println("1.- Registrar Pacientes");
            System.out.println("2.- Registrar Medicos");
            System.out.println("3.- Registrar Consultorio");
            System.out.println("4.- Registrar Consulta");
            System.out.println("5.- Mostrar Pacientes");
            System.out.println("6.- Mostrar Medicos");
            System.out.println("7.- Mostrar Consultorios");
            System.out.println("8.- Mostrar Consultas");
            System.out.println("9.- Buscar Paciente por Id");
            System.out.println("10. Buscar Medico por Id");
            System.out.println("11. Buscar Consultorio por Id");
            System.out.println("12. Registrar administrador");
            System.out.println("13. Mostrar administradores");
            System.out.println("14. Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--Seleccionaste la opción de registrar usuarios.pacientes--");

                    String id = hospital.generarIdPaciente();

                    System.out.print("\nIngresa el nombre del paciente: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingresa el apellido: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Ingresa el año de nacimiento del paciente: ");
                    int anio = scanner.nextInt();

                    System.out.print("Ingresa el mes de nacimiento del paciente: ");
                    int mes = scanner.nextInt();

                    System.out.print("Ingresa el día de nacimiento del paciente: ");
                    int dia = scanner.nextInt();

                    LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);

                    System.out.print("Ingresa el tipo de sangre: ");
                    String tipoSangre = scanner.nextLine();

                    scanner.nextLine();

                    System.out.print("Ingresa el sexo: ");
                    String sexo2 = scanner.nextLine();
                    char sexo = sexo2.charAt(0);

                    String telefono = null;

                    while (telefono == null){
                        System.out.print("Ingresa el numero de telefono: ");
                        telefono = scanner.nextLine();

                        if (!hospital.validarTelefonoPaciente(telefono)) {
                            System.out.println("\nYa exixte un paciente con el mismo numero de telefono, intente de nuevo\n");
                            telefono = null;
                        }
                    }

                    System.out.println("Ingrese la contraseña del paciente");
                    String contraseniaPaciente = scanner.nextLine();

                    Paciente paciente = new Paciente(id, nombre, apellido, fechaNacimiento, tipoSangre, sexo, telefono, contraseniaPaciente);
                    hospital.registrarPacientes(paciente);

                    System.out.println("Paciente registrado exitosamente");
                    break;
                case 2:
                    System.out.println("\n--Seleccionaste la opción de registrar usuarios.medicos--");

                    System.out.print("\nIngresa el nombre del medico: ");
                    String nombreMedico = scanner.nextLine();

                    System.out.print("Ingresa el apellido: ");
                    String apellidoMedico = scanner.nextLine();

                    System.out.print("Ingresa el año de nacimiento del médico: ");
                    int anioMedico = scanner.nextInt();

                    System.out.print("Ingresa el mes de nacimiento del médico: ");
                    int mesMedico = scanner.nextInt();

                    System.out.print("Ingresa el día de nacimiento del médico: ");
                    int diaMedico = scanner.nextInt();

                    LocalDate fechaNaciMedico = LocalDate.of(anioMedico, mesMedico, diaMedico);

                    String telMedico = null;

                    while (telMedico == null) {
                        System.out.print("Ingresa el telefono del medico: ");
                        telMedico = scanner.nextLine();

                        if (!hospital.validarTelefonoMedico(telMedico)) {
                            System.out.println("\nYa existe un medico con el mismo numero de telefono, Intente de nuevo\n");
                            telMedico = null;
                        }
                    }

                    String rfcMedico = null;

                    while (rfcMedico == null) {
                        System.out.print("Ingresa el rfc del medico: ");
                        rfcMedico = scanner.nextLine(); 

                        if (!hospital.validarRFCMedico(rfcMedico)) {
                            System.out.println("\nYa exixte un medico con el mismo rfc, Intente de nuevo");
                            rfcMedico = null;
                        }
                    }

                    System.out.println("Ingrese la contraseña del medico");
                    String contraseniaMedico = scanner.nextLine();

                    String idMedico= hospital.generarIdMedico(apellidoMedico, String.valueOf(fechaNaciMedico.getYear()));

                    Medico medico = new Medico(idMedico,nombreMedico,apellidoMedico,fechaNaciMedico,telMedico,rfcMedico, contraseniaMedico);
                    hospital.registrarMedico(medico);

                    System.out.println("Medico registrado exitosamente");
                    break;
                case 3:
                    System.out.println("\n--Seleccionaste la opción de registrar consultorio--");

                    String idConsultorio=hospital.generarIdConsultorio();

                    System.out.print("\nIngrese el piso del consultorio: ");
                    int pisoConsultorio =scanner.nextInt();

                    System.out.print("Ingrese el numero de consultorio: ");
                    int numeroConsultorio = scanner.nextInt();

                    Consultorio consultorio = new Consultorio(idConsultorio,pisoConsultorio,numeroConsultorio);
                    hospital.registrarConsultorio(consultorio);

                    System.out.println("Consultorio registrado exitosamente");
                    break;
                case 4:
                    System.out.println("\n--Seleccionaste la opción de registrar consulta--");

                    String idConsulta = hospital.generarIdConsulta();

                    LocalDateTime fechaConsulta = null;

                    while (fechaConsulta == null) {
                        System.out.print("\nIngresa el día de la consnulta deseada: ");
                        int diaConsulta = scanner.nextInt();

                        System.out.print("Ingresar el mes de la consullta deseada: ");
                        int mesConsulta = scanner.nextInt();

                        System.out.print("Ingresa el año de la consulta deseada: ");
                        int anioConsulta = scanner.nextInt();

                        System.out.print("Ingresa la hora de la consulta: ");
                        int horaConsulta = scanner.nextInt();

                        System.out.print("Ingresa los minutos de la consulta: ");
                        int minutosConsulta = scanner.nextInt();

                        fechaConsulta = LocalDateTime.of(anioConsulta, mesConsulta, diaConsulta, horaConsulta, minutosConsulta);

                        if (!hospital.validarFechaConsulta(fechaConsulta)) {
                            System.out.println("La fecha no puede estar en el pasado");
                            fechaConsulta = null;
                        }
                    }

                    Paciente pacienteConsulta = null;

                    scanner.nextLine();

                    while (pacienteConsulta == null) {
                        System.out.print("Ingresa el id del paciente: ");
                        String pacienteId = scanner.nextLine();

                        pacienteConsulta = hospital.obtenerPacientePorId(pacienteId);

                        if (pacienteConsulta == null) {
                            System.out.println("\nPaciente no encontrado, Intenta de nuevo\n");
                        }
                    }

                    Medico medicoConsulta = null;

                    while (medicoConsulta == null) {
                        System.out.print("Ingresa el id del medico: ");
                        String medicoId = scanner.nextLine();

                        medicoConsulta = hospital.obtenerMedicoPorId(medicoId);

                        if (medicoConsulta == null) {
                            System.out.println("\nMedico no encontrado, Intenta de nuevo\n");
                        }
                    }

                    Consultorio consultorioConsulta = null;

                    while (consultorioConsulta == null) {
                        System.out.print("Ingresa el id del consultorio: ");
                        String consultorioId = scanner.nextLine();

                        consultorioConsulta = hospital.obtenerConsultorioPorId(consultorioId);

                        if (consultorioConsulta == null) {
                            System.out.println("\nConsultorio no encontrado, Intenta de nuevon");
                        }
                    }

                    Consulta consulta = new Consulta(idConsulta, fechaConsulta, pacienteConsulta, medicoConsulta, consultorioConsulta);
                    hospital.registrarConsulta(consulta);

                    System.out.println("Consulta registrada exitosamente");
                    break;
                case 5:
                    System.out.println("\n--Seleccionaste la opción de mostrar usuarios.pacientes--");
                    hospital.mostrarPacientes();
                    break;
                case 6:
                    System.out.println("\n--Seleccionaste la opción de mostrar usuarios.medicos--");
                    hospital.mostrarMedicos();
                    break;
                case 7:
                    System.out.println("\n--Seleccionaste la opción de mostrar consultorios--");
                    hospital.mostrarConsultorio();
                    break;
                case 8:
                    System.out.println("\n--Seleccionaste la opción de mostrar consultas--");
                    hospital.mostrarConsultas();
                    return;
                case 9:
                    System.out.println("\n--Seleccionaste la opción de buscar Paciente mediante la ID--");

                    System.out.println("\nIngresa el Id del paciente que deseas buscar");
                    String idPaciente = scanner.nextLine();

                    hospital.mostrarPacienteID(idPaciente);
                    break;
                case 10:
                    System.out.println("\n--Seleccionaste la opción de buscar usuarios.medicos mediante la ID--");

                    System.out.println("Ingresa el Id del medico que deseas buscar: ");
                    String idMedico2 = scanner.nextLine();

                    hospital.mostrarMedicoPorId(idMedico2);
                    break;
                case 11:
                    System.out.println("\n--Seleccionaste la opción de buscar consultorio mediante la ID--");

                    System.out.println("Ingresa el Id del consultorio que deseas buscar: ");
                    String idConsultorio2= scanner.nextLine();

                    hospital.mostrarConsultorioPorId(idConsultorio2);
                    break;
                case 12:
                    System.out.println("\nAccediste a la opcion de registrar un administrador");
                    scanner.nextLine();

                    System.out.println("Ingresa el nombre: ");
                    String nombreAdmin = scanner.nextLine();


                    System.out.println("Ingresa los apellidos: ");
                    String apellidosAdmin = scanner.nextLine();

                    System.out.println("Ingresa la contrasenia: ");
                    String contraseniaAdmin = scanner.nextLine();

                    System.out.println("Ingresa el año de nacimiento: ");
                    int anioNacimientoAdmin = scanner.nextInt();

                    System.out.println("Ingresa el mes de nacimiento: ");
                    int mesNacimientoAdmin = scanner.nextInt();

                    System.out.println("Ingresa el dia de nacimiento: ");
                    int diaNacimientoAdmin = scanner.nextInt();
                    scanner.nextLine();

                    LocalDate fechaNacimientoAdmin = LocalDate.of(anioNacimientoAdmin,mesNacimientoAdmin,diaNacimientoAdmin);

                    String telefonoAdmin = null;
                    while(telefonoAdmin == null) {
                        System.out.println("Ingresa el telefono: ");
                        telefonoAdmin = scanner.nextLine();
                        Administrador telefonoAdmin1 = hospital.obtenerTelefonoAdmin(telefonoAdmin);
                        if(telefonoAdmin1 != null) {
                            System.out.println("\nYa existe ese numero de telefono, intenta con otro\n");
                            telefonoAdmin = null;
                        }
                    }

                    String rfcAdmin = null;
                    while(rfcAdmin == null) {
                        System.out.println("Ingresa el RFC: ");
                        rfcAdmin = scanner.nextLine();
                        Administrador rfcAdmin1 = hospital.obtenerRfcAdmin(rfcAdmin);
                        if(rfcAdmin1 != null) {
                            System.out.println("\nYa existe ese RFC, intenta con otro\n");
                            rfcAdmin = null;
                        }
                    }

                    System.out.println("Ingresa tu sueldo quincenal: ");
                    double sueldo = scanner.nextDouble();

                    System.out.println("Ingresa los anios que llevas laborando: ");
                    int antiguedad = scanner.nextInt();

                    String idAdmin = hospital.generarIdAdmin(apellidosAdmin,String.valueOf(fechaNacimientoAdmin));

                    Administrador administrador = new Administrador(idAdmin,nombreAdmin,apellidosAdmin,fechaNacimientoAdmin,telefonoAdmin, contraseniaAdmin,sueldo,rfcAdmin,antiguedad);
                    hospital.registrarAdministrador(administrador);

                    break;
                case 13:
                    hospital.mostrarAdministradores();
                    break;
                case 14:
                    System.out.println("\n--Seleccionaste la opcion de salir--");
                    System.out.println("*HASTA LUEGO*");
                    return;
            }
        }
    }
}
