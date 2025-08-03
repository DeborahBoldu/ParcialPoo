package mundial;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Date;


public class Grupo extends EtapaMundial {

    public Grupo(String descripcionEtapa) {
        super(descripcionEtapa);
    }


    public void generarFixture(List<Equipo> equipos, Date fechaComun) {
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                Partido p = new Partido(fechaComun, equipos.get(i), equipos.get(j), new Resultado(0, 0));
                this.agregarPartido(p);
            }
        }
    }


    @Override
    public List<Equipo> getEquiposQueAvanzan() {
        Map<Equipo, Integer> puntos = new HashMap<>();

        for (Partido p : getPartidosInternos()) { //
            Equipo l = p.getLocal(), v = p.getVisitante();
            puntos.putIfAbsent(l, 0);
            puntos.putIfAbsent(v, 0);

            Resultado r = p.getResultado();
            if (r.ganoLocal()) {
                puntos.put(l, puntos.get(l) + 3);
            } else if (r.ganoVisitante()) {
                puntos.put(v, puntos.get(v) + 3);
            } else {
                puntos.put(l, puntos.get(l) + 1);
                puntos.put(v, puntos.get(v) + 1);
            }
        }

        return puntos.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(2) // los dos primeros
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}