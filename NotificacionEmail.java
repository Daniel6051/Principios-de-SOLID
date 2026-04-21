//Principios de Responsabilidad única,Principio de segregación de interfaz
public class NotificacionEmail implements INotificaciones {

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("Enviando correo a " + destinatario + ": " + mensaje);
    }
}
