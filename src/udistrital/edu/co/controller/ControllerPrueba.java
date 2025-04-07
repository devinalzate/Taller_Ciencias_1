package udistrital.edu.co.controller;

import udistrital.edu.co.model.BubbleSortStrategy;
import udistrital.edu.co.model.InsertionSortStrategy;
import udistrital.edu.co.model.MergeSortStrategy;
import udistrital.edu.co.model.Politico;

public class ControllerPrueba {

    private InsertionSortStrategy insert;
    private BubbleSortStrategy bubble;
    private MergeSortStrategy merge;

    public ControllerPrueba(){
        insert = new InsertionSortStrategy();
        bubble = new BubbleSortStrategy();
        merge = new MergeSortStrategy();
    }

    public void iniciarOrdenamiento(String metodo, int n ){
        Politico[] lista = CreateArrayPoliticos(n);
        printPoliticos(lista);
        if (metodo.equals("insert")){
            Politico[] lista_ordenada = insert.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
        }
        else if (metodo.equals("merge")){
            Politico[] lista_ordenada = merge.ordenarArreglo(lista, "dinero");
            printPoliticos(lista_ordenada);
        }
        else if (metodo.equals("bubble")){
//            Politico[][] lista_ordenada = bubble.ordenarMatriz(lista);
//            printPoliticos(lista_ordenada);
        }
    }

    public Politico[] CreateArrayPoliticos(int n) {
        Politico[] politicos = new Politico[n];
        for (int i = 0; i < n; i++) {
            politicos[i] = new Politico();
            politicos[i].setId(i); // Asignar un ID único
            politicos[i].setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20)); // Edad aleatoria
            politicos[i].setValor_a_robar((int)((Math.random() * 1000000) + 1)); // Valor a robar aleatorio
        }
        return politicos;
    }

    public void printPoliticos(Politico[] politicos) {
        for (int i = 0; i <= politicos.length-1; i++) {
            System.out.println(politicos[i].getId() + " " + politicos[i].getEdad() + " " + politicos[i].getValor_a_robar());
        }
    }
}
