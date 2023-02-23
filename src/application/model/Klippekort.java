package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Klippekort extends Produkt implements Serializable {
    private transient static int staticNr = 0;
    private int nr;
    private int antalKlip;

    Klippekort(String navn, String beskrivelse, ProduktGruppe produktGruppe) {
        super(navn, beskrivelse, produktGruppe);
        staticNr++;
        this.nr = staticNr;
        this.antalKlip = 4;
    }

    public static int getStaticNr() {
        return staticNr;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getAntalKlip() {
        return antalKlip;
    }

    public void setAntalKlip(int antalKlip) {
        this.antalKlip = antalKlip;
    }
}
