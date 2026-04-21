//Principios de Responsabilidad única,Principio de segregación de interfaz
public class ReporteCuenta implements IReportes {

    private CuentaBancaria cuenta;

    public ReporteCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void imprimirDetalles() {
        System.out.println("Titular de la cuenta: " + cuenta.getTitular());
        System.out.println("ID de la cuenta: "      + cuenta.getIdCuenta());
        System.out.println("Saldo actual: $"         + cuenta.getSaldo());
    }
}
