import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. 32 equipos
        List<String> nombres = List.of(
                "Qatar","Ecuador","Senegal","PaisesBajos",
                "Inglaterra","Iran","EstadosUnidos","Gales",
                "Argentina","ArabiaSaudita","Mexico","Polonia",
                "Francia","Australia","Dinamarca","Tunez",
                "España","CostaRica","Alemania","Japon",
                "Belgica","Canada","Marruecos","Croacia",
                "Brasil","Serbia","Suiza","Cameroon",
                "Portugal","Ghana","Uruguay","CoreaSur"
        );
        List<Equipo> allTeams = new ArrayList<>();
        for (String nombre : nombres) {
            allTeams.add(new Equipo(nombre));
        }
        // 2. Grupos A-H de 4 equipos
        List<Grupo> grupos = new ArrayList<>();
        char grupoChar = 'A';
        for (int i = 0; i < 32; i += 4) {
            Grupo g = new Grupo("Grupo " + (char)(grupoChar++));
            for (int j = i; j < i+4; j++) g.addPartido(new Partido(new Date(), allTeams.get(j), allTeams.get(j), new Resultado(0,0))); // placeholder
            grupos.add(g);
        }