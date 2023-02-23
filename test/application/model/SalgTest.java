package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    private ProduktGruppe pg;
    private Produkt p1;
    private Produkt p2;
    private Salgssituation ss;
    private Pris prisBeløb;
    private Pris prisKlipKlosterbryg;
    private Pris prisKlipForårsbryg;
    private Salg salg1;
    private Ordrelinje ordrelinje1;
    private Ordrelinje ordrelinje2;
    private Salg salg2;
    private Ordrelinje ordrelinje3;
    private Ordrelinje ordrelinje4;
    private Salg salg3;
    private Salg salg4;
    private Salg salg5;
    private Betaling betaling;

    @BeforeEach
    void setUp(){
        pg = Controller.getInstance().createProduktGruppe("Flakseøl");
        p1 = pg.createProdukt("Klosterbryg", "5%");
        p2 = pg.createProdukt("Forårsbryg", "7%");
        ss = Controller.getInstance().createSalgssituation("Fredagsbar", "kl. 16-22");
        prisKlipKlosterbryg = ss.createPris(70, 2, p1);
        prisKlipForårsbryg = ss.createPris(70, 2, p2);
        betaling = Controller.getInstance().createBetaling(Betalingsformer.KLIPPEKORTBETALING);

        salg1 = Controller.getInstance().createSalg(ss);
        ordrelinje1 = salg1.createOrdrelinje(2, p1);
        ordrelinje2 = salg1.createOrdrelinje(1, p2);

        salg2 = Controller.getInstance().createSalg(ss);
        ordrelinje3 = salg2.createOrdrelinje(2, p1);

        salg3 = Controller.getInstance().createSalg(ss);

        salg4 = Controller.getInstance().createSalg(ss);
        ordrelinje4 = salg4.createOrdrelinje(3, p1);
        ordrelinje4.setBetaling(betaling, 2);

        salg5 = Controller.getInstance().createSalg(ss);

    }

        @Test
        void beregnSamletBeloebOgKlip1() {
        //TC1
        assertEquals(210, salg1.getSamletBeloeb(), "Samlet beløb afviger fra 210");
    }
        @Test
        void beregnSamletBeloebOgKlip2() {
        //TC2
        Rabat rabat = salg2.createRabatPct(10);
        assertEquals(126, salg2.getSamletBeloeb());
    }
        @Test
        void beregnSamletBeloebOgKlip3() {
        //TC3
        Rabat rabat3 = salg2.createRabatBeloeb(10);
        assertEquals(130, salg2.getSamletBeloeb());
    }

        @Test
        void beregnSamletBeloebOgKlip4() {
        //TC4
        assertEquals(140, salg2.getSamletBeloeb(), "samlet beløb afviger fra 140");
    }

        @Test
        void beregnSamletBeloebOgKlip5() {
        //TC5
        assertEquals(210, salg1.getSamletBeloeb(), "samlet beløb afviger fra 210");
    }

    @Test
    void beregnSamletBeloebOgKlip6() {
        //TC6
        salg4.beregnSamletBeloebOgKlip();
        assertEquals(70, salg4.getSamletBeloeb(), "samlet beløb afviger fra 210");
        assertEquals(4, salg4.getSamletAntalKlip(), "samlet antal klip afviger fra 4");
    }
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void createOrdrelinje1() {
        //TC1
      Ordrelinje ordrelinje1 = salg5.createOrdrelinje(2, p1);
        assertEquals(1, salg5.getOrdrelinjer().size(), "Ordrelinjen er ikke blevet tilføjet");
    }

    @Test
    void createOrdrelinje2() {
        //TC2
    Ordrelinje ordrelinje1 = salg5.createOrdrelinje(2, p1);
    Ordrelinje ordrelinje2 = salg5.createOrdrelinje(3, p1);
        assertEquals(1, salg5.getOrdrelinjer().size(), "Ordrelinjerne er ikke blevet slået sammen");
        assertEquals(5, ordrelinje1.getAntal(), "Antal afviger fra 5");
    }

    @Test
    void createOrdrelinje3() {
        //TC3
      Ordrelinje ordrelinje = salg5.createOrdrelinje(2, p1);
      assertEquals(p1, ordrelinje.getProdukt(),"Produktet er ikke det samme" );
    }

    @Test
    void createOrdrelinje4() {
        //TC4
        Ordrelinje ordrelinje1 = salg5.createOrdrelinje(2, p1);
        Ordrelinje ordrelinje2 = salg5.createOrdrelinje(3, p2);
        assertEquals(2, salg5.getOrdrelinjer().size(), "Ordrelinjerne er ikke blevet tilføjet");
    }

    @Test
    void createOrdrelinje5() {
        //TC5
        assertThrows(IllegalArgumentException.class, () -> salg5.createOrdrelinje(-1, p1));
    }


    // ----------------------------------------------------------------------------------------------------------

    @Test
    void createRabatBeloeb1() {
        //TC1
        Ordrelinje ordrelinje = salg5.createOrdrelinje(2,p1); //Skal oprettes, ellers giver opret rabat en fejl.
        Rabat rabat = salg5.createRabatBeloeb(20);
        assertEquals(rabat, salg5.getRabat(), "Rabat er ikke blevet sat til salget");
    }

    @Test
    void createRabatBeloeb2() {
        //TC2
        Ordrelinje ordrelinje = salg5.createOrdrelinje(2,p1); //Skal oprettes, ellers giver opret rabat en fejl.
        assertThrows(IllegalArgumentException.class, () ->salg5.createRabatBeloeb(-20));
    }
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void removeOrdrelinje(){
        //TC1
        salg2.removeOrdrelinje(ordrelinje3);
        assertEquals(0, salg2.getOrdrelinjer().size(), "Ordrelinjen er ikke blevet fjernet");
    }


}