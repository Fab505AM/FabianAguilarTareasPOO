package Tarea5U2POOISC;

public class CalculadoraImpuestos{
    int ingresos;
    double porcentajeImpuestos;
    double dividendos;
    double extencion;

    public CalculadoraImpuestos(int ingresos){
        this.ingresos = ingresos;
    }

    public CalculadoraImpuestos(int ingresos, double porcentajeImpuestos){
        this.ingresos = ingresos;
        this.porcentajeImpuestos = porcentajeImpuestos;
    }


    public CalculadoraImpuestos(double porcentajeImpuestos, double dividendos, double extencion){
        this.porcentajeImpuestos = porcentajeImpuestos;
        this.dividendos = dividendos;
        this.extencion = extencion;
    }
    
    public void calcularImpuestos(int ingresos){
        System.out.println("1.- El resultado es: " + (ingresos + 0.15));
    }

    public void calcularImpuestos(int ingresos, double porcentajeImpuestos){
        System.out.println("2.- El resultado es: " + (ingresos * (porcentajeImpuestos / 100)));
    }

    public void calcularImpuestos(double dividendos, double porcentajeImpuestos, double extencion){
        double impuestos = dividendos * (porcentajeImpuestos / 100);

        if (impuestos > extencion){
            System.out.println("3.- El resultado es: 0");
        } else {
            System.out.println("3.- El resultado es: " + impuestos);
        }
    }
 {
    
}
}