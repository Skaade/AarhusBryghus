package application.model;

import java.io.Serializable;

public class Pant implements Serializable {
    double beloeb;

    public Pant(double beloeb) {
        this.beloeb = beloeb;
    }

    public double getBeloeb() {
        return beloeb;
    }

    public void setBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }
}
