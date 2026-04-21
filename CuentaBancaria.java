//Principios de Responsabilidad única,Principio de segregación de interfaz
public class CuentaBancaria implements IOperacionesBancarias {

    private String titular;
    private String idCuenta;
    private double saldo;

    public CuentaBancaria(String titular, String idCuenta, double saldo) {
        this.titular = titular;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depositado: $" + monto);
    }

    @Override
    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retirado: $" + monto);
        } else {
            System.out.println("¡Saldo insuficiente!");
        }
    }

    public String getTitular()  { return titular; }
    public String getIdCuenta() { return idCuenta; }
    public double getSaldo()    { return saldo; }
}
