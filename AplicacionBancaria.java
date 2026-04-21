//Principios de Responsabilidad única,Principio de segregación de interfaz
public class AplicacionBancaria {
    public static void main(String[] args) {

        CuentaBancaria cuenta        = new CuentaBancaria("Pepe", "12345678", 1000);
        INotificaciones notificacion = new NotificacionEmail();
        IReportes reporte            = new ReporteCuenta(cuenta);

        cuenta.depositar(500);
        cuenta.retirar(200);

        reporte.imprimirDetalles();
        notificacion.enviarNotificacion(cuenta.getTitular(), "Notificación exitosa!");
    }
}
