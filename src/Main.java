// Main.java
package mundial;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- 1) FASE DE GRUPOS ---
        System.out.println("=== Fase de Grupos ===");
        System.out.print("Ingrese nombre del Grupo: ");
        String nombreGrupo = sc.nextLine().trim();
        System.out.print("¿Cuántos equipos tiene el grupo? ");
        int numEquipos = Integer.parseInt(sc.nextLine().trim());

        // Lee nombres de equipos
        List<Equipo> equipos = new ArrayList<>();
        for (int i = 1; i <= numEquipos; i++) {
            System.out.print("Nombre del equipo " + i + ": ");
            equipos.add(new Equipo(sc.nextLine().trim()));
        }

        // Crear Grupo y generar  los partidos
        Grupo grupo = new Grupo(nombreGrupo);
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                grupo.agregarPartido(new Partido(equipos.get(i), equipos.get(j)));
            }
        }

        // Registrar resultados
        System.out.println("\n--- Registro de Resultados de Grupo " + nombreGrupo + " ---");
        for (Partido p : grupo.getPartidos()) {
            System.out.println("Partido: " + p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre());
            System.out.print("  Goles " + p.getEquipoA().getNombre() + ": ");
            int golesA = Integer.parseInt(sc.nextLine().trim());
            System.out.print("  Goles " + p.getEquipoB().getNombre() + ": ");
            int golesB = Integer.parseInt(sc.nextLine().trim());
            p.registrarResultado(golesA, golesB);
        }

        // Mostrar tabla y clasificados
        System.out.println();
        grupo.mostrarTabla();
        List<Equipo> clasificados = grupo.obtenerClasificados();
        System.out.println(">>> Clasificados de Grupo " + nombreGrupo + ": " + clasificados);
        System.out.println();

        // --- 2) FASE ELIMINATORIA ---
        System.out.println("=== Fase Eliminatoria ===");
        System.out.print("¿Cuántas llaves (rondas) desea registrar? ");
        int numLlaves = Integer.parseInt(sc.nextLine().trim());

        // Para cada llave, crear partidos, registrar resultados y mostrar ganadores
        for (int i = 1; i <= numLlaves; i++) {
            System.out.print("\nNombre de la Llave " + i + ": ");
            String nombreLlave = sc.nextLine().trim();
            Llave llave = new Llave(nombreLlave);

            System.out.print("¿Cuántos partidos hay en " + nombreLlave + "? ");
            int numPartidos = Integer.parseInt(sc.nextLine().trim());

            // Leer cada partido: permite usar clasificados u otros equipos
            for (int j = 1; j <= numPartidos; j++) {
                System.out.print("Equipo A del partido " + j + ": ");
                String nomA = sc.nextLine().trim();
                System.out.print("Equipo B del partido " + j + ": ");
                String nomB = sc.nextLine().trim();

                Equipo equipoA = buscarOCrearEquipo(nomA, equipos);
                Equipo equipoB = buscarOCrearEquipo(nomB, equipos);

                Partido p = new Partido(equipoA, equipoB);
                System.out.print("  Goles " + equipoA.getNombre() + ": ");
                int gA = Integer.parseInt(sc.nextLine().trim());
                System.out.print("  Goles " + equipoB.getNombre() + ": ");
                int gB = Integer.parseInt(sc.nextLine().trim());
                p.registrarResultado(gA, gB);

                llave.agregarPartido(p);
            }

            // Mostrar ganadores de la llave
            System.out.println();
            llave.mostrarGanadores();
        }

        sc.close();
    }

    /**
     * Busca en la lista un Equipo por nombre; si no existe, lo crea y lo añade.
     */
    private static Equipo buscarOCrearEquipo(String nombre, List<Equipo> lista) {
        for (Equipo e : lista) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        Equipo nuevo = new Equipo(nombre);
        lista.add(nuevo);
        return nuevo;
    }
}
