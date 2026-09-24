import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            String nombre = leerNombre(entrada);
            String id = leerId(entrada);
            double salarioBase = leerSalarioBase(entrada);
            int equipos = leerEquipos(entrada);

            Empleado[] equipo = {
                new Piloto("Ana Gómez", "E-101", 4500.0, 60),
                new TecnicoMantenimiento("Luis Rivas", "E-102", 3800.0, 3),
                new AgenteVentas("Carla Mux", "E-103", 3200.0, 900.0),
                new Supervisor(nombre, id, salarioBase, equipos)
            };

            System.out.println("\nSalarios:");

            for (Empleado empleado : equipo) {
                System.out.printf(
                    Locale.US,
                    "%s: Q%.2f%n",
                    empleado.getNombre(),
                    empleado.calcularSalario()
                );
            }
        }
    }

    private static String leerNombre(Scanner entrada) {
        while (true) {
            System.out.print(
                "Nombre del supervisor (solo letras y espacios): "
            );

            String nombre = Normalizer.normalize(
                entrada.nextLine().trim(),
                Normalizer.Form.NFC
            );

            if (nombre.matches("\\p{L}+(?: +\\p{L}+)*")) {
                return nombre;
            }

            System.out.println(
                "Error: escribe un nombre con letras y espacios, "
                + "sin números ni símbolos. No puede estar vacío."
            );
        }
    }

    private static String leerId(Scanner entrada) {
        while (true) {
            System.out.print(
                "ID del supervisor (solo números, sin espacios ni guiones): "
            );

            String id = entrada.nextLine().trim();

            if (id.matches("[0-9]+")) {
                return id;
            }

            System.out.println(
                "Error: el ID debe contener únicamente números del 0 al 9."
            );
        }
    }

    private static double leerSalarioBase(Scanner entrada) {
        while (true) {
            System.out.print(
                "Salario base (número positivo, hasta 2 decimales: Q"
            );

            String texto = entrada.nextLine().trim();

            if (texto.matches("[0-9]+([.,][0-9]{1,2})?")) {
                double salario = Double.parseDouble(
                    texto.replace(',', '.')
                );

                if (Double.isFinite(salario) && salario > 0) {
                    return salario;
                }
            }

            System.out.println(
                "Error: ingresa un salario mayor que 0. "
                + "Ejemplos: 2000, 2000.50 o 2000,50."
            );
        }
    }

    private static int leerEquipos(Scanner entrada) {
        while (true) {
            System.out.print(
                "Cantidad de equipos que supervisa "
                + "(solo números enteros, 0 o más): "
            );

            String texto = entrada.nextLine().trim();

            if (texto.matches("[0-9]+")) {
                try {
                    return Integer.parseInt(texto);
                } catch (NumberFormatException e) {
                    System.out.println(
                        "Error: la cantidad es demasiado grande. "
                        + "Escribe una cantidad menor."
                    );
                    continue;
                }
            }

            System.out.println(
                "Error: ingresa un entero de 0 o más, "
                + "sin letras, decimales ni símbolos."
            );
        }
    }
}

class Empleado {
    protected String nombre;
    protected String id;
    protected double salarioBase;

    public Empleado(String nombre, String id, double salarioBase) {
        this.nombre = nombre;
        this.id = id;
        this.salarioBase = salarioBase;
    }

    public double calcularSalario() {
        return salarioBase;
    }

    public String getNombre() {
        return nombre;
    }
}

class Piloto extends Empleado {
    private int horasVuelo;

    public Piloto(String nombre, String id, double salarioBase,
                  int horasVuelo) {
        super(nombre, id, salarioBase);
        this.horasVuelo = horasVuelo;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + (horasVuelo * 25.0);
    }
}

class TecnicoMantenimiento extends Empleado {
    private int certificaciones;

    public TecnicoMantenimiento(String nombre, String id,
                               double salarioBase, int certificaciones) {
        super(nombre, id, salarioBase);
        this.certificaciones = certificaciones;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + (certificaciones * 150.0);
    }
}

class AgenteVentas extends Empleado {
    private double comisionVentas;

    public AgenteVentas(String nombre, String id, double salarioBase,
                        double comisionVentas) {
        super(nombre, id, salarioBase);
        this.comisionVentas = comisionVentas;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + comisionVentas;
    }
}