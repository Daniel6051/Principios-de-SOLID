// SRP — responsabilidad única: validar entradas del usuario (extra diferenciador)
public class ValidadorCuenta {

    private ValidadorCuenta() {}   // Clase utilitaria, no se instancia

    public static boolean esTitularValido(String titular) {
        return titular != null && !titular.isBlank() && titular.length() >= 2;
    }

    public static boolean esIdValido(String id) {
        return id != null && id.matches("\\d{6,10}");
    }

    public static boolean esSaldoInicialValido(double saldo) {
        return saldo >= 0;
    }

    public static boolean esMontoValido(String entrada) {
        try {
            double valor = Double.parseDouble(entrada);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esOpcionMenuValida(String entrada, int min, int max) {
        try {
            int valor = Integer.parseInt(entrada);
            return valor >= min && valor <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
