package udistrital.edu.co.model;

public class Politico {
    private int id;
    private int edad;
    private int valor_a_robar;

    public Politico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public int getValor_a_robar() {
        return valor_a_robar;
    }

    public void setValor_a_robar(int valor_a_robar) {
        this.valor_a_robar = valor_a_robar;
    }
}
