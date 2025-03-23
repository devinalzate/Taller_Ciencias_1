package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

import java.util.ArrayList;

public class ControllerArreglo {
    private ArrayList<Politico> politicos;

    public ControllerArreglo(){
        politicos = new ArrayList<>();
    }
    //a
    public void sortInsertPoliticos(ArrayList<Politico> politicos_base){
        int contador = 0;
        long time_inicial = System.currentTimeMillis();
        ArrayList<Politico> politicos_copia = new ArrayList<>(politicos_base);
        for (int i = 0; i < politicos_copia.size(); i++) {
            Politico politico = politicos_copia.get(i);
            int j = i-1;
            while (j >= 0 && politicos_copia.get(j).getValor_a_robar() > politico.getValor_a_robar()){
                politicos_copia.set(j+1, politicos_copia.get(j));
                j--;
                contador++;
            }
            politicos_copia.set(j+1, politico);
        }
        long time_final = System.currentTimeMillis();
        System.out.println("Ordenado con insert");

        for (Politico politico : politicos_copia) {
            System.out.println(politico.getId()+" "+politico.getEdad()+" "+politico.getValor_a_robar());
        }

        System.out.println("Se hicieron " + contador + " intercambios");

        System.out.println("Se tardo en ordenarlo: " + (time_final - time_inicial) + " ms");
    }

    public void sortBubblePoliticos(ArrayList<Politico> politicos_base) {
        int contador = 0;
        long startTime = System.currentTimeMillis(); // Start timer
        ArrayList<Politico> politicos_copia = new ArrayList<>(politicos_base);
        for (int i = 0; i < politicos_copia.size() - 1; i++) {
            for (int j = 0; j < politicos_copia.size() - i - 1; j++) {
                if (politicos_copia.get(j).getValor_a_robar() > politicos_copia.get(j + 1).getValor_a_robar()) {
                    Politico politico = politicos_copia.get(j);
                    politicos_copia.set(j, politicos_copia.get(j + 1));
                    politicos_copia.set(j + 1, politico);
                    contador++;
                }
            }
        }
        long endTime = System.currentTimeMillis(); // End timer
        System.out.println("Ordenado con bubble");

        for (Politico politico : politicos_copia) {
            System.out.println(politico.getId() + " " + politico.getEdad() + " " + politico.getValor_a_robar());
        }

        System.out.println("Se hicieron " + contador + " intercambios");

        System.out.println("Tiempo de ejecución (ms): " + (endTime - startTime)); // Display execution time
    }

    public void printPoliticos(){
        for (Politico politico : politicos) {
            System.out.println(politico.getId()+" "+politico.getEdad()+" "+politico.getValor_a_robar());
        }
    }

    public ArrayList<Politico> getPoliticos(){
        return politicos;
    }

    public void CreateArrayPoliticos(int n){
        for (int i = 0; i < n; i++) {
            Politico politico = new Politico();
            politico.setId(i);
            politico.setEdad((int) (Math.floor(Math.random() * (60 + 1)) + 20));
            politico.setValor_a_robar((int) (Math.random() * 1000000) + 1);
            politicos.add(politico);
        }
    }

}
