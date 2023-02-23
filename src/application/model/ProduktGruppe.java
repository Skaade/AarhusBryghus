package application.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ProduktGruppe implements Serializable {
    private String navn;
    private final HashSet<Produkt> produkter = new HashSet<>();

    public ProduktGruppe(String navn) {
        this.navn = navn;
    }

    public HashSet<Produkt> getProdukter() {
        return new HashSet<>(produkter);
    }

    /**
     * Opretter et produkt-objekt og tilføjer det til sin ProduktGruppes HashSet af produkter
     */
    public Produkt createProdukt(String navn, String beskrivelse) {
        Produkt produkt = new Produkt(navn, beskrivelse, this);
        produkter.add(produkt);
        return produkt;
    }
    /**
     * Opretter et klippekort-objekt og tilføjer det til sin ProduktGruppes HashSet af produkter
     */
    public Produkt createProduktKlippekort(String navn, String beskrivelse) {
        Produkt produkt = new Klippekort(navn, beskrivelse, this);
        produkter.add(produkt);
        return produkt;
    }

    public void removeProdukt(Produkt produkt) {
        if (produkter.contains(produkt)) {
            produkter.remove(produkt);
        }
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn){
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
