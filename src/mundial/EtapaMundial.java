// src/mundial/EtapaMundial.java
package mundial;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public abstract class EtapaMundial {
    protected String descripcionEtapa;
    protected List<Partido> partidos;

    public EtapaMundial(String descripcionEtapa) {
        this.descripcionEtapa = descripcionEtapa;
        this.partidos = new ArrayList<>();
    }

    public String getDescripcionEtapa() {
        return descripcionEtapa;
    }

    public void addPartido(Partido partido) {
        partidos.add(partido);
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos);
    }

    /**
     * Resumen de resultados de partidos
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
