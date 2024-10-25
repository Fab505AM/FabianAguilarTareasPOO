package usuarios.administradores;

import usuarios.Usuario;
import usuarios.utils.Rol;

import java.time.LocalDate;

public class Administrador extends Usuario{
    public double sueldo;
    public String rfc;
    public int antiguedad;

    public Administrador(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String contrasenia, double sueldo, String rfc, int antiguedad) {
        super(id, nombre, apellidos, fechaNacimiento, telefono, contrasenia, Rol.ADMINISTRADOR);
        this.sueldo = sueldo;
        this.rfc = rfc;
        this.antiguedad = antiguedad;
    }

    public double getSueldo() {
        return sueldo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public String getRfc() {
        return rfc;
    }

    public String mostrarDatos() {
        return String.format("Id: %s, nombre: %s, apellidos: %s, fecha de nacimiento: %s, teléfono: %s, sueldo: %f, RFC: %s, antiguedad: %d",
                getId(),
                getNombre(),
                getApellidos(),
                getFechaNacimiento(),
                getTelefono(),
                getSueldo(),
                getRfc(),
                getAntiguedad()
        );
    }
}
