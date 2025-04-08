package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.ResultadoComparacion;

// Este programa se esta ejecutando con una version de JDK 21
public class Main {
    public static void main(String[] args) {
//        ControllerPrueba control = new ControllerPrueba();
//        control.iniciarOrdenamientoArreglo("merge", 10);
//        control.iniciarOrdenamientoMatriz("insert", 17);
//        control.iniciarOrdenamientoMatriz("merge", 16);
//        control.iniciarOrdenamientoMatriz("quick", 16);
//        control.iniciarOrdenamientoMatriz("selection", 16);
        ConectionController controller = new ConectionController();
        controller.inicializar();
    }
}