package Tarea5U2POOISC;

import java.util.Scanner;

public class CalcularRectangulo {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner (System.in)) {
            System.out.println("Ingrese el tamaño de la altura: ");
            double lado1 = scanner.nextDouble();

            System.out.println("Ingrese el tamaño de las bases: ");
            double lado2 = scanner.nextDouble();

            MainRectangulo rectangulo1 = new MainRectangulo(lado1, lado2);

            rectangulo1.calcularArea(lado1, lado2);
            rectangulo1.calcularPerimetro(lado1, lado2);
        }
    }
}
