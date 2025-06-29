package mundial;

public class Partido {
    private final Equipo equipoA;
    private final Equipo equipoB;
    private Resultado resultado;

    public Partido(Equipo equipoA, Equipo equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
    }

    public Equipo getEquipoA() {
        return equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void registrarResultado(int golesA, int golesB) {
        this.resultado = new Resultado(golesA, golesB);
        asignarPuntos();
    }

    private void asignarPuntos() {
        if (resultado.esVictoriaA()) {
            equipoA.sumarPuntos(3);
        } else if (resultado.esVictoriaB()) {
            equipoB.sumarPuntos(3);
        } else {
            equipoA.sumarPuntos(1);
            equipoB.sumarPuntos(1);
        }
    }

    public Resultado getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        String marcador = (resultado != null ? " → " + resultado : "");
        return equipoA.getNombre() + " vs " + equipoB.getNombre() + marcador;
    }
}
