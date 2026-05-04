// SRP — responsabilidad única: registrar y exponer el historial de movimientos
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorialCuenta implements IHistorial {

    private final List<String> movimientos = new ArrayList<>();

    @Override
    public void registrarMovimiento(String movimiento) {
        movimientos.add(movimiento);
    }

    @Override
    public List<String> obtenerHistorial() {
        return Collections.unmodifiableList(movimientos);
    }
}
