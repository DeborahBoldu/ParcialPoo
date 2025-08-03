package mundial;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        // 1. Definir los 32 equipos
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

        // 2. Fase de Grupos (A–H)
        List<Grupo> grupos = new ArrayList<>();
        Random rnd = new Random();
        Date fechaGrupo = new Date();

        for (int gi = 0; gi < 8; gi++) {
            char grupoChar = (char) ('A' + gi);
            List<Equipo> eqs = allTeams.subList(gi * 4, gi * 4 + 4);

            Grupo g = new Grupo("Grupo " + grupoChar);
            g.generarFixture(eqs, fechaGrupo);
            for (Partido p : g.getPartidos()) {
                Resultado res = new Resultado(rnd.nextInt(5), rnd.nextInt(5));
                p.setResultado(res);
            }
            grupos.add(g);
        }

        // 3. Mostrar resultados de grupos y clasificados
        Map<String, List<Equipo>> avanzan = new LinkedHashMap<>();
        for (Grupo g : grupos) {
            System.out.println("=== " + g.getDescripcionEtapa() + " ===");
            g.getResumenResultados().forEach(System.out::println);
            List<Equipo> clas = g.getEquiposQueAvanzan();
            avanzan.put(g.getDescripcionEtapa(), clas);
            System.out.println("Clasificados: " +
                    clas.stream()
                            .map(Equipo::getNombre)
                            .collect(Collectors.joining(", ")));
            System.out.println();
        }

        // 4. Fases eliminatorias con penales en caso de empate
        List<Equipo> participantes = avanzan.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        octavosCuartosSemisFinal(participantes, rnd);
    }

    private static void octavosCuartosSemisFinal(List<Equipo> equipos, Random rnd) {
        Date fecha = new Date();

        // Octavos
        Llave octavos = new Llave("Octavos");
        prepararLlaveConPenales(octavos, equipos, fecha, rnd);
        mostrarEtapa(octavos);

        // Cuartos
        Llave cuartos = new Llave("Cuartos");
        prepararLlaveConPenales(cuartos, octavos.getEquiposQueAvanzan(), fecha, rnd);
        mostrarEtapa(cuartos);

        // Semifinales
        Llave semi = new Llave("Semifinales");
        prepararLlaveConPenales(semi, cuartos.getEquiposQueAvanzan(), fecha, rnd);
        mostrarEtapa(semi);

        // Final
        Llave finalE = new Llave("Final");
        prepararLlaveConPenales(finalE, semi.getEquiposQueAvanzan(), fecha, rnd);
        mostrarEtapa(finalE);
    }


    private static void prepararLlaveConPenales(Llave llave, List<Equipo> equipos, Date fecha, Random rnd) {
        List<Equipo> ordenados = new ArrayList<>(equipos);
        Collections.shuffle(ordenados, rnd);

        // Si la lista es impar, damos un bye al último equipo
        if (ordenados.size() % 2 != 0) {
            Equipo bye = ordenados.remove(ordenados.size() - 1);
            System.err.println("Aviso: número impar de equipos (" + equipos.size() +
                    "). " + bye.getNombre() + " avanza con bye.");
        }

        // Recorremos pares completos: i + 1 siempre < ordenados.size()
        for (int i = 0; i + 1 < ordenados.size(); i += 2) {
            Equipo local = ordenados.get(i);
            Equipo visitante = ordenados.get(i + 1);

            // 1) Resultado en tiempo reglamentario
            int gL = rnd.nextInt(5), gV = rnd.nextInt(5);
            Resultado res = new Resultado(gL, gV);

            // 2) Si empatan, definimos ganador a penales y ajustamos marcador
            if (res.empate()) {
                boolean ganaLocal = rnd.nextBoolean();
                String ganador = ganaLocal ? local.getNombre() : visitante.getNombre();
                System.out.printf("%tF – %s %d:%d %s → empate, gana en penales: %s%n",
                        fecha,
                        local.getNombre(), gL, gV, visitante.getNombre(),
                        ganador);
                // Ajustamos gol de desempate
                if (ganaLocal) res = new Resultado(gL + 1, gV);
                else          res = new Resultado(gL, gV + 1);
            } else {
                // Partido sin penales
                System.out.printf("%tF – %s %d:%d %s%n",
                        fecha,
                        local.getNombre(), gL, gV, visitante.getNombre());
            }

            Partido p = new Partido(fecha, local, visitante, res);
            llave.agregarPartido(p);

        }
    }

    private static void mostrarEtapa(EtapaMundial etapa) {
        System.out.println("\n=== " + etapa.getDescripcionEtapa() + " ===");
        etapa.getResumenResultados().forEach(System.out::println);
        System.out.println("Avanzan: " +
                etapa.getEquiposQueAvanzan().stream()
                        .map(Equipo::getNombre)
                        .collect(Collectors.joining(", ")));
    }
}