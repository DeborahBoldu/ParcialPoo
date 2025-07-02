package mundial;
import java.util.Date;


public class Partido {
    private Date fecha;
    private Equipo local;
    private Equipo visitante;
    private Resultado resultado;

    public Partido(Date fecha, Equipo local, Equipo visitante, Resultado resultado) {
        this.fecha = fecha;
        this.local = local;
        this.visitante = visitante;
        this.resultado = resultado;
        this.local.addPartido(this);
        this.visitante.addPartido(this);
    }

    public Date getFecha() { return fecha; }
    public Equipo getLocal() { return local; }
    public Equipo getVisitante() { return visitante; }
    public Resultado getResultado() { return resultado; }

    /** Permite actualizar el resultado tras la simulación. */
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return String.format("%tF – %s %d:%d %s",
                fecha,
                local.getNombre(),
                resultado.getGolesLocal(),
                resultado.getGolesVisitante(),
                visitante.getNombre());
    }
}
