package udistrital.edu.co.model;

public class MergeSortStrategy implements SortStrategy {
    private int comparaciones = 0;
    private int movimientos = 0;
    private long tiempoEjecucion = 0;

    @Override
    public Politico[] ordenarArreglo(Politico[] politicos, String criterio) {
        return mergeSortPoliticos(politicos,0 ,politicos.length-1,criterio );
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
            matriz_copia[i] = mergeSortPoliticos(matriz_copia[i], 0, matriz[i].length - 1, "dinero");
        }

        tiempoEjecucion = System.currentTimeMillis() - inicio;
        return matriz_copia;
    }


    public Politico[] mergeSortPoliticos(Politico[] politicos_base, int izquierda, int derecha,String criterio) {
        long tiempoInicio = System.nanoTime();

        if (izquierda == 0 && derecha == politicos_base.length - 1) {
            tiempoInicio = System.nanoTime(); // Inicia el cronómetro solo en la primera llamada
        }

        if (izquierda >= derecha) {
            return politicos_base;
        } else {
            int medio = izquierda + (derecha - izquierda) / 2;
            mergeSortPoliticos(politicos_base, izquierda, medio,criterio);
            mergeSortPoliticos(politicos_base, medio + 1, derecha,criterio);
            politicos_base = mergeS(politicos_base, izquierda, medio, derecha,criterio);
        }

        if (izquierda == 0 && derecha == politicos_base.length - 1) {
            long tiempoFin = System.nanoTime(); // Termina el cronómetro en la última llamada
            tiempoEjecucion = tiempoFin - tiempoInicio;
        }

        return politicos_base;
    }

    private Politico[] mergeS(Politico[] politicos_base, int izquierda, int medio, int derecha, String criterio) {
        int i = izquierda;
        int j = medio + 1;

        while (i <= medio && j <= derecha) {
            // Si ambos elementos son null, avanza los dos punteros
            if (politicos_base[i] == null && politicos_base[j] == null) {
                i++;
                j++;
                continue;
            }

            // Si uno es null, mueve el no-null a la izquierda
            if (politicos_base[i] == null) {
                Politico temp = politicos_base[j];
                int k = j;
                while (k > i) {
                    politicos_base[k] = politicos_base[k - 1];
                    k--;
                }
                politicos_base[i] = temp;
                movimientos++;
                i++;
                j++;
                medio++;
                continue;
            }

            if (politicos_base[j] == null) {
                j++;
                continue; // Ignora el null a la derecha, no hay nada que comparar
            }

            comparaciones++;
            boolean condicion;

            if (criterio.equals("edad")) {
                condicion = politicos_base[i].getEdad() <= politicos_base[j].getEdad();
            } else if (criterio.equals("dinero")) {
                condicion = politicos_base[i].getValor_a_robar() <= politicos_base[j].getValor_a_robar();
            } else {
                throw new IllegalArgumentException("Criterio no válido: " + criterio);
            }

            if (condicion) {
                i++;
            } else {
                Politico temp = politicos_base[j];
                int k = j;
                while (k > i) {
                    politicos_base[k] = politicos_base[k - 1];
                    k--;
                }
                politicos_base[i] = temp;
                movimientos++;
                i++;
                j++;
                medio++;
            }
        }

        return politicos_base;
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
