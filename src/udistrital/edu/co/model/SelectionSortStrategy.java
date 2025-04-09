package udistrital.edu.co.model;

public class SelectionSortStrategy implements SortStrategy {
    private int comparaciones;
    private int movimientos;
    private long tiempoEjecucion;


    private int comparaciones_matriz = 0;
    private int movimientos_matriz = 0;
    private long tiempoEjecucion_matriz = 0;
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
                    if(politicos_copia[j] != null){
                        if (politicos_copia[j].getValor_a_robar() < politicos_copia[minimo].getValor_a_robar()) {
                            minimo = j;
                        }
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
                    if(politicos_copia[j] != null){
                        if (politicos_copia[j].getEdad() < politicos_copia[minimo].getEdad()) {
                            minimo = j;
                        }
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
        comparaciones_matriz = getComparaciones();
        movimientos_matriz = getMovimientos();

        tiempoEjecucion_matriz = System.currentTimeMillis() - inicio;
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
