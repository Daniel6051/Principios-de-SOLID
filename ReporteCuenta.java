// SRP — responsabilidad única: formatear e imprimir el estado de la cuenta
public class ReporteCuenta implements IReportes {

    private final CuentaBancaria cuenta;
    private final IHistorial     historial;

    public ReporteCuenta(CuentaBancaria cuenta, IHistorial historial) {
        this.cuenta    = cuenta;
        this.historial = historial;
    }

    @Override
    public void imprimirDetalles() {
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║          RESUMEN DE CUENTA           ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.printf ("  ║  Titular : %-26s║%n", cuenta.getTitular());
        System.out.printf ("  ║  ID      : %-26s║%n", cuenta.getIdCuenta());
        System.out.printf ("  ║  Tipo    : %-26s║%n", cuenta.getTipo().getNombre());
        System.out.printf ("  ║  Saldo   : $%-25.2f║%n", cuenta.getSaldo());
        System.out.printf ("  ║  Comisión: %.0f%% por retiro%-17s║%n",
                cuenta.getTipo().getComisionRetiro() * 100, "");
        System.out.println("  ╚══════════════════════════════════════╝");

        // Historial de movimientos (extra diferenciador)
        var movimientos = historial.obtenerHistorial();
        if (movimientos.isEmpty()) {
            System.out.println("  Sin movimientos registrados.");
        } else {
            System.out.println("  --- Historial de movimientos ---");
            for (int i = 0; i < movimientos.size(); i++) {
                System.out.printf("  %2d. %s%n", i + 1, movimientos.get(i));
            }
        }
    }
}
