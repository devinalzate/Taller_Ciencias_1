package udistrital.edu.co.controller;

import udistrital.edu.co.model.Politico;

import java.util.ArrayList;

public class ControllerMatriz {
    private Politico[][] matrizPoliticos;

    public Politico [][]CreateMatriz(ArrayList<Politico> politicos){
        int size = politicos.size();
        for (int i = 1; i <= Math.sqrt(size); i++) {
            if (size % i == 0) { // Verificamos si 'i' es divisor de 'number'
                int pair = size / i; // Calculamos el par asociado
                System.out.println("Par encontrado: (" + i + ", " + pair + ")");
                matrizPoliticos = new Politico[i][pair];
                break; // Detenemos la búsqueda después de encontrar el primer par
            }
        }
        return matrizPoliticos;
    }
}
