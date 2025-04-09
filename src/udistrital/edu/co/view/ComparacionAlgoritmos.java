package udistrital.edu.co.view;



import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ComparacionAlgoritmos extends JFrame {

    private JTextField txtTamano, txtFactor;
    private JButton btnCalcular;
    private JTable tableArreglo, tableMatriz;
    private DefaultTableModel modelArreglo, modelMatriz;
    private JTabbedPane tabbedPane;

    public ComparacionAlgoritmos() {
        setTitle("Comparación de Algoritmos");
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(0xFB, 0xBD, 0x46));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = new Color(0x43, 0x61, 0xEE);

        JLabel lblTamano = new JLabel("Tamaño");
        lblTamano.setFont(labelFont);
        lblTamano.setForeground(labelColor);
        lblTamano.setBounds(100, 30, 150, 25);
        add(lblTamano);

        txtTamano = new JTextField();
        txtTamano.setFont(fieldFont);
        txtTamano.setBounds(260, 30, 120, 25);
        add(txtTamano);

        JLabel lblFactor = new JLabel("Factor de crecimiento");
        lblFactor.setFont(labelFont);
        lblFactor.setForeground(labelColor);
        lblFactor.setBounds(100, 70, 150, 25);
        add(lblFactor);

        txtFactor = new JTextField();
        txtFactor.setFont(fieldFont);
        txtFactor.setBounds(260, 70, 120, 25);
        add(txtFactor);

        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 12));
        btnCalcular.setBackground(new Color(0x52, 0xB7, 0x88));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setBounds(330, 110, 150, 30);
        add(btnCalcular);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(100, 160, 600, 250);
        add(tabbedPane);

        JPanel panelArreglo = new JPanel(null);
        JPanel panelMatriz = new JPanel(null);

        tabbedPane.addTab("Arreglo", panelArreglo);
        tabbedPane.addTab("Matriz", panelMatriz);

        String[] columnas = {"Algoritmo", "Tiempo de ejecución", "Iteraciones", "Comparaciones"};

        modelArreglo = new DefaultTableModel(columnas, 0);
        tableArreglo = new JTable(modelArreglo);
        tableArreglo.setFont(new Font("Courier New", Font.PLAIN, 10));
        tableArreglo.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        tableArreglo.setRowHeight(25);
        tableArreglo.setDefaultEditor(Object.class, null);
        tableArreglo.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollArreglo = new JScrollPane(tableArreglo);
        scrollArreglo.setBounds(0, 0, 600, 250);
        panelArreglo.add(scrollArreglo);

        modelMatriz = new DefaultTableModel(columnas, 0);
        tableMatriz = new JTable(modelMatriz);
        tableMatriz.setFont(new Font("Courier New", Font.PLAIN, 10));
        tableMatriz.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        tableMatriz.setRowHeight(25);
        tableMatriz.setDefaultEditor(Object.class, null);
        tableMatriz.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollMatriz = new JScrollPane(tableMatriz);
        scrollMatriz.setBounds(0, 0, 600, 250);
        panelMatriz.add(scrollMatriz);

        String[] algoritmos = {"Burbuja", "Inserción", "Mezcla", "Selección", "Quick"};
        for (String alg : algoritmos) {
            Object[] fila = {alg, "", "", ""};
            modelArreglo.addRow(fila);
        }
    }

    // Métodos públicos para acceso desde el controlador

    public JButton getBtnCalcular() {
        return btnCalcular;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public String getTxtTamano() {
        return txtTamano.getText();
    }

    public int getTxtFactor() {
        return Integer.parseInt(txtFactor.getText());
    }

    public void copiarTabla() {
        modelMatriz.setRowCount(0);
        for (int i = 0; i < modelArreglo.getRowCount(); i++) {
            Object[] fila = new Object[modelArreglo.getColumnCount()];
            for (int j = 0; j < modelArreglo.getColumnCount(); j++) {
                fila[j] = modelArreglo.getValueAt(i, j);
            }
            modelMatriz.addRow(fila);
        }
    }

    public void llenarTabla(long[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                modelArreglo.setValueAt(matriz[i][j], i, j+1);
            }
        }
        copiarTabla();
    }
}
