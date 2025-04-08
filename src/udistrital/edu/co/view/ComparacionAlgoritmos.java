package udistrital.edu.co.view;

import udistrital.edu.co.controller.ConectionController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComparacionAlgoritmos extends JFrame {

    // Componentes globales
    private JTextField txtTamano, txtFactor;
    private JButton btnCalcular;
    private JTable tableArreglo, tableMatriz;
    private DefaultTableModel modelArreglo, modelMatriz;
    private JTabbedPane tabbedPane;

    public ComparacionAlgoritmos() {
        // Configurar la ventana
        setTitle("Comparación de Algoritmos");
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        // Fondo: #fbbd46
        getContentPane().setBackground(new Color(0xFB, 0xBD, 0x46));

        // Fuentes y colores
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = new Color(0x43, 0x61, 0xEE); // #4361ee

        // PANEL PARA ENTRADAS (usando posición absoluta)
        // Etiqueta y campo para "Tamaño"
        JLabel lblTamano = new JLabel("Tamaño");
        lblTamano.setFont(labelFont);
        lblTamano.setForeground(labelColor);
        lblTamano.setBounds(100, 30, 150, 25);
        add(lblTamano);

        txtTamano = new JTextField();
        txtTamano.setFont(fieldFont);
        txtTamano.setBounds(260, 30, 120, 25);
        add(txtTamano);

        // Etiqueta y campo para "Factor de crecimiento"
        JLabel lblFactor = new JLabel("Factor de crecimiento");
        lblFactor.setFont(labelFont);
        lblFactor.setForeground(labelColor);
        lblFactor.setBounds(100, 70, 150, 25);
        add(lblFactor);

        txtFactor = new JTextField();
        txtFactor.setFont(fieldFont);
        txtFactor.setBounds(260, 70, 120, 25);
        add(txtFactor);

        // Botón CALCULAR
        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 12));
        btnCalcular.setBackground(new Color(0x52, 0xB7, 0x88)); // #52b788
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setBounds(330, 110, 150, 30);
        add(btnCalcular);

        // Crear JTabbedPane para "Arreglo" y "Matriz"
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(100, 160, 600, 250);
        add(tabbedPane);

        // Panel para "Arreglo" y "Matriz" (usando layout absoluto)
        JPanel panelArreglo = new JPanel(null);
        JPanel panelMatriz = new JPanel(null);

        tabbedPane.addTab("Arreglo", panelArreglo);
        tabbedPane.addTab("Matriz", panelMatriz);

        // Definir columnas para las tablas
        String[] columnas = {"Algoritmo", "Tiempo de ejecución", "Iteraciones", "Comparaciones"};

        // Modelo y tabla para Arreglo
        modelArreglo = new DefaultTableModel(columnas, 0);
        tableArreglo = new JTable(modelArreglo);
        tableArreglo.setFont(new Font("Courier New", Font.PLAIN, 10));
        tableArreglo.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        tableArreglo.setRowHeight(25);
        tableArreglo.setDefaultEditor(Object.class, null); // Solo lectura
        // Deshabilitar reordenación de columnas
        tableArreglo.getTableHeader().setReorderingAllowed(false);


        // Agregar tabla a un JScrollPane en panelArreglo
        JScrollPane scrollArreglo = new JScrollPane(tableArreglo);
        scrollArreglo.setBounds(0, 0, 600, 250);
        panelArreglo.add(scrollArreglo);

        // Modelo y tabla para Matriz
        modelMatriz = new DefaultTableModel(columnas, 0);
        tableMatriz = new JTable(modelMatriz);
        tableMatriz.setFont(new Font("Courier New", Font.PLAIN, 10));
        tableMatriz.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        tableMatriz.setRowHeight(25);
        tableMatriz.setDefaultEditor(Object.class, null); // Solo lectura
        // Deshabilitar reordenación de columnas
        tableMatriz.getTableHeader().setReorderingAllowed(false);

        // Agregar tabla a un JScrollPane en panelMatriz
        JScrollPane scrollMatriz = new JScrollPane(tableMatriz);
        scrollMatriz.setBounds(0, 0, 600, 250);
        panelMatriz.add(scrollMatriz);

        // Insertar filas iniciales en la tabla de Arreglo
        String[] algoritmos = {"Burbuja", "Inserción", "Mezcla", "Selección", "Quick"};
        for (String alg : algoritmos) {
            Object[] fila = {alg, "", "", ""};
            modelArreglo.addRow(fila);
        }

        // Acción del botón: copiar datos de "Arreglo" a "Matriz"
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(getTxtTamano());

                ConectionController controller = new ConectionController();

                controller.calcular(valor);
                copiarTabla();
            }
        });

        // Agregar listener al cambio de pestaña para copiar cuando se elija "Matriz"
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                if (tabbedPane.getTitleAt(index).equals("Matriz")) {
                    copiarTabla();
                }
            }
        });
    }

    // Método para copiar las filas de la tabla de Arreglo a la de Matriz
    private void copiarTabla() {
        // Limpiar modelo de Matriz
        modelMatriz.setRowCount(0);
        // Recorrer filas de Arreglo y copiarlas
        for (int i = 0; i < modelArreglo.getRowCount(); i++) {
            Object[] fila = new Object[modelArreglo.getColumnCount()];
            for (int j = 0; j < modelArreglo.getColumnCount(); j++) {
                fila[j] = modelArreglo.getValueAt(i, j);
            }
            modelMatriz.addRow(fila);
        }
    }

    public String getTxtTamano() {
        return txtTamano.getText();
    }

    public int getTxtFactor() {
        return Integer.parseInt(txtFactor.getText());
    }

    //    public static void main(String[] args) {
//        // Ejecutar en el Event Dispatch Thread (EDT)
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ComparacionAlgoritmos app = new ComparacionAlgoritmos();
//                app.setLocationRelativeTo(null); // Centrar ventana
//                app.setVisible(true);
//            }
//        });
//    }

}
