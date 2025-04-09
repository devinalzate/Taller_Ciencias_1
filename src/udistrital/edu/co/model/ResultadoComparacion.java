package udistrital.edu.co.model;

public class ResultadoComparacion {
    private Politico[] arregloOrdenado;
    private long[][] estadisticas;
    private  Politico[][] matrizOrdenada;
    private  long[][] estadisticasM;

    public ResultadoComparacion(Politico[] arregloOrdenado, long[][] estadisticas, Politico[][] matrizOrdenada, long[][] estadisticasM) {
        this.arregloOrdenado = arregloOrdenado;
        this.estadisticas = estadisticas;
        this.estadisticasM=estadisticasM;
        this.matrizOrdenada=matrizOrdenada;
    }

    public Politico[] getArregloOrdenado() {
        return arregloOrdenado;
    }

    public long[][] getEstadisticas() {
        return estadisticas;
    }

    public Politico[][] getMatrizOrdenada() {
        return matrizOrdenada;
    }

    public long[][] getEstadisticasM() {
        return estadisticasM;
    }
}
