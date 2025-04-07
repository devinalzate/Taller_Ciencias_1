package udistrital.edu.co.model;

public interface SortStrategy {
    Politico[] ordenarArreglo(Politico[] politicos, String criterio);
    void ordenarMatriz(Politico[][] matriz);
    int getComparaciones();
    int getMovimientos();
    long getTiempoEjecucion();

    static Politico[] unirFilas(Politico[][] matriz) {
        // Primero, calculamos el total de elementos
        int totalElementos = 0;
        for (Politico[] fila : matriz) {
            totalElementos += fila.length;
        }

        // Creamos el arreglo resultante
        Politico[] resultado = new Politico[totalElementos];

        // Llenamos el arreglo con los elementos de la matriz
        int index = 0;
        for (Politico[] fila : matriz) {
            for (Politico elemento : fila) {
                resultado[index++] = elemento;
            }
        }

        return resultado;
    }

     static Politico[][] convertirAMatriz(Politico[] arreglo, int columnas) {
        if (columnas <= 0) {
            throw new IllegalArgumentException("El número de columnas debe ser mayor que cero.");
        }

        int filas = (int) Math.ceil((double) arreglo.length / columnas);
        Politico[][] matriz = new Politico[filas][columnas];

        for (int i = 0; i < arreglo.length; i++) {
            int fila = i / columnas;
            int col = i % columnas;
            matriz[fila][col] = arreglo[i];
        }

        return matriz;
    }
}
