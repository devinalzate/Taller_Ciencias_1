package udistrital.edu.co.model;

public class ResultadoComparacion {
    private Politico[] arregloOrdenado;
    private long[][] estadisticas;

    public ResultadoComparacion(Politico[] arregloOrdenado, long[][] estadisticas) {
        this.arregloOrdenado = arregloOrdenado;
        this.estadisticas = estadisticas;
    }

    public Politico[] getArregloOrdenado() {
        return arregloOrdenado;
    }

    public long[][] getEstadisticas() {
        return estadisticas;
    }
}
