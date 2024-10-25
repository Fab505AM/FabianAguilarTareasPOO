package hospital;

import consultas.Consulta;
import usuarios.medicos.Medico;
import usuarios.pacientes.Paciente;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ValidadorHospital {
    public boolean validarDisponibilidadEnFechaConsulta(LocalDateTime fecheDeseada, int numeroConsultorio, ArrayList<Consulta> listaConsultas) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getFechaHora().isEqual(fecheDeseada) && numeroConsultorio == consulta.getConsultorio().getNumeroConsultorio()) {
                return false;
            }
        }

        return true;
    }

    public boolean validarDisponibilidadMedico(LocalDateTime fechaDeseada, String idMedico, ArrayList<Consulta> listaConsultas) {
        for (Consulta consulta : listaConsultas) {
            if (consulta.getFechaHora().isEqual(fechaDeseada) && consulta.getMedico().getId().equals(idMedico)) {
                return false;
            }
        }

        return true;
    }

    public boolean validarFechaCorrecta(LocalDateTime fechaDeseada) {
        LocalDateTime fechaActual = LocalDateTime.now();

        if (fechaDeseada.isBefore(fechaActual)) {
            return false;
        }

        return true;
    }

    public boolean validarTelefonoPaciente(String telefono, ArrayList<Paciente> listaPacientes) {
        for (Paciente paciente : listaPacientes) {
            if (telefono.equals(paciente.getTelefono())) {
                return false;
            }
        }

        return true;
    }

    public boolean validarTelefonoMedico(String telefono, ArrayList<Medico> listaMedicos) {
        for (Medico medico : listaMedicos) {
            if (telefono.equals(medico.getTelefono())) {
                return false;
            }
        }

        return true;
    }

    public boolean validarRFCMedico(String rfc, ArrayList<Medico> listaMedicos) {
        for (Medico medico : listaMedicos) {
            if (rfc.equals(medico.getRfc())) {
                return false;
            }
        }

        return true;
    }
}

