// SRP — responsabilidad única: ejecutar operaciones financieras sobre una cuenta
// OCP — puede extenderse con nuevas validaciones sin modificar esta clase
// DIP — recibe la cuenta como abstracción del estado, no la construye
public class OperacionesCuenta implements IOperacionesBancarias {

    private static final double DEPOSITO_MINIMO  = 1.0;
    private static final double DEPOSITO_MAXIMO  = 100_000.0;
    private static final double RETIRO_MAXIMO    = 50_000.0;

    private final CuentaBancaria cuenta;
    private final IHistorial     historial;
    private final INotificaciones notificaciones;

    public OperacionesCuenta(CuentaBancaria cuenta,
                             IHistorial historial,
                             INotificaciones notificaciones) {
        this.cuenta         = cuenta;
        this.historial      = historial;
        this.notificaciones = notificaciones;
    }

    @Override
    public void depositar(double monto) {
        if (monto < DEPOSITO_MINIMO) {
            System.out.println("  [ERROR] El depósito mínimo es $" + DEPOSITO_MINIMO + ".");
            return;
        }
        if (monto > DEPOSITO_MAXIMO) {
            System.out.println("  [ERROR] El depósito no puede superar $" + DEPOSITO_MAXIMO + ".");
            return;
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        String msg = String.format("Depósito de $%.2f realizado. Saldo actual: $%.2f", monto, cuenta.getSaldo());
        System.out.println("  " + msg);
        historial.registrarMovimiento("[DEPÓSITO]  +" + String.format("%.2f", monto)
                + "  →  Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
        notificaciones.enviarNotificacion(cuenta.getTitular(), msg);
    }

    @Override
    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("  [ERROR] El monto a retirar debe ser mayor a $0.");
            return;
        }
        if (monto > RETIRO_MAXIMO) {
            System.out.println("  [ERROR] El retiro no puede superar $" + RETIRO_MAXIMO + " por operación.");
            return;
        }

        // Comisión según tipo de cuenta
        double comision = monto * cuenta.getTipo().getComisionRetiro();
        double totalDescontar = monto + comision;

        if (cuenta.getSaldo() < totalDescontar) {
            System.out.printf("  [ERROR] Saldo insuficiente. Necesita $%.2f (retiro $%.2f + comisión $%.2f).%n",
                    totalDescontar, monto, comision);
            return;
        }

        cuenta.setSaldo(cuenta.getSaldo() - totalDescontar);
        String comisionMsg = comision > 0
                ? String.format(" (comisión %s: $%.2f)", cuenta.getTipo().getNombre(), comision)
                : "";
        String msg = String.format("Retiro de $%.2f realizado%s. Saldo actual: $%.2f",
                monto, comisionMsg, cuenta.getSaldo());
        System.out.println("  " + msg);
        historial.registrarMovimiento("[RETIRO]    -" + String.format("%.2f", totalDescontar)
                + "  →  Saldo: $" + String.format("%.2f", cuenta.getSaldo())
                + (comision > 0 ? "  (comisión: $" + String.format("%.2f", comision) + ")" : ""));
        notificaciones.enviarNotificacion(cuenta.getTitular(), msg);
    }
}
