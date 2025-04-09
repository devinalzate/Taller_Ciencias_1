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
        for (int i = 0; i < n; i++) {
            Politico politico = new Politico();
            politico.setId(i); // Asignar un ID único
            politico.setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politico.setValor_a_robar((n - i) * 2); // Valor a robar aleatorio
            politicos[i] = politico; // Añadir el político a la lista
        }
        return politicos;
    }

    public ResultadoComparacion OrdenamientoPoliticos(Politico[] politicos) {
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

        resultados[0][2] = bubble.getComparaciones();
        resultados[0][1] = bubble.getMovimientos();
        resultados[0][0] = bubble.getTiempoEjecucion();

        resultados[1][2] = insert.getComparaciones();
        resultados[1][1] = insert.getMovimientos();
        resultados[1][0] = insert.getTiempoEjecucion();

        resultados[2][2] = merge.getComparaciones();
        resultados[2][1] = merge.getMovimientos();
        resultados[2][0] = merge.getTiempoEjecucion();

        resultados[3][2] = selection.getComparaciones();
        resultados[3][1] = selection.getMovimientos();
        resultados[3][0] = selection.getTiempoEjecucion();

        resultados[4][2] = quick.getComparaciones();
        resultados[4][1] = quick.getMovimientos();
        resultados[4][0] = quick.getTiempoEjecucion();

        // Devolver la copia ordenada (puede ser cualquiera) junto con los resultados
        return new ResultadoComparacion(copia5, resultados); // Usamos quick como referencia
    }

    /*

    como usar
    ResultadoComparacion resultado = CreateArrayPoliticos(arregloOriginal);
    Politico[] arregloOrdenado = resultado.getArregloOrdenado();
    long[][] estadisticas = resultado.getEstadisticas();*/


    public long[][] realizarComparacionesCrecientes(int tamañoInicial, int tasaCrecimiento) {
        int tamaño = tamañoInicial;


        long[][] acumulados = new long[5][3];
        int repeticiones = 0;
        while (tamaño <= 100) {
             // Para guardar la suma de comparaciones, movimientos, tiempo
            repeticiones += 1; // Para mayor precisión en el promedio
            Politico[] arreglo = CreateArrayPoliticosOrdenInverso(tamaño);
            ResultadoComparacion resultado = OrdenamientoPoliticos(arreglo);
            long[][] datos = resultado.getEstadisticas();

            // Acumular los resultados para promediarlos
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    acumulados[j][k] += datos[j][k];
                }
            }


            tamaño *= tasaCrecimiento; // Aumentar el tamaño con la tasa de crecimiento
        }
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 3; k++) {
                acumulados[j][k] = acumulados[j][k]/repeticiones;
            }
        }
        return acumulados;
    }
    public static void imprimirMatriz(long[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
