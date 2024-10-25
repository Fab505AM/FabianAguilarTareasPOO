package usuarios.pacientes;
import java.time.LocalDate;
import usuarios.Usuario;
import usuarios.utils.Rol;

public class Paciente extends Usuario {

    public String tipoSangre;
    public String sexo;

    public Paciente(String id, String nombre, String apellidos, LocalDate fechaNacimiento,
                    String telefono, String tipoSangre, String sexo, String contraseña) {
        super(id, nombre, apellidos, fechaNacimiento, telefono, contraseña, Rol.PACIENTE);
        this.tipoSangre = tipoSangre;
        this.sexo = sexo;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void mostrarDatos() {
        System.out.println("\nID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("FechaNacimiento: " + getFechaNacimiento());
        System.out.println("Tipo de sangre: " + getTipoSangre());
        System.out.println("Sexo: " + getSexo());
        System.out.println("Telefono: " + getTelefono());
    }
}

