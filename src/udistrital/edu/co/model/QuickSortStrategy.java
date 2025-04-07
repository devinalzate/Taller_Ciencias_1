package udistrital.edu.co.model;

public class QuickSortStrategy implements SortStrategy{
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        long inicio = System.currentTimeMillis();
        Politico[] politicos_copia = politicos.clone();
        quickSort(politicos_copia, 0, politicos_copia.length - 1, criterio);
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return politicos_copia;

    }

    private void quickSort(Politico[] arr, int low, int high,String criterio) {
        if (low < high) {
            int pi = partition(arr, low, high, criterio);
            quickSort(arr, low, pi - 1, criterio);
            quickSort(arr, pi + 1, high, criterio);
        }
    }

    private int partition(Politico[] arr, int low, int high, String criterio) {
        int i = (low - 1);
        if (criterio.equals("edad")) {
            double pivot = arr[high].getEdad();


            for (int j = low; j < high; j++) {
                comparaciones++;
                if (arr[j].getEdad() <= pivot) {
                    i++;

                    Politico temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    movimientos++;
                }
            }

            Politico temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            movimientos++;
        }
        if (criterio.equals("dinero")) {
            double pivot = arr[high].getValor_a_robar();


            for (int j = low; j < high; j++) {
                comparaciones++;
                if (arr[j].getValor_a_robar() <= pivot) {
                    i++;

                    Politico temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    movimientos++;
                }
            }


            Politico temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            movimientos++;
        }
        return i+1;
    }

    @Override
    public Politico[][] ordenarMatriz(Politico[][] matriz) {
        long inicio = System.currentTimeMillis();
        Politico[][] matriz_copia = matriz.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;

        Politico[] arreglo = SortStrategy.unirFilas(matriz_copia);

        Politico[] arreglo_ordenado = ordenarArreglo(arreglo, "edad");

        matriz_copia = SortStrategy.convertirAMatriz(arreglo_ordenado, columnas);

        for (int i = 0; i < matriz_copia.length; i++) {
            matriz_copia[i] = ordenarArreglo(matriz_copia[i], "dinero");
        }

        tiempoEjecucion = System.currentTimeMillis() - inicio;
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
}
