package hospital;

import consultas.Consulta;
import consultorios.Consultorio;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Hospital {

    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    public ArrayList<Medico> listaMedicos = new ArrayList<>();
    public ArrayList<Consulta> listaConsultas = new ArrayList<>();
    public ArrayList<Consultorio> listaConsultorios = new ArrayList<>();
    private ValidadorHospital validador = new ValidadorHospital();
    Random random = new Random();

    public void registrarPacientes(Paciente paciente) {
        this.listaPacientes.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
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
}