package udistrital.edu.co.model;

public class RetornoComparaciones {

    private long[][] acumulaciones;
    private long[][] acumulacionesM;

    public RetornoComparaciones(long[][] acumulaciones, long[][] acumulacionesM){
        this.acumulaciones = acumulaciones;
        this.acumulacionesM = acumulacionesM;
    }

    public long[][] getAcumulaciones() {
        return acumulaciones;
    }

    public long[][] getAcumulacionesM() {
        return acumulacionesM;
    }
}
