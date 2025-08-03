package mundial;

    public class Resultado {
        private int golesLocal;
        private int golesVisitante;

        public Resultado(int golesLocal, int golesVisitante) {
            this.golesLocal = golesLocal;
            this.golesVisitante = golesVisitante;
        }

        public int getGolesLocal()     {
            return golesLocal; }
        public int getGolesVisitante() {
            return golesVisitante; }

        public boolean ganoLocal()     {
            return golesLocal > golesVisitante; }
        public boolean ganoVisitante() {
            return golesVisitante > golesLocal; }
        public boolean empate()        {
            return golesLocal == golesVisitante; }

        @Override
        public String toString() {
            return "Resultado{" +
                    "golesLocal=" + golesLocal +
                    ", golesVisitante=" + golesVisitante + '}';
        }

}
