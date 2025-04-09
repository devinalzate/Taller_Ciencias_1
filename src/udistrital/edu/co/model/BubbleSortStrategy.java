package udistrital.edu.co.model;

public class BubbleSortStrategy implements SortStrategy {
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    private int comparaciones_matriz = 0;
    private int movimientos_matriz = 0;
    private long tiempoEjecucion_matriz = 0;

    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de burbuja (Bubble Sort).
     *
     * @param politicos Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También guarda el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        long inicio = System.currentTimeMillis(); // Tiempo inicial

        // Crear copia de la lista base
        Politico[] politicos_copia = politicos.clone();

        // Algoritmo de ordenamiento de burbuja
        if (criterio.equals("dinero")){
            for (int i = 0; i < politicos_copia.length - 1; i++) {
                for (int j = 0; j < politicos_copia.length - i - 1; j++) {
                    comparaciones++;
                    if (politicos_copia[j].getValor_a_robar() > politicos_copia[j + 1].getValor_a_robar()) {
                        Politico politico = politicos_copia[j];
                        politicos_copia[j] = politicos_copia[j + 1];
                        politicos_copia[j + 1] = politico;
                        movimientos++;
                    }
                }
            }
        }

        if (criterio.equals("edad")){
            for (int i = 0; i < politicos_copia.length - 1; i++) {
                for (int j = 0; j < politicos_copia.length - i - 1; j++) {
                    comparaciones++;
                    if (politicos_copia[j] != null && politicos_copia[j +1] != null){
                        if (politicos_copia[j].getEdad() > politicos_copia[j + 1].getEdad()) {
                            Politico politico = politicos_copia[j];
                            politicos_copia[j] = politicos_copia[j + 1];
                            politicos_copia[j + 1] = politico;
                            movimientos++;
                        }
                    }
                }
            }
        }

        tiempoEjecucion_matriz = System.currentTimeMillis() - inicio;
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
        comparaciones_matriz = getComparaciones();
        movimientos_matriz = getMovimientos();


        matriz_copia = SortStrategy.convertirAMatriz(arreglo_ordenado, columnas);


        // 1. Ordenar filas por valor_a_robar (burbuja)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                for (int k = 0; k < columnas - j - 1; k++) {
                    comparaciones_matriz += 1;
                    if (matriz_copia[i][k] != null && matriz_copia[i][k + 1] != null) {
                        if (matriz_copia[i][k].getValor_a_robar() > matriz_copia[i][k + 1].getValor_a_robar()) {
                            // Intercambiar
                            Politico temp = matriz_copia[i][k];
                            matriz_copia[i][k] = matriz_copia[i][k + 1];
                            matriz_copia[i][k + 1] = temp;
                            movimientos_matriz += 1;
                        }
                    }
                }
            }
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
