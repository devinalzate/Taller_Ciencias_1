package udistrital.edu.co.model;

public class QuickSortStrategy implements SortStrategy{
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos) {
        long inicio = System.currentTimeMillis();
        Politico[] politicos_copia = politicos.clone();
        quickSort(politicos_copia, 0, politicos_copia.length - 1);
        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return politicos_copia;

    }

    private void quickSort(Politico[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Politico[] arr, int low, int high) {
        double pivot = arr[high].getValor_a_robar();
        int i = (low - 1);

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

        Politico temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        movimientos++;

        return i+1;
    }

    @Override
    public void ordenarMatriz(Politico[][] matriz) {

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
