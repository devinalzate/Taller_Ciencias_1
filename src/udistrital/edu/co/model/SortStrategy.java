package udistrital.edu.co.model;

public interface SortStrategy {
    Politico[] ordenarArreglo(Politico[] politicos);
    void ordenarMatriz(Politico[][] matriz);
    int getComparaciones();
    int getMovimientos();
    long getTiempoEjecucion();
}
