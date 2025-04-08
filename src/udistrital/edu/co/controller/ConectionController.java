package udistrital.edu.co.controller;

import udistrital.edu.co.model.Ordenamiento;
import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.ResultadoComparacion;
import udistrital.edu.co.view.ComparacionAlgoritmos;

public class ConectionController {
    private ComparacionAlgoritmos vista;
    private controladorJP controlador;

    public ConectionController(){
        this.vista = new ComparacionAlgoritmos();
        this.controlador = new controladorJP();
    }

    public void inicializar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public long[][] calcular(int tamaño){
        Politico[] inicial = controlador.CreateArrayPoliticos(tamaño);

        ResultadoComparacion resultado = controlador.CreateArrayPoliticos(inicial);

        long[][] estadisticas = resultado.getEstadisticas();
        return estadisticas;
    }

}
