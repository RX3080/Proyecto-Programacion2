// Este paquete agrupa las clases que representan a los empleados
package gt.edu.umg;

// extends permite heredar los datos y metodos comunes de Empleado
public class EmpleadoPorHora extends Empleado {

    private double pagoPorHora;
    private double horasTrabajadas;

    // El constructor recibe los datos del empleado y guarda los que corresponden a este tipo
    public EmpleadoPorHora(String dpi, String nombre, double pagoPorHora,
            double horasTrabajadas) {
        // super envia el DPI y el nombre al constructor de Empleado sin duplicar esos atributos
        super(dpi, nombre);
        this.pagoPorHora = pagoPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    // Permite consultar cuanto recibe el empleado por cada hora
    public double getPagoPorHora() {
        return pagoPorHora;
    }

    // Permite consultar las horas del mes, incluyendo sus decimales
    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    // Aqui se calcula el pago multiplicando la tarifa por las horas trabajadas
    // @Override indica que se define el calculo declarado en la clase Empleado
    @Override
    public double calcularPago() {
        return pagoPorHora * horasTrabajadas;
    }

    // Se devuelve el tipo para que el reporte lo muestre sin preguntar qué clase tiene el objeto
    @Override
    public String obtenerTipo() {
        return "Por hora";
    }
}