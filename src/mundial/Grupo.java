package mundial;

import java.util.*;
import java.util.stream.*;

public class Grupo {
    private final String nombre;
    private final List<Partido> partidos = new ArrayList<>();

    public Grupo(String nombre){
        this.nombre = nombre;
    }

    public void agregarPartido(Partido partido){
        partidos.add(partido);
    }

    /**
     * Devuelve los dos equipos con más puntos
     * (suponiendo que no hay empates en puntos)
     */

    public List<Equipo> obtenerClasificados(){
        return partidos.stream()
                .flatMap(p -> Stream.of(p.getEquipoA(), p.getEquipoB()))
                .distinct()
                .sorted(Comparator.comparingInt(Equipo::getPuntos).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }

    public void mostrarTabla() {
        System.out.println("=== Grupo" + nombre + "===");
        partidos.stream()
                .flatMap(p -> Stream.of(p.getEquipoA(), p.getEquipoB()))
                .distinct()
                .sorted(Comparator.comparingInt(Equipo::getPuntos).reversed())
                .forEach(e -> System.out.println(e));
    }
}
