package udistrital.edu.co.controller;

import udistrital.edu.co.model.*;

public class controladorJP {
    private InsertionSortStrategy insert;
    private BubbleSortStrategy bubble;
    private MergeSortStrategy merge;
    private SelectionSortStrategy selection;
    private QuickSortStrategy quick;
    private Ordenamiento valores;

    public controladorJP(){
        valores = new Ordenamiento();
        insert = new InsertionSortStrategy();
        bubble = new BubbleSortStrategy();
        merge = new MergeSortStrategy();
        selection = new SelectionSortStrategy();
        quick = new QuickSortStrategy();
    }

    public Politico[] CreateArrayPoliticos(int n) {
        Politico[] politicos = new Politico[n];
        for (int i = 0; i < n; i++) {
            politicos[i] = new Politico();
            politicos[i].setId(i); // Asignar un ID único
            politicos[i].setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politicos[i].setValor_a_robar((int)((Math.random() * 1000000) + 1)); // Valor a robar aleatorio
        }
        return politicos;
    }

    public Politico[] CreateArrayPoliticosOrdenInverso(int n) {
        Politico[] politicos = new Politico[n];
        for (int i = 1; i < n; i++) {
            Politico politico = new Politico();
            politico.setId(i); // Asignar un ID único
            politico.setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politico.setValor_a_robar((n - i) * 2); // Valor a robar aleatorio
            politicos[i] = politico; // Añadir el político a la lista
        }
        return politicos;
    }

    public ResultadoComparacion CreateArrayPoliticos(Politico[] politicos) {
        Politico[] copia1 = politicos.clone();
        Politico[] copia2 = politicos.clone();
        Politico[] copia3 = politicos.clone();
        Politico[] copia4 = politicos.clone();
        Politico[] copia5 = politicos.clone();

        insert.ordenarArreglo(copia1, "dinero");
        bubble.ordenarArreglo(copia2, "dinero");
        merge.ordenarArreglo(copia3, "dinero");
        selection.ordenarArreglo(copia4, "dinero");
        quick.ordenarArreglo(copia5, "dinero");

        long[][] resultados = new long[5][3];

        resultados[0][0] = insert.getComparaciones();
        resultados[0][1] = insert.getMovimientos();
        resultados[0][2] = insert.getTiempoEjecucion();

        resultados[1][0] = bubble.getComparaciones();
        resultados[1][1] = bubble.getMovimientos();
        resultados[1][2] = bubble.getTiempoEjecucion();

        resultados[2][0] = merge.getComparaciones();
        resultados[2][1] = merge.getMovimientos();
        resultados[2][2] = merge.getTiempoEjecucion();

        resultados[3][0] = selection.getComparaciones();
        resultados[3][1] = selection.getMovimientos();
        resultados[3][2] = selection.getTiempoEjecucion();

        resultados[4][0] = quick.getComparaciones();
        resultados[4][1] = quick.getMovimientos();
        resultados[4][2] = quick.getTiempoEjecucion();

        // Devolver la copia ordenada (puede ser cualquiera) junto con los resultados
        return new ResultadoComparacion(copia5, resultados); // Usamos quick como referencia
    }

    /*

    como usar
    ResultadoComparacion resultado = CreateArrayPoliticos(arregloOriginal);
    Politico[] arregloOrdenado = resultado.getArregloOrdenado();
    long[][] estadisticas = resultado.getEstadisticas();*/

    public void realizarComparacionesCrecientes(int tamañoInicial, int tasaCrecimiento) {
        int tamaño = tamañoInicial;

        System.out.printf("%-10s %-20s %-20s %-20s\n", "Tamaño", "Algoritmo", "Comparaciones", "Movimientos");

        while (tamaño <= 20) {
            long[][] acumulados = new long[5][3]; // Para guardar la suma de comparaciones, movimientos, tiempo
            int repeticiones = 5; // Para mayor precisión en el promedio

            for (int i = 0; i < repeticiones; i++) {
                Politico[] arreglo = CreateArrayPoliticos(tamaño);
                ResultadoComparacion resultado = CreateArrayPoliticos(arreglo);
                long[][] datos = resultado.getEstadisticas();

                // Acumular los resultados para promediarlos
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 3; k++) {
                        acumulados[j][k] += datos[j][k];
                    }
                }
            }

            // Mostrar promedios
            String[] algoritmos = {"Insert", "Bubble", "Merge", "Selection", "Quick"};
            for (int i = 0; i < 5; i++) {
                long promedioComparaciones = acumulados[i][0] / repeticiones;
                long promedioMovimientos = acumulados[i][1] / repeticiones;
                // Si también quieres mostrar tiempo, puedes usar: acumulados[i][2] / repeticiones

                System.out.printf("%-10d %-20s %-20d %-20d\n", tamaño, algoritmos[i], promedioComparaciones, promedioMovimientos);
            }

            tamaño *= tasaCrecimiento; // Aumentar el tamaño con la tasa de crecimiento
        }
    }

}
