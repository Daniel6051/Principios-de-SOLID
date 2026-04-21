//Principios de Responsabilidad única,Principio de segregación de interfaz
public interface IOperacionesBancarias {
    void depositar(double monto);
    void retirar(double monto);
}
