package udistrital.edu.co.model;

public class BubbleSortStrategy implements SortStrategy {
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de burbuja (Bubble Sort).
     *
     * @param politicos Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También guarda el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos) {
        long inicio = System.currentTimeMillis(); // Tiempo inicial

        // Crear copia de la lista base
        Politico[] politicos_copia = politicos.clone();

        // Algoritmo de ordenamiento de burbuja
        for (int i = 0; i < politicos_copia.length - 1; i++) {
            for (int j = 0; j < politicos_copia.length - i - 1; j++) {
                comparaciones++;
                if (politicos_copia[j].getValor_a_robar() > politicos_copia[j+1].getValor_a_robar()) {
                    Politico politico = politicos_copia[j];
                    politicos_copia[j] =  politicos_copia[j+1];
                    politicos_copia[j+1]= politico;
                    movimientos++;
                }
            }
        }

        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return politicos_copia;

    }

    @Override
    public void ordenarMatriz(Politico[][] matriz) {
        long inicio = System.currentTimeMillis();
        Politico[][] matriz_copia = matriz.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;


        // 1. Ordenar filas por valor_a_robar (burbuja)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                for (int k = 0; k < columnas - j - 1; k++) {
                    if (matriz_copia[i][k] != null && matriz_copia[i][k + 1] != null) {
                        if (matriz_copia[i][k].getValor_a_robar() > matriz_copia[i][k + 1].getValor_a_robar()) {
                            // Intercambiar
                            Politico temp = matriz_copia[i][k];
                            matriz_copia[i][k] = matriz_copia[i][k + 1];
                            matriz_copia[i][k + 1] = temp;
                        }
                    }
                }
            }
        }

        // 2. Ordenar columnas por edad, pero manteniendo orden de dinero si hay empates
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas - 1; j++) {
                for (int k = 0; k < filas - j - 1; k++) {
                    if (matriz_copia[k][i] != null && matriz_copia[k + 1][i] != null) {
                        if (matriz_copia[k][i].getEdad() > matriz_copia[k + 1][i].getEdad()) {
                            Politico temp = matriz_copia[k][i];
                            matriz_copia[k][i] = matriz_copia[k + 1][i];
                            matriz_copia[k + 1][i] = temp;
                        }
                    }
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
