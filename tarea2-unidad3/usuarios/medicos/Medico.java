package usuarios.medicos;

import java.time.LocalDate;
import usuarios.Usuario;
import usuarios.utils.Rol;

public class Medico extends Usuario {

    public String telefono; 
    private String rfc;

    public Medico(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String rfc, String contraseña) {
        super(id, nombre, apellidos, fechaNacimiento, telefono, contraseña, Rol.MEDICO);
        this.telefono = telefono;
        this.rfc = rfc;
    }

    public Medico(String id, String nombre, String apellidos, LocalDate fechaNacimiento, String telefono, String contraseña, Rol rol) {
        super(id, nombre, apellidos, fechaNacimiento, telefono, contraseña, rol);
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void mostrarMedico() {
        System.out.println("\nID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("FechaNacimiento: " + getFechaNacimiento());
        System.out.println("Telefono: " + getTelefono());
        System.out.println("RFC: " + getRfc());
    }
}