package mundial;

public class Equipo {
    private final String nombre;
    private int puntos;

    public Equipo (String nombre){
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPuntos(){
        return puntos;
    }

    public void sumarPuntos(int pts){
        this.puntos += pts;
    }

    @Override
    public String toString() {
        return nombre + "("+ puntos +" pts)";
    }
}
