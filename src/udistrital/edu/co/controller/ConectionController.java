package udistrital.edu.co.controller;

import udistrital.edu.co.model.Ordenamiento;
import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.ResultadoComparacion;
import udistrital.edu.co.view.ComparacionAlgoritmos;

public class ConectionController {
    private ComparacionAlgoritmos vista;
    private controladorJP controlador;
    private ControllerPrueba controla;

    public ConectionController(){
        this.vista = new ComparacionAlgoritmos();
        this.controlador = new controladorJP();
        this.controla = new ControllerPrueba();
    }

    public void inicializar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

    }

    public void calcular(int tamaño){
      /*  Politico[] inicial = controlador.CreateArrayPoliticos(tamaño);

        Politico[] inversa = controlador.CreateArrayPoliticosOrdenInverso(tamaño);

        controla.printPoliticos(inversa);

        ResultadoComparacion resultado = controlador.CreateArrayPoliticos(inicial);

        long[][] estadisticas = resultado.getEstadisticas();
        return estadisticas;

       */
       controlador.imprimirMatriz(controlador.realizarComparacionesCrecientes(tamaño, 2));


    }

}
