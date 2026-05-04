// SRP — CuentaBancaria solo gestiona el estado de la cuenta (datos y saldo)
// Las operaciones financieras fueron movidas a OperacionesCuenta
public class CuentaBancaria {

    private final String titular;
    private final String idCuenta;
    private final TipoCuenta tipo;
    private double saldo;

    public CuentaBancaria(String titular, String idCuenta, double saldoInicial, TipoCuenta tipo) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        if (idCuenta == null || idCuenta.isBlank())
            throw new IllegalArgumentException("El ID de cuenta no puede estar vacío.");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");

        this.titular   = titular;
        this.idCuenta  = idCuenta;
        this.tipo      = tipo;
        this.saldo     = saldoInicial;
    }

    // Solo accesores — la lógica bancaria vive en OperacionesCuenta
    public String    getTitular()  { return titular; }
    public String    getIdCuenta() { return idCuenta; }
    public TipoCuenta getTipo()    { return tipo; }
    public double    getSaldo()    { return saldo; }

    // Métodos de paquete — solo OperacionesCuenta los invoca
    void setSaldo(double nuevoSaldo) { this.saldo = nuevoSaldo; }
}
