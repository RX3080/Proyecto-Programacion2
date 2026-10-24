// Este paquete agrupa las clases que representan a los empleados
package gt.edu.umg;

// La clase abstracta reúne los datos comunes; los objetos se crean a partir de sus clases hijas
public abstract class Empleado {

    // Los atributos privados protegen los datos; los métodos de esta clase pueden consultarlos
    private String dpi;
    private String nombre;

    // dpi y nombre son parametros disponibles dentro del constructor
    // this.nombre identifica el atributo y nombre identifica el parámetro recibido
    public Empleado(String dpi, String nombre) {
        this.dpi = dpi;
        this.nombre = nombre;
    }

    // Este getter permite consultar el DPI sin acceder directamente al atributo privado
    public String getDpi() {
        return dpi;
    }

    // Este getter devuelve el nombre para mostrarlo en la confirmación y en el reporte
    public String getNombre() {
        return nombre;
    }

    // Cada clase hija debe definir cómo calcular y devolver su pago
    public abstract double calcularPago();

    // Cada clase hija devuelve su tipo para mostrarlo en el reporte
    public abstract String obtenerTipo();
}