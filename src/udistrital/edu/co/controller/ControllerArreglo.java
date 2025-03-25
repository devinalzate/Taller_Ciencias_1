package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

import java.util.ArrayList;

/**
 * Clase ControllerArreglo
 * Controlador que gestiona un arreglo de objetos del tipo "Politico".
 */
public class ControllerArreglo {

    // Lista que almacena objetos de tipo Politico
    private ArrayList<Politico> politicos;

    /**
     * Constructor de la clase.
     * Inicializa la lista de políticos.
     */
    public ControllerArreglo() {
        politicos = new ArrayList<>();
    }

    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de ordenamiento por inserción (Insertion Sort).
     *
     * @param politicos_base Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También imprime el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */
    public void sortInsertPoliticos(ArrayList<Politico> politicos_base) {
        int contador_intercambios = 0; // Contador de intercambios
        int contador_comparaciones = 0;
        long time_inicial = System.currentTimeMillis(); // Tiempo inicial

        // Crear copia de la lista base
        ArrayList<Politico> politicos_copia = new ArrayList<>(politicos_base);

        // Algoritmo de ordenamiento por inserción
        for (int i = 0; i < politicos_copia.size(); i++) {
            Politico politico = politicos_copia.get(i);
            int j = i - 1;
            contador_intercambios++;
            // Desplazamiento hacia la derecha de los elementos mayores
            while (j >= 0 && politicos_copia.get(j).getValor_a_robar() > politico.getValor_a_robar()) {
                politicos_copia.set(j + 1, politicos_copia.get(j));
                j--;
                contador_comparaciones++;
            }
            politicos_copia.set(j + 1, politico);
        }

        long time_final = System.currentTimeMillis(); // Tiempo final

        // Impresión de resultados
        System.out.println("Ordenado con insert");
        for (Politico politico : politicos_copia) {
            System.out.println(politico.getId() + " " + politico.getEdad() + " " + politico.getValor_a_robar());
        }
        System.out.println("Se hicieron " + contador_intercambios + " intercambios");
        System.out.println("Se hicieron " + contador_comparaciones + " comparaciones");
        System.out.println("Se tardó en ordenarlo: " + (time_final - time_inicial) + " ms");
    }

    /**
     * Método para ordenar una lista de políticos utilizando el algoritmo de burbuja (Bubble Sort).
     *
     * @param politicos_base Lista de políticos a ordenar.
     * El método realiza una copia de la lista base, realiza el ordenamiento y muestra el resultado.
     * También imprime el número de intercambios realizados y el tiempo total de ejecución en milisegundos.
     */
    public void sortBubblePoliticos(ArrayList<Politico> politicos_base) {
        int contador_intercambios = 0; // Contador de intercambios
        int contador_comparaciones = 0;
        long startTime = System.currentTimeMillis(); // Tiempo inicial

        // Crear copia de la lista base
        ArrayList<Politico> politicos_copia = new ArrayList<>(politicos_base);

        // Algoritmo de ordenamiento de burbuja
        for (int i = 0; i < politicos_copia.size() - 1; i++) {
            for (int j = 0; j < politicos_copia.size() - i - 1; j++) {
                contador_comparaciones++;
                if (politicos_copia.get(j).getValor_a_robar() > politicos_copia.get(j + 1).getValor_a_robar()) {
                    Politico politico = politicos_copia.get(j);
                    politicos_copia.set(j, politicos_copia.get(j + 1));
                    politicos_copia.set(j + 1, politico);
                    contador_intercambios++;
                }
            }
        }

        long endTime = System.currentTimeMillis(); // Tiempo final

        // Impresión de resultados
        System.out.println("Ordenado con bubble");
        for (Politico politico : politicos_copia) {
            System.out.println(politico.getId() + " " + politico.getEdad() + " " + politico.getValor_a_robar());
        }
        System.out.println("Se hicieron " + contador_intercambios + " intercambios");
        System.out.println("Se hicieron " + contador_comparaciones + " comparaciones");
        System.out.println("Tiempo de ejecución (ms): " + (endTime - startTime));
    }
    public ArrayList MergeSortPoliticos(ArrayList<Politico> politicos_base, int izquierda, int derecha) {
        if(politicos_base.size() == 1){
            return politicos_base;
        }
        int medio = izquierda +(derecha-izquierda)/2;
        MergeSortPoliticos(politicos_base, izquierda, medio);
        MergeSortPoliticos(politicos_base, medio+1, derecha);
        return mergeS(politicos_base,izquierda,medio,derecha);
    }
    public ArrayList mergeS(ArrayList<Politico> politicos_base, int izquierda, int medio, int derecha) {
        int i= izquierda;
        int j= medio+1;
        while (i<medio && j<derecha){
            if(politicos_base.get(i).getValor_a_robar() < politicos_base.get(j).getValor_a_robar()){
                i++;
            }else{
                Politico pibote=politicos_base.get(j);
                int control=j;
                while(control>i){
                    politicos_base.set(control, politicos_base.get(control-1));
                    control--;
                }
                politicos_base.set(i, politicos_base.get(control));
                i++;
                j++;
                medio++;
            }

        }
        return politicos_base;
    }

    /**
     * Método para imprimir la lista de políticos almacenada en el controlador.
     *
     * Muestra en consola el ID, la edad y el valor a robar de cada político en la lista.
     */
    public void printPoliticos() {
        for (Politico politico : politicos) {
            System.out.println(politico.getId() + " " + politico.getEdad() + " " + politico.getValor_a_robar());
        }
    }

    /**
     * Método para obtener la lista de políticos almacenada en el controlador.
     *
     * @return Lista de objetos de tipo Politico.
     */
    public ArrayList<Politico> getPoliticos() {
        return politicos;
    }

    /**
     * Método para generar una lista de políticos de manera aleatoria.
     *
     * @param n Número de políticos que se desea crear.
     * Cada político generado tendrá un ID único, una edad entre 20 y 80 años,
     * y un valor a robar (propiedad) aleatorio entre 1 y 1,000,000.
     */
    public void CreateArrayPoliticos(int n) {
        for (int i = 0; i < n; i++) {
            Politico politico = new Politico();
            politico.setId(i); // Asignar un ID único
            politico.setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politico.setValor_a_robar((int) (Math.random() * 1000000) + 1); // Valor a robar aleatorio
            politicos.add(politico); // Añadir el político a la lista
        }
    }
}
