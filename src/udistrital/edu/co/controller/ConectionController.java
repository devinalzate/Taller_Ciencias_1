package udistrital.edu.co.controller;

import udistrital.edu.co.model.Ordenamiento;
import udistrital.edu.co.model.Politico;
import udistrital.edu.co.model.ResultadoComparacion;
import udistrital.edu.co.model.RetornoComparaciones;
import udistrital.edu.co.view.ComparacionAlgoritmos;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConectionController {
    private ComparacionAlgoritmos vista;
    private controladorJP controlador;
    private ControllerPrueba controla;
    private long[][] acumulaciones;
    private long[][] acumulacionesM;


    public ConectionController(ComparacionAlgoritmos vista) {
        this.vista = vista;
        agregarListeners();
        this.controlador = new controladorJP();
        this.controla = new ControllerPrueba();
    }

    public void inicializar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

    }
    private void agregarListeners() {
        // Botón calcular
        vista.getBtnCalcular().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(vista.getTxtTamano());
                calcular(valor);
                vista.copiarTabla();
            }
        });

        // Cambio de pestaña
        vista.getTabbedPane().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = vista.getTabbedPane().getSelectedIndex();
                String titulo = vista.getTabbedPane().getTitleAt(index);

                if (titulo.equals("Matriz")) {
                    vista.llenarTabla(acumulacionesM); // matriz acumulada
                } else if (titulo.equals("Arreglo")) {
                    vista.llenarTabla(acumulaciones); // resultados normales
                }

                vista.copiarTabla(); // si necesitas copiar los datos visibles a otro lugar
            }
        });
    }

    public void calcular(int tamaño){
      /*  Politico[] inicial = controlador.CreateArrayPoliticos(tamaño);

        Politico[] inversa = controlador.CreateArrayPoliticosOrdenInverso(tamaño);

        controla.printPoliticos(inversa);

        ResultadoComparacion resultado = controlador.CreateArrayPoliticos(inicial);

        long[][] estadisticas = resultado.getEstadisticas();
        return estadisticas;

       */
        RetornoComparaciones resultados = controlador.realizarComparacionesCrecientes(tamaño, 2);
        acumulaciones = resultados.getAcumulaciones();
        acumulacionesM = resultados.getAcumulacionesM();

        // Mostrar por defecto uno (ej. el arreglo)
        vista.llenarTabla(acumulaciones);
        controlador.imprimirMatriz(resultados.getAcumulaciones());
        controlador.imprimirMatriz(resultados.getAcumulacionesM());


    }


}
