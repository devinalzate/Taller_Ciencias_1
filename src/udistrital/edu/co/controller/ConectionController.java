package udistrital.edu.co.controller;

import udistrital.edu.co.model.Ordenamiento;
import udistrital.edu.co.model.Politico;
import udistrital.edu.co.view.ComparacionAlgoritmos;

public class ConectionController {
    private ComparacionAlgoritmos vista;
    private ControllerPrueba controlador;

    public ConectionController(){
        this.vista = new ComparacionAlgoritmos();
        this.controlador = new ControllerPrueba();
    }

    public void inicializar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void calcular(int tamaño){

        Ordenamiento ordenamiento = controlador.iniciarOrdenamientoArreglo("insert", tamaño);

        System.out.println(ordenamiento.getComparaciones());


    }

}
