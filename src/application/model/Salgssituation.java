package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Salgssituation implements Serializable {
    private String navn;
    private String beskrivelse;
    private ArrayList<Pris> priser = new ArrayList<>();

    public Salgssituation(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }

    /**
     * Opretter et pris-objekt uden klip og tilføjer det til hhv. Salgssituationens arraylist af
     * priser og til det bestemte produkts arraylist af priser
     */
    public Pris createPris(double beloeb, Produkt produkt){
        if (beloeb <= 0) {
            throw new IllegalArgumentException("Beløb skal være større end 0");
        }
        Pris pris = new Pris(beloeb, produkt, this);
        priser.add(pris);
        produkt.addPris(pris);
        return pris;
    }

    /**
     * Opretter et pris-objekt med klip og tilføjer det til hhv. Salgssituationens arraylist af
     * priser og til det bestemte produkts arraylist af priser
     */
    public Pris createPris(double beloeb, int antalKlip, Produkt produkt){
        if (beloeb <= 0) {
            throw new IllegalArgumentException("Beløb skal være større end 0");
        }
        if (antalKlip <= 0) {
            throw new IllegalArgumentException("AntalKlip skal være større end 0");
        }
        Pris pris = new Pris(beloeb, antalKlip, produkt, this);
        priser.add(pris);
        produkt.addPris(pris);
        return pris;
    }

    public void removePris(Pris pris) {
        if (priser.contains(pris)){
            priser.remove(pris);
        }
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    @Override
    public String toString() {
        return navn;
    }
}
