package consultas;

import consultas.utils.Status;
import consultorioss.Consultorio;

import java.time.LocalDateTime;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;

public class Consulta {

    public String id;
    public LocalDateTime fechaHora;
    public Paciente paciente;
    public Consultorio consultorio;
    public Medico medico;
    public Status status;

    public Consulta(String id, LocalDateTime fechaHora, Paciente paciente, Consultorio consultorio, Medico medico) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.medico = medico;
        this.status = Status.PENDIENTE;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
    public void setMedico(Medico medico){
        this.medico = medico;
    }
    public Medico getMedico() {
        return medico;
    }

    public Status getStatus() {
        return status;
    }

    public void mostrarConsulta(){

        System.out.println("ID: "+ getId());
        System.out.println("Fecha y Hora: "+ getFechaHora());
        System.out.println("ID del paciente: "+ paciente.getId());
        System.out.println("Nombre del paciente: "+ paciente.getNombre());
        System.out.println("ID del médico: "+ medico.getId());
        System.out.println("Nombre del medico: "+ medico.getNombre());
        System.out.println("Piso donde se encuentra el consultorio: "+ consultorio.getPiso());
        System.out.println("Número del consultorio: "+ consultorio.getNumeroConsultorio());

    }
}
