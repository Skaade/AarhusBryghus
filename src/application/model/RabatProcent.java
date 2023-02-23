package application.model;

import java.io.Serializable;

public class RabatProcent extends Rabat implements Serializable {
    private double procent;

    /**
     Oprettes af enten Ordrelinje eller Salg
     Pre: procent > 0
     */
    RabatProcent(double procent) {
        this.procent = procent;
    }

    public double getRabat(double pris) {
        return pris * procent / 100;
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }
}
