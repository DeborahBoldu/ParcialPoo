package mundial;

import java.util.List;
import java.util.ArrayList;

public class Llave extends EtapaMundial {
    public Llave(String descripcionEtapa) {
        super(descripcionEtapa);
    }

    @Override
    public List<Equipo> getEquiposQueAvanzan() {
        List<Equipo> ganadores = new ArrayList<>();
        for (Partido p : getPartidosInternos()) { // ← corregido aquí
            Resultado r = p.getResultado();
            if (r.ganoLocal()) ganadores.add(p.getLocal());
            else if (r.ganoVisitante()) ganadores.add(p.getVisitante());
        }
        return ganadores;
    }
}