// DIP — AplicacionBancaria depende de abstracciones (interfaces), no de implementaciones
// SRP — solo orquesta el menú y delega cada responsabilidad a la clase correspondiente
import java.util.Scanner;

public class AplicacionBancaria {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       BIENVENIDO AL SISTEMA BANCARIO     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── Crear cuenta ──────────────────────────────────────────────────────
        CuentaBancaria cuenta = crearCuenta();

        // DIP: usamos interfaces, no implementaciones concretas
        IHistorial        historial     = new HistorialCuenta();
        INotificaciones   notificacion  = new NotificacionEmail();
        IOperacionesBancarias operaciones = new OperacionesCuenta(cuenta, historial, notificacion);
        IReportes         reporte       = new ReporteCuenta(cuenta, historial);

        // ── Menú principal ────────────────────────────────────────────────────
        boolean salir = false;
        while (!salir) {
            System.out.println("\n┌─────────────── MENÚ PRINCIPAL ───────────────┐");
            System.out.println("│  1. Depositar                                 │");
            System.out.println("│  2. Retirar                                   │");
            System.out.println("│  3. Ver resumen y historial                   │");
            System.out.println("│  4. Salir                                     │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("  Ingrese una opción: ");

            String opcion = scanner.nextLine().trim();

            if (!ValidadorCuenta.esOpcionMenuValida(opcion, 1, 4)) {
                System.out.println("  [ERROR] Opción inválida. Ingrese un número del 1 al 4.");
                continue;
            }

            switch (opcion) {
                case "1" -> {
                    double monto = pedirMonto("depósito");
                    if (monto > 0) operaciones.depositar(monto);
                }
                case "2" -> {
                    double monto = pedirMonto("retiro");
                    if (monto > 0) operaciones.retirar(monto);
                }
                case "3" -> reporte.imprimirDetalles();
                case "4" -> {
                    salir = true;
                    System.out.println("\n  ¡Gracias por usar el sistema bancario! Hasta pronto.");
                }
            }
        }

        scanner.close();
    }

    // ── Métodos auxiliares ─────────────────────────────────────────────────
    private static CuentaBancaria crearCuenta() {
        System.out.println("\n── Registrar nueva cuenta ─────────────────────");

        String titular = pedirTitular();
        String id      = pedirId();
        double saldo   = pedirSaldoInicial();
        TipoCuenta tipo = pedirTipoCuenta();

        CuentaBancaria cuenta = new CuentaBancaria(titular, id, saldo, tipo);
        System.out.printf("%n  Cuenta %s creada exitosamente para %s. Saldo inicial: $%.2f%n",
                tipo.getNombre(), titular, saldo);
        return cuenta;
    }

    private static String pedirTitular() {
        while (true) {
            System.out.print("  Nombre del titular (mín. 2 letras): ");
            String valor = scanner.nextLine().trim();
            if (ValidadorCuenta.esTitularValido(valor)) return valor;
            System.out.println("  [ERROR] Nombre inválido.");
        }
    }

    private static String pedirId() {
        while (true) {
            System.out.print("  ID de cuenta (6-10 dígitos numéricos): ");
            String valor = scanner.nextLine().trim();
            if (ValidadorCuenta.esIdValido(valor)) return valor;
            System.out.println("  [ERROR] ID inválido. Debe contener entre 6 y 10 dígitos.");
        }
    }

    private static double pedirSaldoInicial() {
        while (true) {
            System.out.print("  Saldo inicial ($0 o más): ");
            String valor = scanner.nextLine().trim();
            if (ValidadorCuenta.esMontoValido(valor) || valor.equals("0")) {
                try { return Double.parseDouble(valor); }
                catch (NumberFormatException ignored) {}
            }
            System.out.println("  [ERROR] Saldo inválido.");
        }
    }

    private static TipoCuenta pedirTipoCuenta() {
        System.out.println("  Tipos disponibles:");
        TipoCuenta[] tipos = TipoCuenta.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("    %d. %s (comisión retiro: %.0f%%)%n",
                    i + 1, tipos[i].getNombre(), tipos[i].getComisionRetiro() * 100);
        }
        while (true) {
            System.out.print("  Seleccione tipo (1-" + tipos.length + "): ");
            String opcion = scanner.nextLine().trim();
            if (ValidadorCuenta.esOpcionMenuValida(opcion, 1, tipos.length)) {
                return tipos[Integer.parseInt(opcion) - 1];
            }
            System.out.println("  [ERROR] Selección inválida.");
        }
    }

    private static double pedirMonto(String operacion) {
        System.out.print("  Ingrese el monto para el " + operacion + ": $");
        String valor = scanner.nextLine().trim();
        if (!ValidadorCuenta.esMontoValido(valor)) {
            System.out.println("  [ERROR] Monto inválido. Debe ser un número mayor a 0.");
            return -1;
        }
        return Double.parseDouble(valor);
    }
}
