package usuarios.pacientes;

import usuarios.Usuario;
import usuarios.utils.Rol;

import java.time.LocalDate;

public class Paciente extends Usuario {
    public String tipoSangre;
    public char sexo;

    public Paciente(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String tipoSangre, char sexo, String telefono, String contrasenia) {
        super(id, nombre, apellidos, fechaNacimiento, telefono, contrasenia, Rol.PACIENTE);
        this.tipoSangre = tipoSangre;
        this.sexo = sexo;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public char getSexo() {
        return sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String mostrarDatos() {
        return String.format("Id: %s  |  Nombre: %s  |  Apellidos: %s  |  Fecha de Nacimiento: %s  |  Tipo de Sangre: %s  |  Sexo: %s  |  Telefono: %s",
                getId(), getNombre(), getApellidos(), getFechaNacimiento(), getTipoSangre(), getSexo(), getTelefono());
    }
}
