package consultorios;

public class Consultorio {

    public String id;
    public int piso;
    public int numeroConsultorio;

    public Consultorio(String id, int piso, int numeroConsultorio) {
        this.id = id;
        this.piso = piso;
        this.numeroConsultorio = numeroConsultorio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(int numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public void mostrarConsultorio() {
        System.out.println("\nId: " + getId());
        System.out.println("Piso: " + getPiso());
        System.out.println("Numero Consultorio: " + getNumeroConsultorio());
    }
}
