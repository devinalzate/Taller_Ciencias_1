package udistrital.edu.co.controller;

import udistrital.edu.co.model.*;

public class ControllerPrueba {
    
    private InsertionSortStrategy insert;
    private BubbleSortStrategy bubble;
    private MergeSortStrategy merge;
    private SelectionSortStrategy selection;
    private QuickSortStrategy quick;
    private Ordenamiento valores;

    public ControllerPrueba(){
        valores = new Ordenamiento();
        insert = new InsertionSortStrategy();
        bubble = new BubbleSortStrategy();
        merge = new MergeSortStrategy();
        selection = new SelectionSortStrategy();
        quick = new QuickSortStrategy();
    }

    public Ordenamiento iniciarOrdenamientoArreglo(String metodo, int n ){
        Politico[] lista = CreateArrayPoliticos(n);
        printPoliticos(lista);
        if (metodo.equals("insert")){
            Politico[] lista_ordenada = insert.ordenarArreglo(lista, "dinero");
            valores.setComparaciones(insert.getComparaciones());
            valores.setIntercambios(insert.getMovimientos());
            printPoliticos(lista_ordenada);
            return valores;
        }
        else if (metodo.equals("merge")){
            Politico[] lista_ordenada = merge.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
            valores.setComparaciones(merge.getComparaciones());
            valores.setIntercambios(merge.getMovimientos());
            printPoliticos(lista_ordenada);
            return valores;
        }
        else if (metodo.equals("bubble")){
            Politico[] lista_ordenada = bubble.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
            valores.setComparaciones(bubble.getComparaciones());
            valores.setIntercambios(bubble.getMovimientos());
            printPoliticos(lista_ordenada);
            return valores;
        }
        else if(metodo.equals("selection")){
            Politico[] lista_ordenada = selection.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
            valores.setComparaciones(selection.getComparaciones());
            valores.setIntercambios(selection.getMovimientos());
            printPoliticos(lista_ordenada);
            return valores;
        }
        else if(metodo.equals("quick")){
            Politico[] lista_ordenada = quick.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
            valores.setComparaciones(quick.getComparaciones());
            valores.setIntercambios(quick.getMovimientos());
            printPoliticos(lista_ordenada);
            return valores;
        }
        return null;
    }

    public void iniciarOrdenamientoMatriz(String metodo, int n){
        Politico[] lista = CreateArrayPoliticos(n);
        Politico[][] matriz = CreateMatriz(lista);
        imprimirMatrizPoliticos(matriz);
        if (metodo.equals("insert")){
            Politico[][] matriz_ordenada = insert.ordenarMatriz(matriz);
            imprimirMatrizPoliticos(matriz_ordenada);
        }
        else if (metodo.equals("merge")){
            Politico[][] matriz_ordenada = merge.ordenarMatriz(matriz);
            imprimirMatrizPoliticos(matriz_ordenada);
        }
        else if (metodo.equals("bubble")){
            Politico[][] matriz_ordenada = bubble.ordenarMatriz(matriz);
            imprimirMatrizPoliticos(matriz_ordenada);
        }
        else if(metodo.equals("selection")){
            Politico[][] matriz_ordenada = selection.ordenarMatriz(matriz);
            imprimirMatrizPoliticos(matriz_ordenada);
        }
        else if(metodo.equals("quick")){
            Politico[][] matriz_ordenada = quick.ordenarMatriz(matriz);
            imprimirMatrizPoliticos(matriz_ordenada);
        }
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

    public void printPoliticos(Politico[] politicos) {
        for (int i = 0; i <= politicos.length-1; i++) {
            System.out.println(politicos[i].getId() + " " + politicos[i].getEdad() + " " + politicos[i].getValor_a_robar());
        }
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

    public void imprimirMatrizPoliticos(Politico[][] matrizPoliticos) {
        // Validación inicial
        if (matrizPoliticos == null || matrizPoliticos.length == 0) {
            System.out.println("╔════════════════════════════╗");
            System.out.println("║  MATRIZ VACÍA O NULA       ║");
            System.out.println("╚════════════════════════════╝");
            return;
        }

        // Constantes de formato
        final String BORDE_HORIZONTAL = "═";
        final String ESQUINA_SUP_IZQ = "╔";
        final String ESQUINA_SUP_DER = "╗";
        final String ESQUINA_INF_IZQ = "╚";
        final String ESQUINA_INF_DER = "╝";
        final String UNION_IZQ = "╠";
        final String UNION_DER = "╣";
        final String UNION_CENTRO = "╬";
        final String LINEA_VERTICAL = "║";
        final String SEPARADOR_HORIZONTAL = "═";

        // Calcular anchos de columnas
        int[] anchosColumnas = new int[matrizPoliticos[0].length];
        for (int col = 0; col < matrizPoliticos[0].length; col++) {
            anchosColumnas[col] = "Col X".length(); // Ancho mínimo
            for (int fila = 0; fila < matrizPoliticos.length; fila++) {
                if (matrizPoliticos[fila][col] != null) {
                    String contenido = String.format("%d|%d|%d",
                            matrizPoliticos[fila][col].getId(),
                            matrizPoliticos[fila][col].getEdad(),
                            matrizPoliticos[fila][col].getValor_a_robar());
                    anchosColumnas[col] = Math.max(anchosColumnas[col], contenido.length());
                }
            }
            anchosColumnas[col] += 2; // Margen adicional
        }

        // Imprimir encabezado superior
        System.out.print(ESQUINA_SUP_IZQ);
        for (int col = 0; col < matrizPoliticos[0].length; col++) {
            System.out.print(BORDE_HORIZONTAL.repeat(anchosColumnas[col]));
            if (col < matrizPoliticos[0].length - 1) {
                System.out.print(UNION_CENTRO);
            }
        }
        System.out.println(ESQUINA_SUP_DER);

        // Imprimir encabezado de columnas
        System.out.print(LINEA_VERTICAL);
        for (int col = 0; col < matrizPoliticos[0].length; col++) {
            System.out.print(String.format(" %-" + (anchosColumnas[col] - 1) + "s", "Col " + col));
            System.out.print(LINEA_VERTICAL);
        }
        System.out.println();

        // Imprimir separador
        System.out.print(UNION_IZQ);
        for (int col = 0; col < matrizPoliticos[0].length; col++) {
            System.out.print(SEPARADOR_HORIZONTAL.repeat(anchosColumnas[col]));
            if (col < matrizPoliticos[0].length - 1) {
                System.out.print(UNION_CENTRO);
            }
        }
        System.out.println(UNION_DER);

        // Imprimir filas de datos
        for (int fila = 0; fila < matrizPoliticos.length; fila++) {
            System.out.print(LINEA_VERTICAL);
            for (int col = 0; col < matrizPoliticos[fila].length; col++) {
                Politico politico = matrizPoliticos[fila][col];
                String contenido;

                if (politico != null) {
                    contenido = String.format("%d|%d|%d",
                            politico.getId(),
                            politico.getEdad(),
                            politico.getValor_a_robar());
                } else {
                    contenido = "null";
                }

                System.out.print(String.format(" %-" + (anchosColumnas[col] - 1) + "s", contenido));
                System.out.print(LINEA_VERTICAL);
            }
            System.out.println();

            // Imprimir separador entre filas (excepto después de la última)
            if (fila < matrizPoliticos.length - 1) {
                System.out.print(UNION_IZQ);
                for (int col = 0; col < matrizPoliticos[fila].length; col++) {
                    System.out.print(SEPARADOR_HORIZONTAL.repeat(anchosColumnas[col]));
                    if (col < matrizPoliticos[fila].length - 1) {
                        System.out.print(UNION_CENTRO);
                    }
                }
                System.out.println(UNION_DER);
            }
        }

        // Imprimir borde inferior
        System.out.print(ESQUINA_INF_IZQ);
        for (int col = 0; col < matrizPoliticos[0].length; col++) {
            System.out.print(BORDE_HORIZONTAL.repeat(anchosColumnas[col]));
            if (col < matrizPoliticos[0].length - 1) {
                System.out.print(UNION_CENTRO);
            }
        }
        System.out.println(ESQUINA_INF_DER);
    }
    public void probarTodosLosAlgoritmos(int tamañoInicial, double coeficiente) {
        String[] metodos = {"insert", "merge", "bubble", "selection", "quick"};

        for (String metodo : metodos) {
            System.out.println("\n\n========== MÉTODO: " + metodo.toUpperCase() + " ==========\n");
            int tamañoActual = tamañoInicial;

            while (tamañoActual <= 300000000) {
                System.out.println("Tamaño actual: " + tamañoActual);

                // Crear datos
                Politico[] arregloOriginal = this.CreateArrayPoliticos(tamañoActual);
                Politico[][] matrizOriginal = this.CreateMatriz(arregloOriginal);

                // --- Arreglo ---
                System.out.println(">>> ARREGLO ORIGINAL:");
                this.printPoliticos(arregloOriginal);

                long inicioArreglo = System.nanoTime();
                Politico[] arregloOrdenado = null;
                switch (metodo) {
                    case "insert":
                        arregloOrdenado = this.insert.ordenarArreglo(arregloOriginal.clone(), "dinero");
                        break;
                    case "merge":
                        arregloOrdenado = this.merge.ordenarArreglo(arregloOriginal.clone(), "dinero");
                        break;
                    case "bubble":
                        arregloOrdenado = this.bubble.ordenarArreglo(arregloOriginal.clone(), "dinero");
                        break;
                    case "selection":
                        arregloOrdenado = this.selection.ordenarArreglo(arregloOriginal.clone(), "dinero");
                        break;
                    case "quick":
                        arregloOrdenado = this.quick.ordenarArreglo(arregloOriginal.clone(), "dinero");
                        break;
                }
                long finArreglo = System.nanoTime();

                System.out.println(">>> ARREGLO ORDENADO:");
                this.printPoliticos(arregloOrdenado);

                // --- Matriz ---
                System.out.println(">>> MATRIZ ORIGINAL:");
                this.imprimirMatrizPoliticos(matrizOriginal);

                long inicioMatriz = System.nanoTime();
                Politico[][] matrizOrdenada = null;
                switch (metodo) {
                    case "insert":
                        matrizOrdenada = this.insert.ordenarMatriz(matrizOriginal);
                        break;
                    case "merge":
                        matrizOrdenada = this.merge.ordenarMatriz(matrizOriginal);
                        break;
                    case "bubble":
                        matrizOrdenada = this.bubble.ordenarMatriz(matrizOriginal);
                        break;
                    case "selection":
                        matrizOrdenada = this.selection.ordenarMatriz(matrizOriginal);
                        break;
                    case "quick":
                        matrizOrdenada = this.quick.ordenarMatriz(matrizOriginal);
                        break;
                }
                long finMatriz = System.nanoTime();

                System.out.println(">>> MATRIZ ORDENADA:");
                this.imprimirMatrizPoliticos(matrizOrdenada);

                // Tiempos
                long tiempoArreglo = (finArreglo - inicioArreglo) / 1000000;
                long tiempoMatriz = (finMatriz - inicioMatriz) / 1000000;

                System.out.println("→ Tiempo en ordenar ARREGLO: " + tiempoArreglo + " ms");
                System.out.println("→ Tiempo en ordenar MATRIZ: " + tiempoMatriz + " ms");
                System.out.println("======================================================");

                tamañoActual = (int) Math.round(tamañoActual * coeficiente);
            }
        }
    }

}
