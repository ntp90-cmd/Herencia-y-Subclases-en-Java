public class Supervisor extends Empleado {
    private final int equiposSupervisados;

    public Supervisor(String nombre, String id, double salarioBase,
                      int equiposSupervisados) {
        super(nombre, id, salarioBase);
        this.equiposSupervisados = equiposSupervisados;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + (equiposSupervisados * 500.0);
    }
}