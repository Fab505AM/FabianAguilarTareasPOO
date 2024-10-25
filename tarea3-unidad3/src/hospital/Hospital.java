package hospital;



import consultas.Consulta;
import consultas.utils.Status;
import consultorios.Consultorio;
import expediente.Expediente;
import usuarios.Usuario;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;
import usuarios.administradores.Administrador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hospital {
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    public ArrayList<Medico> listaMedicos = new ArrayList<>();
    public ArrayList<Consulta> listaConsultas = new ArrayList<>();
    public ArrayList<Consultorio> listaConsultorios = new ArrayList<>();
    public ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    private ValidadorHospital validador = new ValidadorHospital();
    Random random = new Random();

    public Hospital() {
        Administrador administrador = new Administrador("A-123456", "Geovani", "Lobato", LocalDate.of(1980, 7, 11), "4498326548", "Geo1107*", 50000, "GD156AD5AEF", 5);
        this.listaUsuarios.add(administrador);
        this.listaAdministradores.add(administrador);
    }

    public void registrarPacientes(Paciente paciente) {
        this.listaPacientes.add(paciente);
        this.listaUsuarios.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
        this.listaUsuarios.add(medico);
    }

    public void registrarConsulta(Consulta consulta) {
        //  No exista una consulta en la fecha deseada
        if (!validador.validarDisponibilidadEnFechaConsulta(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio(), this.listaConsultas)) {
            System.out.println("Ya existe una consulta registrada para esa fecha");
            return;
        }

        // Validar disponibilidad del médico
        if (!validador.validarDisponibilidadMedico(consulta.getFechaHora(), consulta.getMedico().getId(), this.listaConsultas)) {
            System.out.println("El médico no tiene disponibilidad para esa fecha");
            return;

        }

        this.listaConsultas.add(consulta);
    }

    public void registrarConsultorio(Consultorio consultorio) {
        this.listaConsultorios.add(consultorio);
    }

    public void registrarAdministrador(Administrador admin) {
        this.listaAdministradores.add(admin);
        this.listaUsuarios.add(admin);
    }

    public void mostrarPacientes() {
        System.out.println("\n** Pacientes del Hospital **");
        for (Paciente paciente : this.listaPacientes) {
            System.out.println(paciente.mostrarDatos());
        }
    }

    public void mostrarMedicos() {
        System.out.println("\n** Medicos del Hospital **");
        for (Medico medico : this.listaMedicos) {
            System.out.println(medico.mostrarMedico());
        }
    }

    public void mostrarConsultorio(){
        System.out.println("\n** Consultorios del Hospital **");
        for (Consultorio consultorio : this.listaConsultorios) {
            System.out.println(consultorio.mostrarConsultorio());
        }
    }

    public void mostrarConsultas() {
        System.out.println("\n** Consultas del Hospital **");
        for (Consulta consulta : this.listaConsultas) {
            System.out.println(consulta.mostrarDatos());
        }
    }

    public void mostrarAdministradores() {
        for(Administrador administrador : this.listaAdministradores) {
            System.out.println(administrador.mostrarDatos());
        }
    }

    public Paciente obtenerPacientePorId(String idPaciente) {
        return listaPacientes.stream().filter(paciente -> paciente.getId().equals(idPaciente)).findFirst().orElse(null);
    }

    public Medico obtenerMedicoPorId(String idMedico) {
        return listaMedicos.stream().filter(medico -> medico.getId().equals(idMedico)).findFirst().orElse(null);
    }

    public Consultorio obtenerConsultorioPorId(String idConsultorio) {
        return listaConsultorios.stream().filter(consultorio -> consultorio.getId().equals(idConsultorio)).findFirst().orElse(null);
    }

    public void mostrarPacienteID(String idPaciente) {
        Paciente paciente = obtenerPacientePorId(idPaciente);

        if (paciente != null) {
            System.out.println(paciente.mostrarDatos());
        } else {
            System.out.println("No se encontro el paciente");
        }
    }

    public void mostrarMedicoPorId(String idMedico){
        Medico medico = obtenerMedicoPorId(idMedico);

        if (medico != null) {
            System.out.println(medico.mostrarMedico());
        }else{
            System.out.println("No se encontro el medico");
        }
    }

    public void mostrarConsultorioPorId(String idConsultorio){
        Consultorio consultorio = obtenerConsultorioPorId(idConsultorio);

        if (consultorio != null) {
            System.out.println(consultorio.mostrarConsultorio());
        }else {
            System.out.println("No se encontro el consultorio");
        }
    }

    public String generarIdPaciente() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anioActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int longitudPacienteMasUno = this.listaPacientes.size()+1;
        int numeroAleatorio = random.nextInt();

        return String.format("P%d%d%d%d", anioActual, mesActual, longitudPacienteMasUno, numeroAleatorio);
    }

    public String generarIdMedico(String apellidoMedico, String fechaNaciMedico){
        //M-{Primeras 2 letras de su apellido} - {ultimo dígito de su año de nacimiento} - {año actual} - {numero aleatorio entre 50 y 700000} - {longitud de la lista de usuarios.medicos + 1}
        LocalDate fecha = LocalDate.now();
        int anioActual = fecha.getYear();
        int longitudMedicoMasUno = this.listaMedicos.size() + 1;
        int numeroAleatorio = random.nextInt(700000+50);
        char letra1 = apellidoMedico.charAt(0);
        letra1= Character.toUpperCase(letra1);
        char letra2 = apellidoMedico.charAt(1);
        letra2= Character.toUpperCase(letra2);
        String letrasApellidos = letra1 + "" + letra2;
        char ultimoDigitoNaci= fechaNaciMedico.charAt(fechaNaciMedico.length() - 1);

        return String.format("M%s%c%d%d%d",letrasApellidos, ultimoDigitoNaci, anioActual, numeroAleatorio,longitudMedicoMasUno);
    }

    public String generarIdConsultorio(){
        //C-{longitud de la lista de consultorios + 1}-{dia actual}-{año actual}-{numero aleatorio entre 1 y 500000}
        LocalDate fecha = LocalDate.now();
        int anioActual = fecha.getYear();
        int diaActual = fecha.getDayOfMonth();
        int longitudConsultorio = this.listaConsultorios.size() + 1;
        int NumAleatorio = random.nextInt(500000);
        return String.format("C%d%d%d%d",longitudConsultorio,diaActual,anioActual,NumAleatorio);
    }

    public String generarIdConsulta() {
        int diaActual = LocalDate.now().getDayOfMonth();
        int numeroAleatorio = random.nextInt(100000 - 50) + 50;
        return String.format("CO-%d-%d-%d", listaConsultas.size() + 1, numeroAleatorio, diaActual);
    }

    public String generarIdAdmin(String apellido, String fechaNacimiento) {
        LocalDate fecha = LocalDate.now();
        Random random = new Random();
        String ap = apellido.substring(0, 2).toUpperCase();
        char ultimoDigito = fechaNacimiento.charAt(fechaNacimiento.length()-1);
        int yearActual = fecha.getYear();
        int aleatorio = random.nextInt(51,70001);
        int lista = listaAdministradores.size()+1;
        return String.format("A-%s-%s-%d-%d-%d",
                ap,
                ultimoDigito,
                yearActual,
                aleatorio,
                lista);
    }

    public boolean validarFechaConsulta(LocalDateTime fechaDeseada) {
        return this.validador.validarFechaCorrecta(fechaDeseada);
    }

    public boolean validarTelefonoPaciente(String telefono) {
        return this.validador.validarTelefonoPaciente(telefono, this.listaPacientes);
    }

    public boolean validarTelefonoMedico(String telefono) {
        return this.validador.validarTelefonoMedico(telefono, this.listaMedicos);
    }

    public boolean validarRFCMedico(String rfc) {
        return this.validador.validarRFCMedico(rfc, this.listaMedicos);
    }

    public void mostrarConsultasPorPaciente(String idPaciente) {
        List<Consulta> consultasDelPaciente = listaConsultas.stream()
                .filter(c -> c.getPaciente().getId().equals(idPaciente))
                .toList();

        if (consultasDelPaciente.isEmpty()) {
            System.out.println("No se encontraron consultas: ");
        } else {
            System.out.println("Consultas registradas: ");
            for (Consulta consulta : consultasDelPaciente) {
                System.out.println(consulta.mostrarDatos());
            }
        }
    }

    public void mostrarConsultasPorMedico(String idMedico) {
        List<Consulta> consultasDelMedico = listaConsultas.stream()
                .filter(c -> c.getMedico().getId().equals(idMedico))
                .toList();

        if (consultasDelMedico.isEmpty()) {
            System.out.println("No se encontraron consultas: ");
        } else {
            System.out.println("Consultas registradas: ");
            for (Consulta consulta : consultasDelMedico) {
                System.out.println(consulta.mostrarDatos());
            }
        }
    }

    public List<String> obtenerNombresPacientesPorMedico(String idMedico) {
        return listaConsultas.stream()
                .filter(c -> c.getMedico().getId().equals(idMedico))
                .map(c -> c.getPaciente().mostrarDatos().concat("\n"))
                .collect(Collectors.toList());
    }

    public Administrador obtenerRfcAdmin(String rfc) {
        return listaAdministradores.stream().filter(a -> a.getRfc().equals(rfc)).findFirst().orElse(null);
    }

    public Administrador obtenerTelefonoAdmin(String telefonoAdmin) {
        return listaAdministradores.stream().filter(a -> a.getTelefono().equals(telefonoAdmin)).findFirst().orElse(null);
    }

    public Usuario validarInisioSesion(String idUsuario, String contrasenia) {
        for (Usuario usuario : this.listaUsuarios) {
            if (usuario.getId().equals(idUsuario) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }

        return null;
    }

    public void verConsultasPaciente(String idPaciente) {
        boolean existenConsultas = false;
        for (Consulta consulta : this.listaConsultas) {
            if (idPaciente.equals(consulta.getPaciente().getId()) && consulta.getStatus() == Status.PENDIENTE) {
                existenConsultas = true;
                System.out.println(consulta.mostrarDatos());
            }
        }

        if (!existenConsultas) {
            System.out.println("\n No tienes consultas agregadas");
        }
    }

    public Consulta obternerConsultaPorId(String idConsulta) {
        for (Consulta consulta : this.listaConsultas) {
            if (consulta.getId().equals(idConsulta)) {
                return consulta;
            }
        }

        return null;
    }

    public void eliminarConsultaPorId(String idConsulta) {
        for (Consulta consulta : this.listaConsultas) {
            if (consulta.getId().equals(idConsulta)) {
                this.listaConsultas.remove(consulta);
                return;
            }
        }
    }

    public void generarExpedienteConsulta(String idConsulta, String idPaciente) {
        Scanner scanner = new Scanner(System.in);
        Consulta consulta = this.obternerConsultaPorId(idConsulta);

        if (consulta == null) {
            System.out.println("No existe una consulta con el ID proorcionado");
            return;
        }

        Paciente paciente = this.obtenerPacientePorId(idPaciente);

        if (paciente == null) {
            System.out.println("No existe con el ID proporcionado");
            return;
        }

        consulta.setStatus(Status.TERMINADA);

        this.eliminarConsultaPorId(idConsulta);

        System.out.println("Ingresa las observaciones finales de la consulta:");
        String observaciones = scanner.nextLine();

        Expediente expediente = new Expediente(consulta,observaciones);
        paciente.registrarExpediente(expediente);
    }
}