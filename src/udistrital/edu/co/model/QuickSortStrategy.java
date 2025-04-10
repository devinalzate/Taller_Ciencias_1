package udistrital.edu.co.model;

public class QuickSortStrategy implements SortStrategy{
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;


    private int comparaciones_matriz = 0;
    private int movimientos_matriz = 0;
    private long tiempoEjecucion_matriz = 0;

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        long inicio = System.nanoTime(); // Tiempo en nanosegundos
        if (politicos == null) return null;

        Politico[] politicos_copia = politicos.clone();
        quickSort(politicos_copia, 0, politicos_copia.length - 1, criterio);
        tiempoEjecucion = (System.nanoTime() - inicio) / 1000; // Microsegundos
        return politicos_copia;
    }

    private void quickSort(Politico[] arr, int low, int high, String criterio) {
        if (arr == null) return;
        if (low < high) {
            int pi = partition(arr, low, high, criterio);
            quickSort(arr, low, pi - 1, criterio);
            quickSort(arr, pi + 1, high, criterio);
        }
    }

    private int partition(Politico[] arr, int low, int high, String criterio) {
        int i = (low - 1);

        if (criterio.equals("edad")) {
            double pivot = arr[high] != null ? arr[high].getEdad() : Double.MAX_VALUE;

            for (int j = low; j < high; j++) {
                comparaciones++;
                double valor = arr[j] != null ? arr[j].getEdad() : Double.MAX_VALUE;
                if (valor <= pivot) {
                    i++;
                    if (i != j) {
                        Politico temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        movimientos++;
                    }
                }
            }

            if ((i + 1) != high) {
                Politico temp = arr[i + 1];
                arr[i + 1] = arr[high];
                arr[high] = temp;
                movimientos++;
            }

        } else if (criterio.equals("dinero")) {
            double pivot = arr[high] != null ? arr[high].getValor_a_robar() : Double.MAX_VALUE;

            for (int j = low; j < high; j++) {
                comparaciones++;
                double valor = arr[j] != null ? arr[j].getValor_a_robar() : Double.MAX_VALUE;
                if (valor <= pivot) {
                    i++;
                    if (i != j) {
                        Politico temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        movimientos++;
                    }
                }
            }

            if ((i + 1) != high) {
                Politico temp = arr[i + 1];
                arr[i + 1] = arr[high];
                arr[high] = temp;
                movimientos++;
            }
        }

        return i + 1;
    }

    @Override
    public Politico[][] ordenarMatriz(Politico[][] matriz) {
        long inicio = System.nanoTime(); // Tiempo en nanosegundos
        if (matriz == null) return null;

        Politico[][] matriz_copia = matriz.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0] != null ? matriz_copia[0].length : 0;

        Politico[] arreglo = SortStrategy.unirFilas(matriz_copia);
        Politico[] arreglo_ordenado = ordenarArreglo(arreglo, "edad");

        matriz_copia = SortStrategy.convertirAMatriz(arreglo_ordenado, columnas);

        for (int i = 0; i < matriz_copia.length; i++) {
            if (matriz_copia[i] != null)
                matriz_copia[i] = ordenarArreglo(matriz_copia[i], "dinero");
        }

        comparaciones_matriz = getComparaciones();
        movimientos_matriz = getMovimientos();
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
