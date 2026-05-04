// SRP — responsabilidad única: enviar notificaciones por correo electrónico
// OCP — nuevos canales (SMS, WhatsApp) se agregan creando otra implementación de INotificaciones
public class NotificacionEmail implements INotificaciones {

    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("  [EMAIL → " + destinatario + "] " + mensaje);
    }
}
