package hospital;

import consultas.Consulta;
import consultas.utils.Status;
import consultorios.Consultorio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import usuarios.Usuario;
import usuarios.admin.Admin;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;
import usuarios.utils.Rol;

public class Hospital {

    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    public ArrayList<Medico> listaMedicos = new ArrayList<>();
    public ArrayList<Consulta> listaConsulta = new ArrayList<>();
    public ArrayList<Consultorio> listaConsultorio = new ArrayList<>();
    public ArrayList<Admin> listaAdmin = new ArrayList<>();

    private final ValidadorHospital validador = new ValidadorHospital();
    Random ran = new Random();

    public Hospital() {
        Admin admin = new Admin("A2024ADMIN", "Admin", "Default", LocalDate.now(),
                "4435867439", "Admin123",
                12388888844.555, "DHAK3242RRH3", "2", Rol.ADMIN);
        this.listaUsuarios.add(admin);
        this.listaAdmin.add(admin);
    }

    public void registrarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);
        this.listaUsuarios.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
        this.listaUsuarios.add(medico);
    }

    public void registrarConsulta(Consulta consulta) {
        if (!validador.validarDisponibilidadEnFecha(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio(), this.listaConsulta)) {
            System.out.println("Ya existe una consulta registrada para esa fecha");
            return;
        }

        if (!validador.validarDisponibilidadMedico(consulta.getFechaHora(), consulta.getMedico().getId(), this.listaConsulta)) {
            System.out.println("\nEl medico no tiene disponibilidad para esa fecha");
            return;
        }
        this.listaConsulta.add(consulta);
    }

    public boolean validarNumeroTelefono(String telefonoDeseado) {
        return !validador.validarTelefono(telefonoDeseado, this.listaPacientes);
    }

    public boolean validarRFCMedico(String Rfc) {
        return !validador.validarRFC(Rfc, this.listaMedicos);
    }

    public void registrarConsultorio(Consultorio consultorio) {
        this.listaConsultorio.add(consultorio);
    }

    public void mostrarPaciente() {
        if (this.listaPacientes.isEmpty()) {
            System.out.println("\nNo hay usuarios.pacientes registrados :(");
            return;
        }
        for (Paciente paciente : this.listaPacientes) {
            paciente.mostrarDatos();
        }
    }

    public void mostrarConsultorios() {
        if (this.listaConsultorio.isEmpty()) {
            System.out.println("\nNo hay consultorios registrados :(");
            return;
        }
        for (Consultorio consultorio : this.listaConsultorio) {
            consultorio.mostrarConsultorio();
        }
    }

    public void mostrarMedicos() {
        if (this.listaMedicos.isEmpty()) {
            System.out.println("\nNo hay médicos registrados :(");
            return;
        }
        for (Medico medico : this.listaMedicos) {

            medico.mostrarMedico();
        }
    }
    public void mostrarConsultas(){

        if (this.listaConsulta.isEmpty()) {
            System.out.println("\nNo hay consultas registrados :(");
            return;
        }
        for (Consulta consulta : this.listaConsulta) {

            consulta.mostrarConsulta();
        }

    }

    public String generarIDpaciente() {
        //P(paciente)-(año actual)-(mes actual)-longitud de la lista usuarios.pacientes + 1)-(1-100000)

        LocalDate fecha = LocalDate.now();
        int añoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int longitudPacientesMasUno = this.listaPacientes.size() + 1;
        int numeroAleatorio = ran.nextInt(1, 100000);

        String id = String.format("P%d%d%d%d", añoActual, mesActual, longitudPacientesMasUno, numeroAleatorio).toUpperCase();
        return id;
    }

    public String generarIDMedico(String apellidos, int año) {
        //M-(Primeras dos letras de su apellido)-(último dígito de su año de nacimiento)-(Año actual)-(Número
        // aleatorio entre 50 y 700000)-(longitud de la lista de médicos + 1)

        LocalDate fecha = LocalDate.now();
        int añoActual = fecha.getYear();
        char PrimerLetra = apellidos.charAt(0);
        char SegundaLetra = apellidos.charAt(1);
        int digitoaño = año % 10;
        int numero = ran.nextInt(50, 700000);
        int longitud = this.listaMedicos.size() + 1;

        String id = String.format("M%s%c%d%d%d%d", PrimerLetra, SegundaLetra, digitoaño, añoActual, numero, longitud).toUpperCase();
        return id;
    }

    public String generarIDConsultorio() {
        //C-(Longitud de la lista de consultorios + 1)-(dia actual)-(añoactual)-(numeroaleatorio entre 1 y 500000)

        LocalDate fecha = LocalDate.now();
        int diaActual = fecha.getDayOfMonth();
        int añoActual = fecha.getYear();
        int numero = ran.nextInt(1, 500000);
        int longitud = this.listaMedicos.size() + 1;

        String id = String.format("C%d%d%d%d", longitud, diaActual, añoActual, numero).toUpperCase();
        return id;
    }
    public String generarIDConsulta(){
        //CT-(Longitud de la lista Consultas +1)-(mes actual)-(numeroaleatorio entre 1 y 500000)

        LocalDate fecha = LocalDate.now();
        int tamaño=this.listaMedicos.size()+1;
        int mes=fecha.getMonthValue();
        int aleatorio=ran.nextInt(1, 500000);

        String id = String.format("CT%d%d%d", tamaño, mes, aleatorio).toUpperCase();
        return id;
    }
    public Paciente obtenerPacientePorId(String id) {
        return listaPacientes.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

    }
    public Medico obtenerMedicoPorId(String id) {
        return listaMedicos.stream().filter(medico -> medico.getId().equals(id)).findFirst().orElse(null);

    }
    public Consultorio obtenerConsultorioPorId(String id) {
        return listaConsultorio.stream().filter(consultorio -> consultorio.getId().equals(id)).findFirst().orElse(null);

    }
    public void mostrarPacientePorID(String id) {
      Paciente paciente = this.listaPacientes.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

            if (paciente!=null) {
                paciente.mostrarDatos();
            }
            else {
                System.out.println("\nPaciente no encontrado.");
            }
     }
    public void mostrarMedicoPorID(String id) {
        Medico medicoEncontrado = this.listaMedicos.stream().filter(medico -> medico.getId().equals(id)).findFirst().orElse(null);

        if (medicoEncontrado != null) {

            medicoEncontrado.mostrarMedico();
        }
        else {
            System.out.println("\nMédico no encontrado.");
        }
    }
    public void mostrarConsultorioPorID(String id) {
        Consultorio consultorioEncontrado = this.listaConsultorio.stream().filter(consultorio -> consultorio.getId().equals(id)).findFirst().orElse(null);

        if (consultorioEncontrado != null) {

            consultorioEncontrado.mostrarConsultorio();
        }
        else {
            System.out.println("\nConsultorio no encontrado.");
        }
    }

    public boolean validadFechaConsulta(LocalDateTime fechaDeseada) {

        return this.validador.validarFechaCorrecta(fechaDeseada);

    }

    public Usuario validadInicioSesion(String idUsuario, String contraseña){

        for(Usuario usuario : this.listaUsuarios){

            if (idUsuario.equals(usuario.getId()) && contraseña.equals(usuario.getContraseña())){
                return usuario;
            }
        }
        return null;
    }
    public void verConsultasPaciente(String idPaciente){
        for(Consulta consulta : this.listaConsulta){
            boolean existeConsulta = false;
            if (idPaciente.equals(consulta.getPaciente().getId()) && consulta.getStatus() == Status.PENDIENTE) {
                existeConsulta = true;
                consulta.mostrarConsulta();
            }
            if(existeConsulta){
                System.out.println("\nNo tiene consultas agendadas.");
            }
        }
    }

    public void verConsultasMedico(String idMedico){
        for(Consulta consulta : this.listaConsulta){
            boolean existeConsulta = false;
            if (idMedico.equals(consulta.getMedico().getId()) && consulta.getStatus() == Status.PENDIENTE) {
                existeConsulta = true;
                consulta.mostrarConsulta();
            }
            if(existeConsulta){
                System.out.println("\nNo tiene consultas agendadas.");
            }
        }
    }
}