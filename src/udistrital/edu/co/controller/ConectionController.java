package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;
import udistrital.edu.co.view.ComparacionAlgoritmos;

public class ConectionController {
    private ComparacionAlgoritmos vista;
    private ControllerPrueba controlador;

    public ConectionController(){
        this.vista = new ComparacionAlgoritmos();
        this.controlador = new ControllerPrueba();
        inicializar();
    }

    public void inicializar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        int tamaño = vista.getTxtTamano();

        Politico[] array = controlador.iniciarOrdenamientoArreglo("insert", tamaño);

    }

}
