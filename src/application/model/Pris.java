package application.model;

import java.io.Serializable;

public class Pris implements Serializable {
    private double beloeb;
    private int antalKlip;
    private boolean medKlip;
    private Produkt produkt;
    private Salgssituation salgssituation;

    /**
     * Constructor med beløb og antalKlip
     * Da constructoren ikke er public oprettes pris-objekter kun igennem Salgssituation-klassen
     * Pre: antalKlip > 0 && beloeb > 0
     */
    Pris(double beloeb, int antalKlip, Produkt produkt, Salgssituation salgssituation) {
        this.beloeb = beloeb;
        this.antalKlip = antalKlip;
        this.produkt = produkt;
        this.salgssituation = salgssituation;
        this.medKlip = true;

    }

    /**
     * Constructor med beløb uden antalKlip
     * Da constructoren ikke er public oprettes pris-objekter kun igennem Salgssituation-klassen
     * Pre: beloeb > 0
     */
    Pris(double beloeb, Produkt produkt, Salgssituation salgssituation){
        this.beloeb = beloeb;
        this.produkt = produkt;
        this.salgssituation = salgssituation;
        this.medKlip = false;
    }

    public boolean isMedKlip() {
        return medKlip;
    }

    public double getBeloeb() {
        return beloeb;
    }

    public void setBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }

    public int getAntalKlip() {
        return antalKlip;
    }

    public void setAntalKlip(int antalKlip) {
        this.antalKlip = antalKlip;
    }

    public Salgssituation getSalgssituation() {
        return salgssituation;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        if (this.produkt != produkt) {
            Produkt oldProdukt = this.produkt;
            if (oldProdukt != null) {
                oldProdukt.removePris(this);
            }
            this.produkt = produkt;
            if (produkt != null) {
                produkt.addPris(this);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getAntalKlip()!=0){
            result = produkt + ", " + beloeb + "kr, " + antalKlip+"klip";
        }
        else {
            result = produkt + ", " + beloeb;
        }
        return result;
    }
}
