package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Betaling implements Serializable {
    private final ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
    private final ArrayList<Beloeb> alleBeloeb = new ArrayList<>();
    private Betalingsformer betalingsform;


    public Betaling(Betalingsformer betalingsform){
        this.betalingsform = betalingsform;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjer);
    }

    public void addOrdrelinje(Ordrelinje ordrelinje, int antalProdukter){
        if (!ordrelinjer.contains(ordrelinje)){
            ordrelinjer.add(ordrelinje);
            ordrelinje.setBetaling(this, antalProdukter);
        }
    }

    public void removeOrdrelinje(Ordrelinje ordrelinje){
        if (ordrelinjer.contains(ordrelinje)){
            ordrelinjer.remove(ordrelinje);
            ordrelinje.setBetaling(null, 0);
        }
    }
    public Betalingsformer getBetalingsform() {
        return betalingsform;
    }

    public ArrayList<Beloeb> getAlleBeloeb(){
        return new ArrayList<>(alleBeloeb);
    }

    public void addBeloeb(Beloeb beloeb){
        if (!alleBeloeb.contains(beloeb)){
            alleBeloeb.add(beloeb);
            beloeb.setBetaling(this);
        }
    }

    public void removeBeloeb(Beloeb beloeb){
        if (alleBeloeb.contains(beloeb)){
            alleBeloeb.remove(beloeb);
            beloeb.setBetaling(null);
        }
    }

    @Override
    public String toString() {
        return betalingsform + "";
    }
}
