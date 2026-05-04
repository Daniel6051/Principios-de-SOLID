// ISP + SRP — interfaz exclusiva para consulta del historial de movimientos
import java.util.List;

public interface IHistorial {
    void registrarMovimiento(String movimiento);
    List<String> obtenerHistorial();
}
