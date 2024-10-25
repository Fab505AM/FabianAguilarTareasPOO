package consultas;

import consultas.utils.Status;
import consultorios.Consultorio;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;

public class Consulta {
    public String id;
    public LocalDateTime fechaHora;
    public Paciente paciente;
    public Medico medico;
    public Consultorio consultorio;
    public Status status;

    public Consulta(String id, LocalDateTime fechaHora, Paciente paciente, Medico medico, Consultorio consultorio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
        this.status = Status.PENDIENTE;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Consultorio getConsultorio() { return consultorio; }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String mostrarDatos() {
        return String.format("Id: %s  |  Fecha: %s  |  Id Paciente: %s  |  Paciente: %s  |  Id Medico: %s  |  Médico: %s  |  Piso Consultorio: %d  |  Numero Consultorio: %d",
                getId(),
                getFechaHora(),
                paciente.getId(),
                paciente.getNombre(),
                medico.getId(),
                medico.getNombre(),
                consultorio.getPiso(),
                consultorio.getNumeroConsultorio()
        );
    }
}