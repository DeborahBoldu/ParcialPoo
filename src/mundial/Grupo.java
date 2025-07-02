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




}
