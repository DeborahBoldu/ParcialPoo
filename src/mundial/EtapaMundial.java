package mundial;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class EtapaMundial {
    private String descripcionEtapa;
    private List<Partido> partidos;

    public EtapaMundial(String descripcionEtapa) {
        this.descripcionEtapa = descripcionEtapa;
        this.partidos = new ArrayList<>();
    }

    public String getDescripcionEtapa() {
        return descripcionEtapa;
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos);
    }

    /**
     * Método protegido para permitir que subclases agreguen partidos.
     */
    protected void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    /**
     * Método protegido para permitir que subclases recorran los partidos.
     */
    protected List<Partido> getPartidosInternos() {
        return partidos;
    }

    /**
     * Devuelve resumen de resultados de los partidos jugados.
     */
    public List<String> getResumenResultados() {
        return partidos.stream()
                .map(p -> String.format("%tF – %s %d:%d %s",
                        p.getFecha(),
                        p.getLocal().getNombre(),
                        p.getResultado().getGolesLocal(),
                        p.getResultado().getGolesVisitante(),
                        p.getVisitante().getNombre()))
                .toList();
    }

    public abstract List<Equipo> getEquiposQueAvanzan();

    @Override
    public String toString() {
        return "EtapaMundial{" +
                "descripcionEtapa='" + descripcionEtapa + '\'' +
                ", partidos=" + partidos.size() + '}';
    }
}