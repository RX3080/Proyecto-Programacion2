// Este paquete agrupa las clases que representan a los empleados
package gt.edu.umg;

// extends permite heredar los datos y metodos comunes de Empleado
public class EmpleadoFijo extends Empleado {

    private double sueldoMensual;
    private double bonificacion;

    // El constructor recibe los datos del empleado y guarda los que corresponden a este tipo
    public EmpleadoFijo(String dpi, String nombre, double sueldoMensual,
            double bonificacion) {
        // super envia el DPI y el nombre al constructor de Empleado sin duplicar esos atributos
        super(dpi, nombre);
        this.sueldoMensual = sueldoMensual;
        this.bonificacion = bonificacion;
    }

    // Permite consultar el sueldo mensual que se guardo al crear el empleado
    public double getSueldoMensual() {
        return sueldoMensual;
    }

    // Permite consultar la bonificacion correspondiente a este mes
    public double getBonificacion() {
        return bonificacion;
    }

    // Aqui se calcula el pago del empleado fijo sumando el sueldo y la bonificacion
    // @Override indica que se define el cálculo declarado en la clase Empleado
    @Override
    public double calcularPago() {
        return sueldoMensual + bonificacion;
    }

    // Se devuelve el tipo para que el reporte lo muestre sin preguntar que clase tiene el objeto
    @Override
    public String obtenerTipo() {
        return "Fijo";
    }
}