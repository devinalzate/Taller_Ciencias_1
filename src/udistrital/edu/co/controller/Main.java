package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

// Este programa se esta ejecutando con una version de JDK 21
public class Main {
    public static void main(String[] args) {
//        ControllerPrueba control = new ControllerPrueba();
//        control.iniciarOrdenamiento("merge", 10);

        ControllerArreglo controllerArreglo = new ControllerArreglo();
        controllerArreglo.CreateArrayPoliticos(17);
//        controllerArreglo.printPoliticos(controllerArreglo.getPoliticos());
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        controllerArreglo.sortInsertPoliticos(controllerArreglo.getPoliticos());
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        controllerArreglo.sortBubblePoliticos(controllerArreglo.getPoliticos());
//        Politico[] politicosOriginal = controllerArreglo.getPoliticos();

        // Usar el método de instancia mergeSortPoliticos
//        Politico[] politicosCopia = controllerArreglo.mergeSortPoliticos(politicosOriginal, 0, politicosOriginal.length - 1);
//System.out.println("---------------------------");
//        // Imprimir el resultado de la copia ordenada
//        for (int i = 0; i < politicosCopia.length; i++) {
//            System.out.println(
//                    politicosCopia[i].getId() + " " + politicosCopia[i].getEdad() + " " + politicosCopia[i].getValor_a_robar()
//            );
//        }




        ControllerMatriz controllerMatriz = new ControllerMatriz();
        Politico[][] matriz = controllerMatriz.CreateMatriz(controllerArreglo.getPoliticos());
        controllerMatriz.imprimirMatrizPoliticos(matriz);
        controllerMatriz.InsertSortMatriz(matriz);
//        controllerMatriz.BubbleSortMatriz(matriz);
    }
}