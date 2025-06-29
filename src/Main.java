import java.util.ArrayList;
import java.util.List;

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