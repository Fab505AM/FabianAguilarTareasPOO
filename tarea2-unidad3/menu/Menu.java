package menu;

import consultas.Consulta;
import consultorios.Consultorio;
import hospital.Hospital;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import usuarios.Usuario;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;

public class Menu {

    private final Scanner leer = new Scanner(System.in);
    private final Hospital hospital = new Hospital();

    public void login() {
        int intentosMaximos = 5, intentosUsuario = 0;

        System.out.println("\nBienvenido.\n");

        while (intentosUsuario < intentosMaximos) {

            System.out.print("Ingresa tu ID: ");
            String usuario = leer.next();
            System.out.print("Ingresa tu contraseña: ");
            String contraseña = leer.next();

            Usuario usuarioEnSesion = hospital.validadInicioSesion(usuario, contraseña);

            if (usuarioEnSesion instanceof Usuario) {

                switch (usuarioEnSesion.getRol()) {
                    case PACIENTE -> {
                        Paciente pacienteEnSesion = (Paciente) usuarioEnSesion;
                        this.mostrarMenuPaciente(pacienteEnSesion);
                        intentosUsuario = 0;
                    }
                    case MEDICO -> {
                        Medico medicoEnSesion = (Medico) usuarioEnSesion;
                        this.mostrarMenuMedico(medicoEnSesion);
                        intentosUsuario = 0;
                    }
                    default -> {
                        this.mostrarMenuAdmin(usuarioEnSesion);
                        intentosUsuario = 0;
                    }
                }
            } else {
                intentosUsuario = mostrarErrorLogin(intentosUsuario);
            }
        }
        System.out.println("\nDemasiados intentos fallidos :(\n");
    }

    private int mostrarErrorLogin(int intentosUsuario) {
        System.out.println("\nUsuario o contraseña incorrectos. Intenta de nuevo.");
        return intentosUsuario + 1;
    }

    private void mostrarMenuPaciente(Paciente paciente) {
        int opc = 0;

        while (opc != 3) {
            System.out.println("\n----SISTEMA DEL HOSPITAL----\n");
            System.out.println("1. Ver mis consultas.");
            System.out.println("2. Ver mi infomación personal.");
            System.out.println("3. Salir");
            opc = leer.nextInt();

            switch (opc) {
                case 1 -> {
                    System.out.println("\n--------MOSTRAR CONSULTAS--------\n");
                    hospital.verConsultasPaciente(paciente.getId());
                }
                case 2 -> {
                    System.out.println("\n---------SU INFORMACIÓN PERSONAL--------\n");
                    hospital.mostrarPacientePorID(paciente.getId());
                }
                case 3 -> System.out.println("\nSaliendo...\n");
                default -> System.out.println("Opción invalida. Intenta de nuevo.");
            }
        }
    }

    private void mostrarMenuMedico(Medico medico){
        int opc=0;
        while (opc!=7) {

            System.out.println("\n----SISTEMA DEL HOSPITAL----\n");
            System.out.println("1. Ver mi información personal.");
            System.out.println("2. Ver mis consultas actuales.");
            System.out.println("3. Ver mis pacientes");
            System.out.println("4. Consultar paciente.");
            System.out.println("5. Consular historial o expediente de paciente.");
            System.out.println("6. Actualizar consulta.");
            System.out.println("7. Salir");
            opc = leer.nextInt();

                switch (opc) {

                    case 1:
                        System.out.println("\n---------SU INFORMACIÓN PERSONAL--------\n");
                        hospital.mostrarMedicoPorID(medico.getId());
                        break;
                    case 2:
                        System.out.println("\n--------MOSTRAR CONSULTAS--------\n");
                        hospital.verConsultasMedico(medico.getId());
                        break;

                    case 3:
                        System.out.println("\n--------MOSTRAR PACIENTES--------\n");
                        break;

                    case 4:
                        System.out.println("\n---------MOSTRAR PACIENTE------------\n");
                        System.out.print("Ingresa el ID del paciente al que se desea buscar: ");
                        String idBuscarPaciente = leer.next();
                        hospital.mostrarPacientePorID(idBuscarPaciente);
                        break;

                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:
                        System.out.println("\nSaliendo...\n");
                        break;

                    default:
                         System.out.println("\nOpción inválida. Intente de nuevo.\n");
                }
            }
    }
    private void mostrarMenuAdmin(Usuario usuario){

        String nombre, apellidos, telefono, tipoSangre, rfc, id, idBuscar, sexo;
        int opc=0, piso, numeroCons, año, mes, dia;

        while(opc!=13){

            System.out.println("\n---------MENÚ-----------");
            System.out.println("1. Registrar paciente.");
            System.out.println("2. Registrar médico.");
            System.out.println("3. Registrar consultorio.");
            System.out.println("4. Registrar consulta.");
            System.out.println("5. Mostrar paciente.");
            System.out.println("6. Mostrar médicos.");
            System.out.println("7. Mostrar consultorios.");
            System.out.println("8. Mostrar consultas.");
            System.out.println("9. Mostrar paciente por ID.");
            System.out.println("10. Mostrar médico por ID.");
            System.out.println("11. Mostrar consultorio por ID.");
            System.out.println("13. Salir.");
            opc=leer.nextInt();

            switch(opc) {

                case 1:
                    id = hospital.generarIDpaciente();
                    System.out.println("\n--------REGISTRAR PACIENTE--------");
                    System.out.print("Ingresar nombre: ");
                    nombre = leer.next();
                    System.out.print("Ingresar apellidos: ");
                    apellidos = leer.next();
                    System.out.print("Ingresar año de nacimiento: ");
                    año= leer.nextInt();
                    System.out.print("Ingresar mes de nacimiento: ");
                    mes= leer.nextInt();
                    System.out.print("Ingresar día de nacimiento: ");
                    dia= leer.nextInt();
                    System.out.print("Ingresar la contraseña del paciente: ");
                    String contraseña= leer.next();

                    LocalDate fechaNacimientoPaciente= LocalDate.of(año,mes,dia);
                    System.out.print("Ingresar tipo de sangre: ");
                    tipoSangre = leer.next();
                    System.out.print("Ingresar sexo: ");
                    sexo=leer.next();

                    System.out.print("Ingresar teléfono: ");
                    telefono = leer.next();

                    while(hospital.validarNumeroTelefono(telefono)){

                        if(hospital.validarNumeroTelefono(telefono)){
                            System.out.println("Este número de teléfono ya existe. Por favor ingresa uno diferente.");
                            System.out.print("Ingresar teléfono: ");
                            telefono = leer.next();

                            hospital.validarNumeroTelefono(telefono);
                        }
                    }

                    Paciente paciente = new Paciente(id, nombre, apellidos, fechaNacimientoPaciente, telefono, tipoSangre, sexo, contraseña);
                    hospital.registrarPaciente(paciente);
                    paciente.setId(id);
                    paciente.setApellidos(apellidos);
                    paciente.setNombre(nombre);
                    paciente.setFechaNacimiento(fechaNacimientoPaciente);
                    paciente.setTipoSangre(tipoSangre);
                    paciente.setTelefono(telefono);
                    paciente.setSexo(sexo);
                    System.out.println("\n Se registró un nuevo paciente");
                    break;

                case 2:
                    System.out.println("\n--------REGISTRAR MÉDICO--------");
                    System.out.print("Ingresar nombre: ");
                    nombre=leer.next();
                    System.out.print("Ingresar apellidos: ");
                    apellidos=leer.next();
                    System.out.print("Ingresar año de nacimiento: ");
                    año= leer.nextInt();
                    System.out.print("Ingresar mes de nacimiento: ");
                    mes= leer.nextInt();
                    System.out.print("Ingresar día de nacimiento: ");
                    dia= leer.nextInt();
                    System.out.print("Ingresar la contraseña del médico: ");
                    contraseña= leer.next();

                    LocalDate fechaNacimientoMedico = LocalDate.of(año,mes,dia);
                    id= hospital.generarIDMedico(apellidos, año);

                    System.out.print("Ingresar teléfono: ");
                    telefono = leer.next();

                    while(hospital.validarNumeroTelefono(telefono)){

                        if(hospital.validarNumeroTelefono(telefono)){
                            System.out.println("Este número de teléfono ya existe. Por favor ingresa uno diferente.");
                            System.out.print("Ingresar teléfono: ");
                            telefono = leer.next();

                            hospital.validarNumeroTelefono(telefono);
                        }
                    }

                    System.out.print("Ingresar RFC: ");
                    rfc=leer.next();

                    while(hospital.validarRFCMedico(rfc)){

                        if(hospital.validarRFCMedico(rfc)){
                            System.out.println("Este RFC ya existe. Por favor ingresa uno diferente.");
                            System.out.print("Ingresar RFC: ");
                            rfc = leer.next();

                            hospital.validarRFCMedico(rfc);
                        }
                    }

                    Medico medico=new Medico(id, nombre, apellidos, fechaNacimientoMedico, telefono, rfc, contraseña);
                    medico.setNombre(nombre);
                    medico.setApellidos(apellidos);
                    medico.setFechaNacimiento(fechaNacimientoMedico);
                    medico.setTelefono(telefono);
                    medico.setRfc(rfc);
                    medico.setId(id);
                    hospital.registrarMedico(medico);
                    System.out.println("\nSe registró un nuevo médico");
                    break;

                case 3:
                    System.out.println("\n--------REGISTRAR CONSULTORIO--------");
                    id= hospital.generarIDConsultorio();
                    System.out.print("Ingresar piso donde se encuentra el consultorio: ");
                    piso = leer.nextInt();
                    System.out.print("Ingresar numero de consultorio: ");
                    numeroCons = leer.nextInt();

                    Consultorio consultorio = new Consultorio(id, piso, numeroCons);
                    consultorio.setPiso(piso);
                    consultorio.setNumeroConsultorio(numeroCons);
                    hospital.registrarConsultorio(consultorio);
                    System.out.println("\nSe registró un nuevo consultorio");
                    break;

                case 4:

                    System.out.println("\n--------REGISTRAR CONSULTA--------");
                    id= hospital.generarIDConsulta();
                    LocalDateTime fechaConsulta= LocalDateTime.of(2005,06, 01, 10,30);

                    while (!hospital.validadFechaConsulta(fechaConsulta)) {
                        System.out.print("Ingresa el día de la consulta deseada: ");
                        int diaConsulta= leer.nextInt();
                        System.out.print("Ingresar el mes de la consultada deseada: ");
                        int mesConsulta= leer.nextInt();
                        System.out.print("Ingresa el año de la consulta deseada: ");
                        int añoConsulta= leer.nextInt();
                        System.out.print("Ingresa la hora de la consulta deseada: ");
                        int horaConsulta= leer.nextInt();
                        System.out.print("Ingresa los minutos de la consulta deseada: ");
                        int minutosConsulta= leer.nextInt();

                        fechaConsulta= LocalDateTime.of(añoConsulta,mesConsulta,diaConsulta,horaConsulta,minutosConsulta);

                        if (!hospital.validadFechaConsulta(fechaConsulta)) {

                            System.out.println("\nLa consulta no puede estár registrada en el pasado.\n");

                        }
                    }

                    Paciente pacienteConsulta = null;

                    while (pacienteConsulta == null) {

                        System.out.print("Ingresar ID del paciente: ");
                        String idPaciente= leer.next();

                        pacienteConsulta = hospital.obtenerPacientePorId(idPaciente);

                        if (pacienteConsulta == null) {
                            System.out.println("\nPaciente no encontrado. Intenta de nuevo.\n");
                        }
                    }

                    Medico medicoConsulta = null;

                    while (medicoConsulta == null) {

                        System.out.print("Ingresar ID del médico: ");
                        String idMedico= leer.next();
                        medicoConsulta = hospital.obtenerMedicoPorId(idMedico);

                        if (medicoConsulta == null) {
                            System.out.println("\nMédico no encontrado. Intenta de nuevo.\n");
                        }
                    }

                    Consultorio consultorioConsulta = null;

                    while (consultorioConsulta == null) {

                        System.out.print("Ingresar ID del consultorio: ");
                        String idConsultorio= leer.next();
                        consultorioConsulta = hospital.obtenerConsultorioPorId(idConsultorio);

                        if (consultorioConsulta == null) {

                            System.out.println("\nConsultorio no encontrado. Intenta de nuevo.\n");
                        }
                    }

                    Consulta consulta = new Consulta(id, fechaConsulta, pacienteConsulta, consultorioConsulta, medicoConsulta);

                    hospital.registrarConsulta(consulta);

                    consulta.setPaciente(pacienteConsulta);
                    consulta.setMedico(medicoConsulta);
                    consulta.setConsultorio(consultorioConsulta);
                    consulta.setFechaHora(fechaConsulta);
                    consulta.setId(id);
                    break;

                case 5:
                    System.out.println("\n--------MOSTRAR PACIENTES--------");
                    hospital.mostrarPaciente();
                    break;

                case 6:
                    System.out.println("\n--------MOSTRAR MÉDICOS--------");
                    hospital.mostrarMedicos();
                    break;

                case 7:
                    System.out.println("\n--------MOSTRAR CONSULTORIOS--------");
                    hospital.mostrarConsultorios();
                    break;

                case 8:
                    System.out.println("\n--------MOSTRAR CONSULTAS--------");
                    hospital.mostrarConsultas();
                    break;

                case 9:
                    System.out.println("\n---------MOSTRAR PACIENTE------------");
                    System.out.print("Ingresa el ID del paciente al que se desea buscar: ");
                    String idBuscarPaciente=leer.next();
                    hospital.mostrarPacientePorID(idBuscarPaciente);
                    break;

                case 10:
                    System.out.println("\n--------MOSTRAR MÉDICO--------");
                    System.out.print("Ingresa el ID del médico al que se desea buscar: ");
                    idBuscar=leer.next();
                    hospital.mostrarMedicoPorID(idBuscar);
                    break;

                case 11:
                    System.out.println("\n--------MOSTRAR CONSULTORIO--------");
                    System.out.print("Ingresa el ID del consultorio al que se desea buscar: ");
                    idBuscar=leer.next();
                    hospital.mostrarConsultorioPorID(idBuscar);
                    break;

                case 13:
                    System.out.println("\nCuide de su salud");
                    break;

                default:
                    System.out.println("\nOpción invalida");
            }
        }
    }
}
