package udistrital.edu.co.model;

public class InsertionSortStrategy implements SortStrategy{
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    private int comparaciones_matriz = 0;
    private int movimientos_matriz = 0;
    private long tiempoEjecucion_matriz = 0;
    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de ordenamiento por inserción (Insertion Sort).
     *
     * @param politicos Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También guarda el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */


    @Override

    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        long inicio = System.nanoTime(); // Tiempo inicial en nanosegundos

        Politico[] copia = politicos.clone();

        if (criterio.equals("dinero")) {
            for (int i = 1; i < copia.length; i++) {
                Politico actual = copia[i];
                int j = i - 1;
                if (actual == null) {
                    break;
                }

                if (j >= 0 && (copia[j].getValor_a_robar() <= actual.getValor_a_robar())) {
                    comparaciones++;
                }

                while (j >= 0 && (copia[j].getValor_a_robar() > actual.getValor_a_robar())) {
                    copia[j + 1] = copia[j];
                    j--;
                    movimientos++;
                    comparaciones++;
                }

                copia[j + 1] = actual;
            }
        }

        if (criterio.equals("edad")) {
            for (int i = 1; i < copia.length; i++) {
                Politico actual = copia[i];
                int j = i - 1;
                movimientos++;

                if (actual == null) {
                    break;
                }

                if (j >= 0 && (copia[j].getEdad() <= actual.getEdad())) {
                    comparaciones++;
                }

                while (j >= 0 && (copia[j].getEdad() > actual.getEdad())) {
                    copia[j + 1] = copia[j];
                    j--;
                    movimientos++;
                    comparaciones++;
                }

                copia[j + 1] = actual;
            }
        }

        tiempoEjecucion = (System.nanoTime() - inicio) / 1000; // Microsegundos
        return copia;
    }

    @Override
    public Politico[][] ordenarMatriz(Politico[][] matriz) {
        long inicio = System.nanoTime(); // Tiempo inicial en nanosegundos

        Politico[][] matriz_copia = matriz.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;

        Politico[] arreglo = SortStrategy.unirFilas(matriz_copia);

        Politico[] arreglo_ordenado = ordenarArreglo(arreglo, "edad");

        comparaciones_matriz = getComparaciones();
        movimientos_matriz = getMovimientos();

        matriz_copia = SortStrategy.convertirAMatriz(arreglo_ordenado, columnas);

        int maxDimension = Math.max(filas, columnas);

        for (int i = 0; i < maxDimension; i++) {
            if (i < filas) {
                for (int j = 1; j < columnas; j++) {
                    Politico politico = matriz_copia[i][j];
                    int k = j - 1;

                    if (politico == null) {
                        break;
                    }

                    if (j >= 0 && (matriz_copia[i][k].getValor_a_robar() <= politico.getValor_a_robar())) {
                        comparaciones_matriz++;
                    }

                    while (k >= 0 && matriz_copia[i][k].getValor_a_robar() > politico.getValor_a_robar()) {
                        matriz_copia[i][k + 1] = matriz_copia[i][k];
                        k--;
                        movimientos_matriz++;
                        comparaciones_matriz++;
                    }

                    matriz_copia[i][k + 1] = politico;
                }
            }
        }

        tiempoEjecucion_matriz = (System.nanoTime() - inicio) / 1000; // Microsegundos
        return matriz_copia;
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

    public int getComparaciones_matriz() {
        return comparaciones_matriz;
    }

    public int getMovimientos_matriz() {
        return movimientos_matriz;
    }

    public long getTiempoEjecucion_matriz() {
        return tiempoEjecucion_matriz;
    }
}
