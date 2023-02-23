package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.StorageInitializer;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Salgssituation ss1;
    private Salgssituation ss2;
    private Salg salg1;
    private Salg salg2;
    private Salg salg3;
    private ProduktGruppe pg1;
    private ProduktGruppe pg2;
    private Produkt p1;
    private Produkt p2;
    private Produkt p3;
    private Pris pris1;
    private Pris pris2;
    private Pris pris3;
    private Betaling b_klip;


    Controller controller = Controller.getInstance();

    @BeforeEach
    void setUp(){

        ss1 = controller.createSalgssituation("Butik", "");
        ss2 = controller.createSalgssituation("Fredagsbar", "");

        salg1 = controller.createSalg(ss1);
        salg2 = controller.createSalg(ss2);
        salg3 = controller.createSalg(ss1);

        pg1 = controller.createProduktGruppe("Flakseøl");
        pg2 = controller.createProduktGruppe("Fadøl");

        p1 = pg1.createProdukt("Klosterbryg", "Flaskeøl");
        p2 = pg2.createProdukt("Forårsbryg", "Fadøl");
        p3 = pg1.createProdukt("Tuborg", "Flaskeøl");

        pris1 = ss1.createPris(70, p1);
        pris1 = ss2.createPris(70, p1);
        pris2 = ss1.createPris(70, 2, p2);
        pris2 = ss2.createPris(70, 2, p2);
        pris3 = ss1.createPris(70,3, p3);

        b_klip = controller.createBetaling(Betalingsformer.KLIPPEKORTBETALING);

        Storage.getInstance().getSalgssituationer();
    }

    @Test
    void getDagsopgoer1() {
        //TC1
        Ordrelinje ordrelinje1 = salg1.createOrdrelinje(3, p1);
        Ordrelinje ordrelinje2 = salg1.createOrdrelinje(3, p1);
        salg1.beregnSamletBeloebOgKlip();
        assertEquals(490, controller.getDagsopgoer(LocalDate.now()), "Afviger fra beløb");
    }

    @Test
    void getDagsopgoer2() {
        //TC2
        Ordrelinje ordrelinje3 = salg2.createOrdrelinje(1, p1);
        salg2.beregnSamletBeloebOgKlip();
        assertEquals(70, controller.getDagsopgoer(LocalDate.now()), "Afviger fra beløb");
    }

    @Test
    void getDagsopgoer3() {
        //TC3
        Ordrelinje ordrelinje3 = salg1.createOrdrelinje(2, p1);
        salg1.beregnSamletBeloebOgKlip();
        Ordrelinje ordrelinje4 = salg2.createOrdrelinje(1, p1);
        salg2.beregnSamletBeloebOgKlip();
        salg2.setTidspunktBetaling(LocalDateTime.now().minusDays(1));
        assertEquals(140, controller.getDagsopgoer(LocalDate.now()), "Afviger fra beløb");
    }


    @Test
    void getAntalBrugteKlip1() {
        //TC1
        Ordrelinje ordrelinje1 = salg1.createOrdrelinje(2, p2);
        Ordrelinje ordrelinje2 = salg1.createOrdrelinje(2, p3);
        ordrelinje1.setBetaling(b_klip, 2);
        ordrelinje2.setBetaling(b_klip, 2);
        salg1.beregnSamletBeloebOgKlip();
        assertEquals(10, controller.getAntalBrugteKlip(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)), "Afviger fra klip");
    }

    @Test
    void getAntalBrugteKlip2() {
        //TC2
       assertThrows(IllegalArgumentException.class,
               () -> controller.getAntalBrugteKlip(LocalDate.now(), LocalDate.now().minusDays(1)));
    }

    @Test
    void getAntalBrugteKlip3() {
        //TC3
        assertEquals(0, controller.getAntalBrugteKlip(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)), "Afviger fra klip");

    }
    @Test
    void getAntalBrugteKlip4() {
        //TC4
        Ordrelinje ordrelinje1 = salg1.createOrdrelinje(2, p2);
        Ordrelinje ordrelinje2 = salg1.createOrdrelinje(2, p3);
        ordrelinje1.setBetaling(b_klip, 2);
        salg1.beregnSamletBeloebOgKlip();
        assertEquals(4, controller.getAntalBrugteKlip(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)), "Afviger fra klip");

    }

    @Test
    void getPrProduktAntalKlipIPeriode1() {
        //TC1
        Ordrelinje ordrelinje1 = salg1.createOrdrelinje(2, p2);
        Ordrelinje ordrelinje2 = salg1.createOrdrelinje(2, p3);
        ordrelinje1.setBetaling(b_klip, 2);
        ordrelinje2.setBetaling(b_klip, 2);
        salg1.beregnSamletBeloebOgKlip();
        assertEquals(2, controller.getPrProduktAntalKlipIPeriode(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)).size(), "Afviger fra klip");

    }

    @Test
    void getPrProduktAntalKlipIPeriode2() {
        //TC2
        assertThrows(IllegalArgumentException.class,
                () -> controller.getPrProduktAntalKlipIPeriode(LocalDate.now(), LocalDate.now().minusDays(1)));

    }
    @Test
    void getPrProduktAntalKlipIPeriode3() {
        //TC3
        assertEquals(0, controller.getPrProduktAntalKlipIPeriode(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)).size(), "Afviger fra klip");
    }

    @Test
    void getPrProduktAntalKlipIPeriode4() {
        //TC4
        Ordrelinje ordrelinje1 = salg1.createOrdrelinje(2, p2);
        Ordrelinje ordrelinje2 = salg1.createOrdrelinje(2, p3);
        ordrelinje1.setBetaling(b_klip, 2);
        salg1.beregnSamletBeloebOgKlip();
        assertEquals(1, controller.getPrProduktAntalKlipIPeriode(
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)).size(), "Afviger fra klip");

    }


}