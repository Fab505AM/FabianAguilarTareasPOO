package Tarea5U2POOISC;
import java.util.Scanner;

public class MainEmpleado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    System.out.println("Ingrese su sueldo base: ");
    double sueldoBase = scanner.nextDouble();

    System.out.println("Ingrese su bonificacion (0 indicara que no tiene bonificacion): ");
    double bonificacion = scanner.nextDouble();
    
        if (bonificacion != 0){
            System.out.println("Ingrese sus horas extra (0 indicara que no tiene horas extra): ");
            double horasE = scanner.nextDouble();
            
            if(horasE != 0){
                MainEmpleado empleado1 = new MainEmpleado();
                empleado1.calcularSalario(sueldoBase, bonificacion, horasE);
            }
            else {
                MainEmpleado empleado1 = new MainEmpleado();
                empleado1.calcularSalario(sueldoBase, bonificacion, horasE);
            }

        }

        else {
            MainEmpleado empleado1 = new MainEmpleado();
            empleado1.calcularSalario(sueldoBase, bonificacion, bonificacion);
        }  
    }

    private void calcularSalario(double sueldoBase, double bonificacion, double horasE) {
       
        throw new UnsupportedOperationException("Unimplemented method 'calcularSalario'");
    }

}
