package udistrital.edu.co.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import udistrital.edu.co.model.*;

import java.io.IOException;

public class controladorJP {
    private InsertionSortStrategy insert;
    private BubbleSortStrategy bubble;
    private MergeSortStrategy merge;
    private SelectionSortStrategy selection;
    private QuickSortStrategy quick;

    private InsertionSortStrategy insert_matriz;
    private BubbleSortStrategy bubble_matriz;
    private MergeSortStrategy merge_matriz;
    private SelectionSortStrategy selection_matriz;
    private QuickSortStrategy quick_matriz;

    public controladorJP(){
        insert = new InsertionSortStrategy();
        bubble = new BubbleSortStrategy();
        merge = new MergeSortStrategy();
        selection = new SelectionSortStrategy();
        quick = new QuickSortStrategy();

        insert_matriz = new InsertionSortStrategy();
        bubble_matriz = new BubbleSortStrategy();
        merge_matriz =  new MergeSortStrategy();
        selection_matriz = new SelectionSortStrategy();
        quick_matriz = new QuickSortStrategy();


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

    public Politico[] CreateArrayPoliticosOrdenado(int n) {
        Politico[] politicos = new Politico[n];
        for (int i = 0; i < n; i++) {
            Politico politico = new Politico();
            politico.setId(i); // Asignar un ID único
            politico.setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politico.setValor_a_robar((n) * 2); // Valor a robar aleatorio
            politicos[i] = politico; // Añadir el político a la lista
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

    public Politico[][] CreateMatriz(Politico[] politicos) {
        // Validación inicial
        if (politicos == null || politicos.length == 0) {
            return new Politico[0][0];
        }

        int size = politicos.length;
        Politico[][] matrizPoliticos = null;
        int minFilas = 3; // Mínimo de filas requerido

        // Buscamos el par de factores más equilibrado que cumpla con el mínimo de filas
        for (int i = Math.max(minFilas, (int) Math.ceil(Math.sqrt(size))); i >= minFilas; i--) {
            if (size % i == 0) {
                int rows = i;
                int cols = size / i;
                matrizPoliticos = new Politico[rows][cols];
                break;
            }
        }

        // Si no encontramos divisores que cumplan con el mínimo de filas
        if (matrizPoliticos == null) {
            // Calculamos el número de columnas necesario para tener al menos 3 filas
            int rows = minFilas;
            int cols = (int) Math.ceil((double) size / rows);
            matrizPoliticos = new Politico[rows][cols];
        }

        // Llenar la matriz con los políticos y espacios vacíos como null
        int index = 0;
        for (int i = 0; i < matrizPoliticos.length; i++) {
            for (int j = 0; j < matrizPoliticos[i].length; j++) {
                if (index < politicos.length) {
                    matrizPoliticos[i][j] = politicos[index++];
                } else {
                    matrizPoliticos[i][j] = null; // Espacios vacíos explícitos
                }
            }
        }
        return matrizPoliticos;
    }

    public ResultadoComparacion OrdenamientoPoliticos(Politico[] politicos, Politico[][] matriz) {
        Politico[] copia1 = politicos.clone();
        Politico[] copia2 = politicos.clone();
        Politico[] copia3 = politicos.clone();
        Politico[] copia4 = politicos.clone();
        Politico[] copia5 = politicos.clone();

        Politico[][] matriz1 = matriz.clone();
        Politico[][] matriz2 = matriz.clone();
        Politico[][] matriz3 = matriz.clone();
        Politico[][] matriz4 = matriz.clone();
        Politico[][] matriz5 = matriz.clone();

       copia1= insert.ordenarArreglo(copia1, "dinero");
      matriz1= insert_matriz.ordenarMatriz(matriz1);

        bubble.ordenarArreglo(copia2, "dinero");
        bubble_matriz.ordenarMatriz(matriz2);

        merge.ordenarArreglo(copia3, "dinero");
        merge_matriz.ordenarMatriz(matriz3);

        selection.ordenarArreglo(copia4, "dinero");
        selection_matriz.ordenarMatriz(matriz4);

        quick.ordenarArreglo(copia5, "dinero");
        quick_matriz.ordenarMatriz(matriz5);



        long[][] resultados = new long[5][3];
        long[][] resultadosM = new long[5][3];

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


        resultadosM[0][2] = bubble_matriz.getComparaciones();
        resultadosM[0][1] = bubble_matriz.getMovimientos();
        resultadosM[0][0] = bubble_matriz.getTiempoEjecucion();

        resultadosM[1][2] = insert_matriz.getComparaciones();
        resultadosM[1][1] = insert_matriz.getMovimientos();
        resultadosM[1][0] = insert_matriz.getTiempoEjecucion();

        resultadosM[2][2] = merge_matriz.getComparaciones();
        resultadosM[2][1] = merge_matriz.getMovimientos();
        resultadosM[2][0] = merge_matriz.getTiempoEjecucion();

        resultadosM[3][2] = selection_matriz.getComparaciones();
        resultadosM[3][1] = selection_matriz.getMovimientos();
        resultadosM[3][0] = selection_matriz.getTiempoEjecucion();

        resultadosM[4][2] = quick_matriz.getComparaciones();
        resultadosM[4][1] = quick_matriz.getMovimientos();
        resultadosM[4][0] = quick_matriz.getTiempoEjecucion();

        // Devolver la copia ordenada (puede ser cualquiera) junto con los resultados
        return new ResultadoComparacion(copia1, resultados, matriz1,resultadosM); // Usamos quick como referencia
    }



    public RetornoComparaciones realizarComparacionesCrecientes(int tamañoInicial, int tasaCrecimiento) {
        int tamaño = tamañoInicial;
        PDDocument documentoGlobal = new PDDocument();
        pdf p = new pdf();

        Politico[] arreglo = null;
        Politico[][] matriz = null;
        ResultadoComparacion resultado = null;
        long[][] datos = null;
        long[][] datosM = null;
        String[] headers = {"Burbuja", "Inserción", "Mezcla", "Selección", "Quick"};

        long[][] acumulados = new long[5][3];
        long[][] acumuladosM = new long[5][3];
        int repeticiones = 0;

        while (tamaño <= 1000) {
             // Para guardar la suma de comparaciones, movimientos, tiempo
            repeticiones += 1; // Para mayor precisión en el promedio
            arreglo = CreateArrayPoliticos(tamaño);
            matriz = CreateMatriz(arreglo);
            resultado = OrdenamientoPoliticos(arreglo, matriz);
            datos = resultado.getEstadisticas();
            datosM = resultado.getEstadisticasM();

            // Acumular los resultados para promediarlos
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    acumulados[j][k] += datos[j][k];
                    acumuladosM[j][k] += datosM[j][k];
                }
            }


            tamaño = tamaño*tasaCrecimiento; // Aumentar el tamaño con la tasa de crecimiento

            try {

                pdf.agregarContenidoAlPDF(documentoGlobal, arreglo, resultado.getArregloOrdenado(), matriz, resultado.getMatrizOrdenada(), headers, datos, datosM);
                documentoGlobal.save("documento.pdf");



            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try{
        documentoGlobal.close();
}catch (IOException e) {
            e.printStackTrace();
        }

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 3; k++) {
                if(repeticiones != 0){
                    acumulados[j][k] = acumulados[j][k]/repeticiones;
                    acumuladosM[j][k] = acumuladosM[j][k]/repeticiones;
                }

            }
        }

        RetornoComparaciones retorno = new RetornoComparaciones(acumulados, acumuladosM);



        return retorno;
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
