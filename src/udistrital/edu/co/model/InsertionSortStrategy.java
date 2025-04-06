package udistrital.edu.co.model;

public class InsertionSortStrategy implements SortStrategy{
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de ordenamiento por inserción (Insertion Sort).
     *
     * @param politicos Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También guarda el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */
    @Override
    public Politico[] ordenarArreglo(Politico[] politicos) {
        long inicio = System.currentTimeMillis();

        // Crear copia de la lista base
        Politico[] copia = politicos.clone();

        // Algoritmo de ordenamiento por inserción
        for (int i = 1; i < copia.length; i++) {
            Politico actual = copia[i];
            int j = i - 1;
            movimientos++;

            // Desplazamiento hacia la derecha de los elementos mayores
            while (j >= 0 && (copia[j].getValor_a_robar() > actual.getValor_a_robar())) {
                copia[j + 1] = copia[j];
                j--;
                comparaciones++;
            }
            copia[j + 1] = actual;
        }
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return copia;
    }

    @Override
    public void ordenarMatriz(Politico[][] matriz) {

        long inicio = System.currentTimeMillis();
        Politico[][] matriz_copia = matriz.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;
        int maxDimension = Math.max(filas, columnas);

        for (int i = 0; i < maxDimension; i++) {
            // Ordenar fila i (si existe)
            if (i < filas) {
                for (int j = 1; j < columnas; j++) {
                    Politico politico = matriz_copia[i][j];
                    int k = j - 1;

                    if (politico == null) {
                        break;
                    }
                    while (k >= 0 && matriz_copia[i][k].getValor_a_robar() > politico.getValor_a_robar()) {
                        matriz_copia[i][k + 1] = matriz_copia[i][k];
                        k--;
                    }
                    matriz_copia[i][k + 1] = politico;
                }
            }

            // Ordenar columna i (si existe)
            if (i < columnas) {
                for (int j = 1; j < filas; j++) {
                    Politico politico = matriz_copia[j][i];
                    int k = j - 1;

                    if (politico == null) {
                        break;
                    }
                    while (k >= 0 && matriz_copia[k][i].getEdad() > politico.getEdad()) {
                        matriz_copia[k + 1][i] = matriz_copia[k][i];
                        k--;
                    }
                    matriz_copia[k + 1][i] = politico;
                }
            }
        }

        tiempoEjecucion = System.currentTimeMillis() - inicio;

    }

    @Override
    public int getComparaciones() {
        return this.comparaciones;
    }

    @Override
    public int getMovimientos() {
        return this.movimientos;
    }

    @Override
    public long getTiempoEjecucion() {
        return this.tiempoEjecucion;
    }
}
