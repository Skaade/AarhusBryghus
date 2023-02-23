package application.model;

import java.io.Serializable;

public class RabatBeloeb extends Rabat implements Serializable {
    private double beloeb;

     /**
     Oprettes af enten Ordrelinje eller Salg
     Pre: beloeb > 0
     */
    RabatBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }

    public double getRabat(double pris) {
        if (beloeb > pris) {
            beloeb = pris;
        }
        return beloeb;
    }

    public double getBeloeb() {
        return beloeb;
    }

    public void setBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }







}
