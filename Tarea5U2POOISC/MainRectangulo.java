package Tarea5U2POOISC;

public class MainRectangulo {
    double lado1;
    double lado2;

    public MainRectangulo (double lado1, double lado2){
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public void calcularArea(double lado1, double lado2){
        double area = (lado1 * lado2);
        System.out.println("El area del triangulo es: " + area);
    }

    public void calcularArea(int lado1, int lado2){
        double area = (lado1 * lado2);
        System.out.println("El area del triangulo es: " + area);
    }

    public void calcularArea(int lado1, double lado2){
        double area = (lado1 * lado2);
        System.out.println("El area del triangulo es: " + area);
    }

    public void calcularArea(double lado1, int lado2){
        double area = (lado1 * lado2);
        System.out.println("El area del triangulo es: " + area);
    }

    public void calcularPerimetro(double lado1, double lado2){
        double perimetro = (lado1*2 + lado2*2);
        System.out.println("El perimetro del triangulo es: " + perimetro);
    }

    public void calcularPerimetro(int lado1, int lado2){
        double perimetro = (lado1*2 + lado2*2);
        System.out.println("El perimetro del triangulo es: " + perimetro);
    }

    public void calcularPerimetro(int lado1, double lado2){
        double perimetro = (lado1*2 + lado2*2);
        System.out.println("El perimetro del triangulo es: " + perimetro);
    }

    public void calcularPerimetro(double lado1, int lado2){
        double perimetro = (lado1*2 + lado2*2);
        System.out.println("El perimetro del triangulo es: " + perimetro);
    }
}
