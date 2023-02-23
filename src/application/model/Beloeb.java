package application.model;

import java.io.Serializable;

public class Beloeb implements Serializable {
    private double tilBetaling;
    private Salg salg;
    private Betaling betaling;

    Beloeb(double tilBetaling, Betaling betaling, Salg salg){
        this.tilBetaling = tilBetaling;
        this.betaling = betaling;
        this.salg = salg;
    }

    public Betaling getBetaling() {
        return betaling;
    }

    public void setBetaling(Betaling betaling){
        if (this.betaling != betaling){
            Betaling oldBetalingsform = this.betaling;
            if (oldBetalingsform != null){
                oldBetalingsform.removeBeloeb(this);
            }
            this.betaling = betaling;
            if (betaling != null){
                betaling.addBeloeb(this);
            }
        }
    }

    public double getTilBetaling() {
        return tilBetaling;
    }

    public void setTilBetaling(double tilBetaling) {
        this.tilBetaling = tilBetaling;
    }

    public Salg getSalg() {
        return salg;
    }


}
