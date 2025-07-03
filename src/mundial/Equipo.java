package mundial;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Modelo de un equipo participante.
 */
public class Equipo {
    private String nombre;
    private List<Partido> partidosJugados;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.partidosJugados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public void addPartido(Partido p) {
        partidosJugados.add(p);
    }

    public List<Partido> getPartidosJugados() {
        return Collections.unmodifiableList(partidosJugados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipo)) return false;
        Equipo eq = (Equipo) o;
        return Objects.equals(nombre, eq.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre='" + nombre + '\'' + '}';
    }
}