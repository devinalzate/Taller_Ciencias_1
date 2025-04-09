package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.ResultadoComparacion;
import udistrital.edu.co.view.ComparacionAlgoritmos;

import javax.swing.*;

// Este programa se esta ejecutando con una version de JDK 21
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComparacionAlgoritmos vista = new ComparacionAlgoritmos();
                ConectionController controlador = new ConectionController(vista);
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
            }
        });
    }
}