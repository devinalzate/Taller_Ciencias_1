package udistrital.edu.co.model;

public class SelectionSortStrategy implements SortStrategy {
    private int comparaciones;
    private int movimientos;
    private long tiempoEjecucion;

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        movimientos = 0;
        comparaciones = 0;
        long tiempoInicial = System.nanoTime();

        Politico[] politicos_copia = politicos.clone();
        int n = politicos_copia.length;
        if (criterio.equals("dinero")) {
            for (int i = 0; i < n - 1; i++) {
                int minimo = i;
                for (int j = i + 1; j < n; j++) {
                    comparaciones++;
                    if (politicos_copia[j].getValor_a_robar() < politicos_copia[minimo].getValor_a_robar()) {
                        minimo = j;
                    }
                }

                if (minimo != i) {
                    Politico auxiliar = politicos_copia[minimo];
                    politicos_copia[minimo] = politicos_copia[i];
                    politicos_copia[i] = auxiliar;
                    movimientos++;
                }
            }
        }
        if (criterio.equals("edad")) {
            for (int i = 0; i < n - 1; i++) {
                int minimo = i;
                for (int j = i + 1; j < n; j++) {
                    comparaciones++;
                    if (politicos_copia[j].getEdad() < politicos_copia[minimo].getEdad()) {
                        minimo = j;
                    }
                }

                if (minimo != i) {
                    Politico auxiliar = politicos_copia[minimo];
                    politicos_copia[minimo] = politicos_copia[i];
                    politicos_copia[i] = auxiliar;
                    movimientos++;
                }
            }
        }

        long tiempoFinal = System.nanoTime();
        tiempoEjecucion = tiempoFinal - tiempoInicial;

        return politicos_copia;
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
