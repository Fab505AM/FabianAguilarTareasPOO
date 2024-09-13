package Tarea5U2POOISC;

import java.util.Scanner;

public class MainImpuestos {
    public static void main(String[] args){
        try (Scanner sc = new Scanner (System.in)) {
            int seleccion;
            System.out.println("Ingrese 1, si desea calcular solo el los ingresos");
            System.out.println("Ingrese 2, si desea calcular Ingresos y porcentaje impuesto");
            System.out.println("Ingrese 3, si desea calcular Dividendos, porcentaje impuesto y exencion");
            seleccion = sc.nextInt();

            while (seleccion < 1 || seleccion > 3){
                System.out.println("Selección invalida");
                System.out.println("Ingrese un dato valido");
                seleccion = sc.nextInt();
            }
            
            if (seleccion == 1){
                System.out.println("Introduzca el ingreso: ");
                int ingreso = sc.nextInt();

                CalculadoraImpuestos calcular = new CalculadoraImpuestos(ingreso);
                calcular.calcularImpuestos(ingreso);
            }
            else if (seleccion == 2){
                System.out.println("Introduzca el ingreso: ");
                int ingreso = sc.nextInt();
                System.out.println("Introduzca el porcentaje impuesto: ");
                double porcentajeImpuestos = sc.nextDouble();

                CalculadoraImpuestos calcular = new CalculadoraImpuestos(ingreso, porcentajeImpuestos);
                calcular.calcularImpuestos(ingreso, porcentajeImpuestos);
            }
            else {
                System.out.println("Introduzca el porcentaje impuesto: ");
                double porcentajeImpuestos = sc.nextDouble();
                System.out.println("Introduzca el dividendo: ");
                double dividendos = sc.nextDouble();
                System.out.println("Introduzca la exencion: ");
                double exencion = sc.nextDouble();

                CalculadoraImpuestos calcular = new CalculadoraImpuestos(porcentajeImpuestos, dividendos, exencion);
                calcular.calcularImpuestos(porcentajeImpuestos, dividendos, exencion);
            }
        }
     
        
    }
}
