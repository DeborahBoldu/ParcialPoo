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
        // Distribuir equipos en grupos (sin placeholders)
        for (int gi = 0; gi < grupos.size(); gi++) {
            Grupo g = grupos.get(gi);
            g.partidos.clear();
            List<Equipo> eqs = allTeams.subList(gi*4, gi*4+4);
            // Partidos todos contra todos en el grupo
            for (int a = 0; a < 4; a++)
                for (int b = a+1; b < 4; b++) {
                    g.addPartido(simularPartido(eqs.get(a), eqs.get(b)));
                }
        }

        // 3.Resultados de grupos y determinar clasificados
        Map<String,List<Equipo>> avanzanGrupos = new LinkedHashMap<>();
        for (Grupo g : grupos) {
            System.out.println(" " + g.getDescripcionEtapa() + " ---");
            g.getResumenResultados().forEach(System.out::println);
            List<Equipo> clasificados = g.getEquiposQueAvanzan();
            avanzanGrupos.put(g.getDescripcionEtapa(), clasificados);
            System.out.println("Clasificados: " + clasificados);
        }

        // 4. Fase de eliminación directa
        List<Partido> octavos = crearLlave("Octavos", avanzanGrupos);
        List<Partido> cuartos = crearLlave("Cuartos", octavos);
        List<Partido> semifinales = crearLlave("Semifinales", cuartos);
        List<Partido> finalPartido = crearLlave("Final", semifinales);

