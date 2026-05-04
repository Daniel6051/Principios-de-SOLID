// SRP — define únicamente los tipos de cuenta disponibles y sus comisiones
public enum TipoCuenta {
    CLASSIC("Classic", 0.00),
    GOLD("Gold",       0.02),    // 2% de comisión en retiros
    PLATINUM("Platinum", 0.01); // 1% de comisión en retiros

    private final String nombre;
    private final double comisionRetiro;

    TipoCuenta(String nombre, double comisionRetiro) {
        this.nombre = nombre;
        this.comisionRetiro = comisionRetiro;
    }

    public String getNombre()           { return nombre; }
    public double getComisionRetiro()   { return comisionRetiro; }
}
