package Tarea5U2POOISC;
public class empleado {
    

    double sueldoBase;
    double bonificacion;
    double horasE;

    public empleado (double sueldoBase, double bonificacion, double horasE){
        this.sueldoBase = sueldoBase;
        this.bonificacion = bonificacion;
        this.horasE = horasE;
    }

    public empleado (double sueldoBase, double bonificacion){
        this.sueldoBase = sueldoBase;
        this.bonificacion = bonificacion;
    }

    public void calcularSalario (double sueldoBase){
        System.out.println("Su salario es el mismo: " + sueldoBase);
    }

    public void calcularSalario (double sueldoBase, double bonificacion){
        System.out.println("Su salario total aplicando su bonificacion es de: " + (sueldoBase + bonificacion));
    }

    public void calcularSalario (double sueldoBase, double bonificacion, double horasE){
        System.out.println("Su salario total aplicando su bonificacion es de: " + ((sueldoBase + bonificacion) + (horasE * 20)));
    }
}