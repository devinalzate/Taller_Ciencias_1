package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ControllerArreglo controllerArreglo = new ControllerArreglo();
        controllerArreglo.CreateArrayPoliticos(100);
        controllerArreglo.printPoliticos();
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        //controllerArreglo.sortInsertPoliticos(controllerArreglo.getPoliticos());
        //System.out.println("---------------------------");
        //System.out.println("---------------------------");
        controllerArreglo.sortBubblePoliticos(controllerArreglo.getPoliticos());
    }
}