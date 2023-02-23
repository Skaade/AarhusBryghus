package application.controller;

import application.model.*;
import storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private static Controller uniqueInstance;
    private Storage storage;

    /**
     * Sikrer at man kun kan oprette ét controller-objekt
     * Note: Singleton
     */
    public static Controller getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Controller();
        }
        return uniqueInstance;
    }

    private Controller() {
        storage = Storage.getInstance();
    }

    /**
     * Opretter en ny produktgruppe og tilføjer det til Storage
     * Hvis produktgruppens navn allerede findes kastes IllegalArgumentException og produktgruppen oprettes ikke
     */
    public ProduktGruppe createProduktGruppe(String navn) {
        for (int i = 0; i < storage.getProduktGrupper().size(); i++) {
            if (storage.getProduktGrupper().get(i).getNavn().equals(navn)) {
                throw new IllegalArgumentException("Produktgruppen findes allerede");
            }
        }
        ProduktGruppe pg = new ProduktGruppe(navn);
        storage.addProduktGruppe(pg);
        return pg;
    }

    public ArrayList<ProduktGruppe> getProduktGrupper() {
        return storage.getProduktGrupper();
    }

    public void updateProduktGruppe(ProduktGruppe produktGruppe, String navn) {
        produktGruppe.setNavn(navn);
    }

    public void removeProduktGruppe(ProduktGruppe produktGruppe) {
        storage.removeProduktGruppe(produktGruppe);
    }
    // -------------------------------------------------------------------------------------------------------------

    /**
     * Opretter en ny salgssituation og tilføjer det til Storage
     * Hvis salgssituationens navn allerede er oprettet afbrydes oprettelsen og en IllegalArgumentException kastes
     */
    public Salgssituation createSalgssituation(String navn, String beskrivelse) {
        for (int i = 0; i < storage.getSalgssituationer().size(); i++) {
            if (storage.getSalgssituationer().get(i).getNavn().equals(navn)) {
                throw new IllegalArgumentException("Salgssituation findes allerede");
            }
        }
        Salgssituation ss = new Salgssituation(navn, beskrivelse);
        storage.addSalgssituation(ss);
        return ss;
    }

    public ArrayList<Salgssituation> getSalgssituationer() {
        return storage.getSalgssituationer();
    }

    public void updateSalgssituation(Salgssituation salgssituation, String navn, String beskrivelse) {
        salgssituation.setNavn(navn);
        salgssituation.setBeskrivelse(beskrivelse);
    }

    public void removeSalgssituation(Salgssituation salgssituation) {
        storage.removeSalgssituation(salgssituation);
    }

    // ------------------------------------------------------------------------------------------------------------
    public Betaling createBetaling(Betalingsformer betalingsform) {
        for (int i = 0; i < storage.getAlleBetalinger().size(); i++) {
            if (storage.getAlleBetalinger().get(i).getBetalingsform().equals(betalingsform)) {
                throw new IllegalArgumentException("Betalingsformen findes allerede");
            }
        }
        Betaling betaling = new Betaling(betalingsform);
        storage.addBetaling(betaling);
        return betaling;
    }

    public ArrayList<Betaling> getAlleBetalinger() {
        return storage.getAlleBetalinger();
    }

    public void removeBetaling(Betaling betaling) {
        storage.removeBetaling(betaling);
    }

    // ------------------------------------------------------------------------------------------------------------
    public Pant createPant(double beloeb) {
        Pant pant = new Pant(beloeb);
        storage.addPant(pant);
        return pant;
    }

    public ArrayList<Pant> getAltPant() {
        return storage.getAltPant();
    }

    public void removePant(Pant pant) {
        storage.removePant(pant);
    }

    // ------------------------------------------------------------------------------------------------------------
    public ArrayList<Salg> getSalgsliste() {
        return storage.getSalgsliste();
    }

    public ArrayList<Salg> getSalgsObejkter() {
        ArrayList salgsObejkter = new ArrayList();
        for (Salg s : storage.getSalgsliste()) {
            if (s instanceof Leje == false) {
                salgsObejkter.add(s);
            }
        }
        return salgsObejkter;
    }

    public ArrayList<Leje> getLejeObejkter() {
        ArrayList lejeObejkter = new ArrayList();
        for (Salg s : storage.getSalgsliste()) {
            if (s instanceof Leje) {
                lejeObejkter.add(s);
            }
        }
        return lejeObejkter;
    }

    /**
     * Opretter et nyt salgsobjekt og tilføjer det til Storage
     */
    public Salg createSalg(Salgssituation salgssituation) {
        Salg salg = new Salg(salgssituation);
        storage.addSalg(salg);
        return salg;

    }

    public Salg addSalg(Salg salg) {
        storage.addSalg(salg);
        return salg;
    }

    /**
     * Opretter et nyt lejeobjekt og tilføjer det til Storage
     */
    public Leje createLeje(Salgssituation salgssituation) {
        Leje leje = null;
        leje = new Leje(salgssituation,leje.pantBeloebIndbetalt(),LocalDate.now());
        storage.addSalg(leje);
        return leje;
    }

    public Leje tvingSalgTilLeje(Salg salg){
        Leje leje = new Leje(salg.getSalgssituation(),0,salg.getTidspunktBetaling().toLocalDate());
        leje.setPantBeloebInbetalt(leje.pantBeloebIndbetalt());
        for (Ordrelinje o : salg.getOrdrelinjer()) {
            leje.createOrdrelinje(o.getAntal(), o.getProdukt());
            if (o.getRabat() != null) {
                leje.getOrdrelinjer().get(leje.getOrdrelinjer().size() - 1).createRabatBeloeb(o.getOrdrelinjeBeloeb() / o.getAntal());
            }
        }
        leje.beregnSamletBeloebOgKlip();
        storage.addSalg(leje);
        return leje;
    }

    public void updateSalg(Salg salg, LocalDateTime tidspunktBetaling, double samletBeloeb, int samletAntalKlip) {
        salg.setTidspunktBetaling(tidspunktBetaling);
        salg.setSamletBeloeb(samletBeloeb);
        salg.setSamletAntalKlip(samletAntalKlip);
    }

    public void removeSalg(Salg salg) {
        storage.removeSalg(salg);
    }
    // -------------------------------------------------------------------------------------------------------------

    public double getDagsopgoer(LocalDate date) {
        double dagsSum = 0;
        for (Salg s : Controller.getInstance().getSalgsObejkter())
            if (s.getTidspunktBetaling().getDayOfYear() == date.getDayOfYear()) {
                dagsSum += s.getSamletBeloeb();
            }
        return dagsSum;
    }

    //Kan bruges en smartere ift Collections/Maps
    public ArrayList<Salg> getDagsKvitteringer(LocalDate date) {
        ArrayList<Salg> kvitteringer = new ArrayList();
        for (Salg s : Controller.getInstance().getSalgsObejkter()) {
            if (s.getTidspunktBetaling().getDayOfYear() == date.getDayOfYear()) {
                kvitteringer.add(s);
            }
        }
        return kvitteringer;
    }

    public ArrayList<String> getProduktSolgtPrSalgssituationPrProduktGruppe
            (Salgssituation salgssituation, ProduktGruppe produktGruppe) {
        ArrayList<String> produkter = new ArrayList<>();
        for (Salg s : Controller.getInstance().getSalgsliste()) {
            if (s.getSalgssituation() == salgssituation) {
                for (Ordrelinje o : s.getOrdrelinjer()) {
                    if (o.getProdukt().getProduktGruppe() == produktGruppe) {
                        produkter.add(o.getProdukt().getNavn() + ", " + o.getAntalIalt());
                    }
                }
            }
        }
        return produkter;
    }

    public ArrayList<Leje> getIkkeAfleveredeUdlejedeProdukter() {
        ArrayList<Leje> lejeListe = new ArrayList();
        for (Leje l : getLejeObejkter())
            if (l.isBetalt() == false) {
                lejeListe.add(l);
            }
        return lejeListe;
    }

    /**
     * samlet antal klip brugt på produkter for en givet periode
     */
    public int getAntalBrugteKlip(LocalDate start, LocalDate slut) {
        if (start.isAfter(slut)) {
            throw new IllegalArgumentException("Startdato skal være før slutdato");
        }
        int sum = 0;
        for (int i = 0; i < storage.getSalgsliste().size(); i++) {
            Salg salg = storage.getSalgsliste().get(i);
            if (!salg.getTidspunktBetaling().toLocalDate().isBefore(start) &&
                    !salg.getTidspunktBetaling().toLocalDate().isAfter(slut)) {
                sum += salg.getSamletAntalKlip();
            }
        }
        return sum;
    }

    /**
     * samlet antal klip brugt på produkter for en givet periode - her fordelt på de enkelte produkter
     */
    public ArrayList<String> getPrProduktAntalKlipIPeriode(LocalDate start, LocalDate slut) {
        if (start.isAfter(slut)) {
            throw new IllegalArgumentException("Startdato skal være før slutdato");
        }
        ArrayList<String> sumKlip = new ArrayList<>();
        for (Salg s : storage.getSalgsliste()) {
            if (!s.getTidspunktBetaling().toLocalDate().isBefore(start) &&
                    !s.getTidspunktBetaling().toLocalDate().isAfter(slut)) {
                for (int i = 0; i < s.getOrdrelinjer().size(); i++) {
                    if (s.getOrdrelinjer().get(i).getBetaling() != null) {
                        if (s.getOrdrelinjer().get(i).getBetaling().getBetalingsform() == Betalingsformer.KLIPPEKORTBETALING) {
                            sumKlip.add(s.getOrdrelinjer().get(i).getProdukt().getNavn() + ", " + s.getOrdrelinjer().get(i).getOrdrelinjeKlip() + " klip");
                        }
                    }
                }
            }
        }
        return sumKlip;
    }


    // ------------------------------------------------------------------------------------------------------------

    //Serializable

    public void loadStorage() {
        System.out.println("load");
        try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
            try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                storage = (Storage) in.readObject();

                System.out.println("Storage loaded from file storage.ser");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error loading storage object");
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            System.out.println("Error loading storage object");
            throw new RuntimeException(ex);
        }
    }

    public void saveStorage() {
        System.out.println("save");
        try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage.ser");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object");
            throw new RuntimeException(ex);
        }

    }
}

