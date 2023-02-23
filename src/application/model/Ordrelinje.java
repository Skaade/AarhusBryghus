package application.model;

import application.controller.Controller;

import java.io.Serializable;
import java.util.Arrays;

public class Ordrelinje implements Serializable {
    private int nr;
    private int antal;
    private int antalIalt;
    private int antalBetaltMedKlip;
    private double ordrelinjeBeloeb;
    private int ordrelinjeKlip;
    private Produkt produkt;
    private Rabat rabat;
    private Salg salg;
    private Betaling betaling;

    /**
     * Da constructoren ikke er public oprettes ordrelinje-objekter kun igennem Salg-klassen
     * Ved oprettetlse af ny ordrelinje beregnes den samlede pris i hhv. ordrelinjePris og ordrelinjeKlip
     * Pre: antal <= 0
     */
    Ordrelinje(int nr, int antal, Produkt produkt, Salg salg) {
        this.nr = nr;
        this.antal = antal;
        this.produkt = produkt;
        this.salg = salg;
        this.antalBetaltMedKlip = 0;
        this.antalIalt = antal;
        beregnOrdrelinjeBeloebOgKlip();
    }

    public int getAntalIalt() {
        return antalIalt;
    }

    public Salg getSalg() {
        return salg;
    }


    public Rabat getRabat() {
        return rabat;
    }

    /**
     * Opretter rabatBeløb og opdaterer ordrelinjePris og ordrelinjeKlip
     * Rabatten sættes til ordrelinjen
     */
    public Rabat createRabatBeloeb(double beloeb) {
        if (beloeb <= 0) {
            throw new IllegalArgumentException("beløb skal være større end 0");
        }
        Rabat rabat = new RabatBeloeb(beloeb);
        this.rabat = rabat;
        beregnOrdrelinjeBeloebOgKlip();
        return rabat;
    }

    /**
     * Opretter rabatProcent og opdaterer ordrelinjePris og ordrelinjeKlip
     * Rabatten sættes til ordrelinjen
     */
    public Rabat createRabatProcent(double procent) {
        if (procent <= 0) {
            throw new IllegalArgumentException("procent skal være større end 0");
        }
        Rabat rabat = new RabatProcent(procent);
        this.rabat = rabat;
        beregnOrdrelinjeBeloebOgKlip();
        return rabat;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setOrdrelinjeKlip(int ordrelinjeKlip) {
        this.ordrelinjeKlip = ordrelinjeKlip;
    }

    public int getOrdrelinjeKlip() {
        return ordrelinjeKlip;
    }

    public void setOrdrelinjeBeloeb(double ordrelinjeBeloeb) {
        this.ordrelinjeBeloeb = ordrelinjeBeloeb;
    }

    public double getOrdrelinjeBeloeb() {
        return ordrelinjeBeloeb;
    }

    /**
     * Beregner den samlede pris pr. ordrelinje i et salg i hhv. beløb og klip
     */
    public void beregnOrdrelinjeBeloebOgKlip() {
        int i = 0;
        boolean found = false;
        while (i < salg.getSalgssituation().getPriser().size() && !found) {
            if (this.produkt == salg.getSalgssituation().getPriser().get(i).getProdukt()) {
                double ordrelinjeBeloeb = salg.getSalgssituation().getPriser().get(i).getBeloeb() * antal;
                int ordrelinjeKlip = salg.getSalgssituation().getPriser().get(i).getAntalKlip() * antalBetaltMedKlip;

                if (betaling != null) {
                    setOrdrelinjeBeloeb(ordrelinjeBeloeb);
                    setOrdrelinjeKlip(ordrelinjeKlip);

                    if (rabat != null) {
//                        setOrdrelinjeBeloeb(ordrelinjeBeloeb - rabat.getRabat(getOrdrelinjeBeloeb()));
                        setOrdrelinjeBeloeb(antal * ((salg.getSalgssituation().getPriser().get(i).getBeloeb() + getRabat().getRabat(getOrdrelinjeBeloeb())
                                - (salg.getSalgssituation().getPriser().get(i).getBeloeb()))));
                    }
                } else {
                    if (getRabat() != null) {
//                        setOrdrelinjeBeloeb(ordrelinjeBeloeb - rabat.getRabat(getOrdrelinjeBeloeb()));
                        setOrdrelinjeBeloeb(antal * ((salg.getSalgssituation().getPriser().get(i).getBeloeb() + getRabat().getRabat(getOrdrelinjeBeloeb())
                                - (salg.getSalgssituation().getPriser().get(i).getBeloeb()))));
                    } else {
                        setOrdrelinjeBeloeb(ordrelinjeBeloeb);
                    }

                }
                found = true;
            } else i++;
        }
    }

    public Betaling getBetaling() {
        return betaling;
    }

    public int getAntalBetaltMedKlip() {
        return antalBetaltMedKlip;
    }

    public void setAntalBetaltMedKlip(int antalBetaltMedKlip) {
        this.antalBetaltMedKlip = antalBetaltMedKlip;
    }

    public void setBetaling(Betaling betaling, int antalProdukter) {
        if (betaling.getBetalingsform() != null) {
            if (betaling.getBetalingsform() != Betalingsformer.KLIPPEKORTBETALING) {
                throw new RuntimeException("Betalingsformen skal være KLIPPEKORTBETALING.");
            }
        }
        if (this.betaling != betaling
//                && this.produkt.getPriser().get(tjekOmGyldigKlipBetaling()).isMedKlip()==true)
        ) {
            Betaling oldBetalingsform = this.betaling;
            if (oldBetalingsform != null) {
                oldBetalingsform.removeOrdrelinje(this);
            }
            if (antalProdukter > antal) {
                throw new RuntimeException("Du skal angive et antal der er mindre" +
                        " eller lig med antal produkter produkter på ordrelinjen.");
            }
            this.betaling = betaling;
            setAntalBetaltMedKlip(antalProdukter);
            setAntal(antal - antalProdukter); //Se her
            betaling.addOrdrelinje(this, antalProdukter);
        }
    }

//    public int tjekOmGyldigKlipBetaling (){
//        int foundIndex = -1;
//        int i = 0;
//        while (i < this.salg.getSalgssituation().getPriser().size() && foundIndex == -1) {
//            if (this.produkt == this.salg.getSalgssituation().getPriser().get(i).getProdukt()) {
//                if (this.salg.getSalgssituation().getPriser().get(i).isMedKlip() == true)
//                    foundIndex = i;
//            } else i++;
//        }
//        return i;
//    }

    @Override
    public String toString() {
        String result = "";
        if (this.getOrdrelinjeKlip() != 0) {
            result = produkt.getNavn() + ", " +
                +antal +
                "stk, " + getOrdrelinjeBeloeb() +
                "0 kr. " + getOrdrelinjeKlip() + " klip";}
        else {
            result=produkt.getNavn() + ", " +
                    +antal +
                    "stk, " + getOrdrelinjeBeloeb() +
                    "0 kr.";
        }
        return result;
    }
}



