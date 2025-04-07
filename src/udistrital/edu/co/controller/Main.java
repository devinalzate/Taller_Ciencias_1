package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

// Este programa se esta ejecutando con una version de JDK 21
public class Main {
    public static void main(String[] args) {
        ControllerPrueba control = new ControllerPrueba();
        control.iniciarOrdenamientoArreglo("merge", 10);
        control.iniciarOrdenamientoMatriz("insert", 17);

    }
}