package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

import java.util.ArrayList;

// Este programa se esta ejecutando con una version de JDK 21
public class Main {
    public static void main(String[] args) {
        ControllerArreglo controllerArreglo = new ControllerArreglo();
        controllerArreglo.CreateArrayPoliticos(26);
        controllerArreglo.printPoliticos(controllerArreglo.getPoliticos());
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        controllerArreglo.sortInsertPoliticos(controllerArreglo.getPoliticos());
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        controllerArreglo.sortBubblePoliticos(controllerArreglo.getPoliticos());
        ControllerMatriz controllerMatriz = new ControllerMatriz();
        Politico[][] matriz = controllerMatriz.CreateMatriz(controllerArreglo.getPoliticos());
        controllerMatriz.imprimirMatrizPoliticos(matriz);
    }
}