package udistrital.edu.co.controller;

import udistrital.edu.co.model.BubbleSortStrategy;
import udistrital.edu.co.model.InsertionSortStrategy;
import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.SortStrategy;

public class ControllerMatriz {
    private Politico[][] matrizPoliticos;
    public InsertionSortStrategy insert = new InsertionSortStrategy();
    public BubbleSortStrategy bubble = new BubbleSortStrategy();

    public static Politico[] unirFilas(Politico[][] matriz) {
        // Primero, calculamos el total de elementos
        int totalElementos = 0;
        for (Politico[] fila : matriz) {
            totalElementos += fila.length;
        }

        // Creamos el arreglo resultante
        Politico[] resultado = new Politico[totalElementos];

        // Llenamos el arreglo con los elementos de la matriz
        int index = 0;
        for (Politico[] fila : matriz) {
            for (Politico elemento : fila) {
                resultado[index++] = elemento;
            }
        }

        return resultado;
    }

    public static Politico[][] convertirAMatriz(Politico[] arreglo, int columnas) {
        if (columnas <= 0) {
            throw new IllegalArgumentException("El número de columnas debe ser mayor que cero.");
        }

        int filas = (int) Math.ceil((double) arreglo.length / columnas);
        Politico[][] matriz = new Politico[filas][columnas];

        for (int i = 0; i < arreglo.length; i++) {
            int fila = i / columnas;
            int col = i % columnas;
            matriz[fila][col] = arreglo[i];
        }

        return matriz;
    }

    public void InsertSortMatriz(Politico[][] matriz_base){
        Politico[][] matriz_copia = matriz_base.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;

        Politico[] arreglo = unirFilas(matriz_copia);

        Politico[] arreglo_ordenado = insert.ordenarArreglo(arreglo, "edad");

        matriz_copia = convertirAMatriz(arreglo_ordenado, columnas);

        int maxDimension = Math.max(filas, columnas);

        for (int i = 0; i < maxDimension; i++) {
            if (i < filas) {
                for (int j = 1; j < columnas; j++) {
                    Politico politico = matriz_copia[i][j];
                    int k = j - 1;

                    if (politico == null) {
                        break;
                    }
                    while (k >= 0 && matriz_copia[i][k].getValor_a_robar() > politico.getValor_a_robar()) {
                        matriz_copia[i][k + 1] = matriz_copia[i][k];
                        k--;
                    }
                    matriz_copia[i][k + 1] = politico;
                }
            }
        }

        imprimirMatrizPoliticos(matriz_copia);

    }

    public void BubbleSortMatriz(Politico[][] matriz_base){
        long inicio = System.currentTimeMillis();
        Politico[][] matriz_copia = matriz_base.clone();
        int filas = matriz_copia.length;
        int columnas = matriz_copia[0].length;


        Politico[] arreglo = unirFilas(matriz_copia);

        Politico[] arreglo_ordenado = bubble.ordenarArreglo(arreglo, "edad");

        matriz_copia = convertirAMatriz(arreglo_ordenado, columnas);


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

        imprimirMatrizPoliticos(matriz_copia);

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
}
