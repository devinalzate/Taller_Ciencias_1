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
        return null;
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

    private Politico[] mergeS(Politico[] politicos_base, int izquierda, int medio, int derecha,String criterio) {
        int i = izquierda;
        int j = medio + 1;

        while (i <= medio && j <= derecha) {
            comparaciones++;
            if (criterio.equals("edad")) {
                if (politicos_base[i].getEdad() <= politicos_base[j].getEdad()) {
                    i++;
                } else {
                    Politico temp = politicos_base[j];
                    int k = j;

                    while (k > i) {
                        politicos_base[k] = politicos_base[k - 1]; // Desplaza los elementos
                        k--;
                    }

                    politicos_base[i] = temp; // Inserta el elemento en la posición correcta
                    movimientos++;

                    i++;
                    j++;
                    medio++; // Se incrementa porque hemos insertado un nuevo elemento en la izquierda
                }
            }
            if (criterio.equals("dinero")){

                if (politicos_base[i].getValor_a_robar() <= politicos_base[j].getValor_a_robar()) {
                i++;
            } else {
                Politico temp = politicos_base[j];
                int k = j;

                while (k > i) {
                    politicos_base[k] = politicos_base[k - 1]; // Desplaza los elementos
                    k--;
                }

                politicos_base[i] = temp; // Inserta el elemento en la posición correcta
                movimientos++;

                i++;
                j++;
                medio++; // Se incrementa porque hemos insertado un nuevo elemento en la izquierda
            }
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
