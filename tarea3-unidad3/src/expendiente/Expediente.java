package expediente;

import consultas.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Expediente {
    public String id;
    public Consulta consultas;
    public String observaciones;
    public LocalDateTime fechaExpediente;

    public Expediente(Consulta consultas, String observaciones) {
        this.id = generarId();
        this.consultas = consultas;
        this.observaciones = observaciones;
        this.fechaExpediente = LocalDateTime.now();
    }

    public String generarId() {
        Random random = new Random();
        LocalDate fecha = LocalDate.now();

        int anioActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int numeroAleatorio = random.nextInt(1, 100000);

        return String.format("E%d%d%d", anioActual, mesActual, numeroAleatorio);
    }
}