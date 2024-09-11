package Tarea4U2POOISC;

public class coche {
    
String marca;
String modelo;
int año;

    public coche(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
}

    public void mostrarCarro() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + año);
    
}
    public void calcularEdad() {
        int edad = 2024 - año;
        System.out.println("La edad del coche es: " + edad + " años");
    }





}   
