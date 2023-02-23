package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SalgWindow extends Stage {
    private Salg salg;

    public SalgWindow(String title, Salg salg) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.salg = salg;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public SalgWindow(String title) {
        this(title, null);
    }

    // -------------------------------------------------------------------------

    // listviews
    private final ListView<Pris> lwSalgsSituationProdukter = new ListView();
    private final ListView<Ordrelinje> lwOrdrelinje = new ListView();

    // buttons
    private final Button btnTilføjOrdrelinje = new Button();
    private final Button btnFjernOrdrelinje = new Button();
    private final Button btnRabat = new Button();
    private final Button btnBetal = new Button();
    private final Button btnLeje = new Button();

    // texts
    private final Text txtAarhusBryghus = new Text("Aarhus Bryghus Produkter:");
    private final Text txtIndKurvOrdrelinje = new Text("Indkøbskurv:");

    // textfields
    private final TextField txfAntalProdukter = new TextField();
    private final TextField txfSumOrdrelinje = new TextField();
    private final TextField txfBeloeb = new TextField();

    // combobox
    private final ComboBox<Salgssituation> cbSalgssituation = new ComboBox();
    private final ComboBox<Betaling> cbBetaling = new ComboBox();


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Listviews

        pane.add(lwSalgsSituationProdukter, 0, 2, 2, 2);
        lwSalgsSituationProdukter.setPrefWidth(50);
        lwSalgsSituationProdukter.setPrefHeight(150);

        pane.add(lwOrdrelinje, 2, 2, 3, 1);
        lwOrdrelinje.setPrefWidth(50);
        lwOrdrelinje.setPrefHeight(150);

        // Texts

        pane.add(txtAarhusBryghus,0,1);
        pane.add(txtIndKurvOrdrelinje,2,1);


        // Buttons

        pane.add(btnTilføjOrdrelinje, 1, 4);
        btnTilføjOrdrelinje.setText("Tilføj");
        btnTilføjOrdrelinje.setPrefWidth(60);

        pane.add(btnFjernOrdrelinje, 2, 3);
        btnFjernOrdrelinje.setText("Fjern");
        btnFjernOrdrelinje.setPrefWidth(60);

        pane.add(btnRabat, 2, 4);
        btnRabat.setText("Rabat");
        btnRabat.setPrefWidth(60);

        pane.add(btnBetal, 3, 4);
        btnBetal.setText("Betal");
        btnBetal.setPrefWidth(60);

        pane.add(btnLeje, 4, 1);
        btnLeje.setText("Leje");
        btnLeje.setPrefWidth(60);

        // TextFields

        pane.add(txfAntalProdukter,0,4);
        txfAntalProdukter.setPromptText("Antal:");
        txfAntalProdukter.setPrefWidth(100);

        pane.add(txfBeloeb, 3, 4);
        txfBeloeb.setPromptText("Klip / Beløb");
        txfBeloeb.setPrefWidth(60);

        pane.add(txfSumOrdrelinje,3,3);
        txfSumOrdrelinje.setPromptText("sum:");
        txfSumOrdrelinje.setPrefWidth(60);
        txfSumOrdrelinje.setDisable(true);

        // ComboBox
        pane.add(cbSalgssituation,0,0);
        cbSalgssituation.setPromptText("Prislister");

        pane.add(cbBetaling,7,7);
        cbBetaling.setPromptText("Betalingsform");
        cbBetaling.setPrefWidth(90);

    }
}
