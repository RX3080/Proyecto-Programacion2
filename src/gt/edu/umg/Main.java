// Este paquete agrupa la clase que controla la aplicacion de consola
package gt.edu.umg;

// Los import permiten usar la coleccion la lectura de consola y las clases de empleados
import java.util.ArrayList;
import java.util.Scanner;

import gt.edu.umg.Empleado;
import gt.edu.umg.EmpleadoFijo;
import gt.edu.umg.EmpleadoPorHora;

public class Main {

    // Estos atributos privados se utilizan desde los métodos de Main para trabajar con la misma nómina.
    private static Scanner entrada = new Scanner(System.in);
    // El arreglo guarda los doce meses porque su cantidad no cambia.
    private static String[] meses = {
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };
    // La misma lista guarda empleados fijos y por hora conforme el usuario los registra.
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
    private static int numeroMes;

    // Aquí inicia el programa y se controla el menú para el usuario hasta que elija salir.
    public static void main(String[] args) {
        System.out.println("CÁLCULO DE NÓMINA");
        numeroMes = leerOpcion("Ingrese el mes a trabajar (1-12): ", 1, 12);

        // Esta variable local solo se utiliza dentro de main para recordar la opción elegida.
        int opcionMenuIngresada;
        do {
            limpiarConsolaEclipse();
            mostrarTitulo();
            System.out.println("1 - Ingreso de nómina");
            System.out.println("2 - Impresión de nómina");
            System.out.println("3 - Salir");
            opcionMenuIngresada = leerOpcion("Seleccione una opción: ", 1, 3);

            switch (opcionMenuIngresada) {
                case 1:
                    registrarEmpleados();
                    break;
                case 2:
                    imprimirNomina();
                    break;
                case 3:
                    System.out.println("Gracias por utilizar el sistema. ¡Adiós!");
                    break;
                default:
                    break;
            }
        } while (opcionMenuIngresada != 3);

        entrada.close();
    }

    // Se muestra el mes consultando el arreglo se resta uno porque los índices empiezan en cero
    private static void mostrarTitulo() {
        System.out.println("NÓMINA DEL MES: " + meses[numeroMes - 1]);
        System.out.println();
    }

    // Las lineas en blanco permiten distinguir cada pantalla en la consola de Eclipse
    private static void limpiarConsolaEclipse() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

 // Aqui se capturan los datos y se permite registrar otro empleado o volver al menu
    private static void registrarEmpleados() {
        int opcionIngreso;
        do {
            limpiarConsolaEclipse();
            mostrarTitulo();
            System.out.println("INGRESO DE EMPLEADO A NÓMINA");
            System.out.println("Trabajadores actuales = " + listaEmpleados.size());
            System.out.println("1 - Empleado fijo");
            System.out.println("2 - Empleado por hora");
            int tipo = leerOpcion("Seleccione el tipo de empleado: ", 1, 2);

            limpiarConsolaEclipse();
            mostrarTitulo();
            if (tipo == 1) {
                System.out.println("INGRESO DE EMPLEADO FIJO");
            } else {
                System.out.println("INGRESO DE EMPLEADO POR HORA");
            }

            String dpi = leerDpi();
            String nombre = leerNombre();
            Empleado empleado;

            if (tipo == 1) {
                double sueldoMensual = leerNumero("Sueldo mensual (Q): ");
                double bonificacion = leerNumero("Bonificación de este mes (Q): ");
                // new crea una instancia independiente dpi nombre y los montos son los argumentos del constructor
                empleado = new EmpleadoFijo(dpi, nombre, sueldoMensual, bonificacion);
                System.out.println("Pago del mes = sueldo mensual + bonificación");
            } else {
                double pagoPorHora = leerNumero("Pago por hora (Q): ");
                double horasTrabajadas = leerNumero("Horas trabajadas este mes: ");
                // Se crea otro tipo de empleado pero se conserva una referencia de tipo Empleado
                empleado = new EmpleadoPorHora(dpi, nombre, pagoPorHora, horasTrabajadas);
                System.out.println("Pago del mes = pago por hora x horas trabajadas");
            }

            // add guarda el objeto despues de validar todos sus datos; size obtiene la cantidad guardada
            listaEmpleados.add(empleado);
            System.out.printf("Pago de %s = Q %.2f%n",
                    empleado.getNombre(), empleado.calcularPago());
            System.out.println("Empleado agregado correctamente.");
            System.out.println("Trabajadores actuales = " + listaEmpleados.size());
            System.out.println();
            System.out.println("¿Desea ingresar otro empleado?");
            System.out.println("1 - Sí, ingresar otro empleado");
            System.out.println("2 - Volver al menú principal");
            opcionIngreso = leerOpcion("Seleccione una opción: ", 1, 2);
        } while (opcionIngreso != 2);
    }

    // Este metodo muestra los empleados y suma sus pagos sin cambiar los registros
    private static void imprimirNomina() {
        limpiarConsolaEclipse();
        mostrarTitulo();
        double totalNomina = 0;

        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados en la nómina de este mes");
        } else {
            System.out.printf("%-15s %-30s %-12s %s%n", "DPI", "Nombre", "Tipo", "Pago (Q)");
            // Se recorre el ArrayList con get cada objeto resuelve su pago mediante polimorfismo
            for (int a = 0; a < listaEmpleados.size(); a++) {
                Empleado empleado = listaEmpleados.get(a);
                double pago = empleado.calcularPago();
                totalNomina += pago;
                System.out.printf("%-15s %-30s %-12s %.2f%n",
                        empleado.getDpi(), empleado.getNombre(), empleado.obtenerTipo(), pago);
            }
        }
        
        System.out.println();
        System.out.println("Total de trabajadores: " + listaEmpleados.size());
        System.out.printf("Total de nómina: Q %.2f%n", totalNomina);
        System.out.println("Presione Enter para volver al menú principal...");
        entrada.nextLine();
    }

    // Los parametros indican que preguntar y cuales opciones aceptar; se devuelve una opcion valida
    private static int leerOpcion(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            // Se lee la linea completa para poder revisar si el usuario solo presiono Enter
            String datoIngresado = entrada.nextLine().trim();
            if (datoIngresado.isEmpty()) {
                System.out.println("No puede dejar este dato vacío. Ingrese un número entero.");
            } else {
                // Este Scanner revisa el dato escrito sin esperar otra linea de la consola
                Scanner dato = new Scanner(datoIngresado);
                if (dato.hasNextInt()) {
                    int opcion = dato.nextInt();
                    if (!dato.hasNext() && opcion >= minimo && opcion <= maximo) {
                        dato.close();
                        return opcion;
                    }
                    System.out.println("Ingrese una opción entre " + minimo + " y " + maximo + ".");
                } else {
                    System.out.println("Entrada inválida. Ingrese un número entero.");
                }
                dato.close();
            }
        }
    }

    // Se revisa que el DPI tenga trece digitos y se guarda como texto para conservar ceros iniciales
    private static String leerDpi() {
        while (true) {
            System.out.print("DPI: ");
            String dpi = entrada.nextLine().trim();
            boolean dpiValido = dpi.length() == 13;
            for (int i = 0; i < dpi.length(); i++) {
                if (dpi.charAt(i) < '0' || dpi.charAt(i) > '9') {
                    dpiValido = false;
                }
            }
            if (dpiValido) {
                return dpi;
            }
            System.out.println("El DPI debe contener exactamente 13 dígitos.");
        }
    }

    // Se vuelve a pedir el nombre cuando esta vacio o contiene solamente espacios
    private static String leerNombre() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = entrada.nextLine().trim();
            if (!nombre.isEmpty()) {
                return nombre;
            }
            System.out.println("El nombre no puede estar vacío ni contener solamente espacios.");
        }
    }

    // Se utiliza para pedir sueldos, bonificaciones, tarifas y horas sin aceptar texto ni negativos
    private static double leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            // Se revisa la linea para avisar cuando el usuario no escribe ningun valor
            String datoIngresado = entrada.nextLine().trim();
            if (datoIngresado.isEmpty()) {
                System.out.println("No puede dejar este dato vacío. Ingrese un número.");
            } else {
                // Se conserva hasNextDouble para comprobar el numero incluyendo sus decimales
                Scanner dato = new Scanner(datoIngresado);
                if (dato.hasNextDouble()) {
                    double numero = dato.nextDouble();
                    if (!dato.hasNext() && Double.isFinite(numero) && numero >= 0) {
                        dato.close();
                        return numero;
                    }
                    System.out.println("Ingrese un único valor finito mayor o igual a 0.");
                } else {
                    System.out.println("Entrada inválida. Ingrese un número.");
                }
                dato.close();
            }
        }
    }
}