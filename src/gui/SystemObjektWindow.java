package gui;

import application.controller.Controller;
import application.model.Pris;
import application.model.Produkt;
import application.model.ProduktGruppe;
import application.model.Salgssituation;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.Serializable;

public class SystemObjektWindow extends Stage{

    private Object o;
    private Controller controller;

    public SystemObjektWindow(String title, Object o) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.o = o;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public SystemObjektWindow(String title) {
        this(title, null);
    }

    private final ListView<Salgssituation> lwSalgsSituation = new ListView();

    // buttons
    private final Button btnSalgsSituationCreate = new Button();
    private final Button btnSalgsSituationUpdate = new Button();
    private final Button btnSalgsSituationDelete = new Button();

    // texts

    private final Text txtSalgsSituation = new Text("Salgssituation:");

    // textfields

    private final TextField txfSalgsSituationNavn = new TextField();
    private final TextField txfSalgsSituationBeskrivelse = new TextField();

    // - Produkt Gruppe

    // listviews
    private final ListView<ProduktGruppe> lwProduktGruppe = new ListView();

    // buttons
    private final Button btnProduktGruppeCreate = new Button();
    private final Button btnProduktGruppeUpdate = new Button();
    private final Button btnProduktGruppeDelete = new Button();

    // texts

    private final Text txtProduktGruppe = new Text("Produkt Gruppe:");

    // textfields

    private final TextField txfProduktGruppeNavn = new TextField();

    // - Pris

    // listviews
    private final ListView<Pris> lwPris = new ListView();

    // buttons
    private final Button btnPrisCreate = new Button();
    private final Button btnPrisUpdate = new Button();
    private final Button btnPrisDelete = new Button();

    // texts

    private final Text txtPris = new Text("Priser:");

    // textfields

    private final TextField txfPrisBeløb = new TextField();
    private final TextField txfPrisKlip = new TextField();

    // - Produkt

    // listviews
    private final ListView<Produkt> lwProdukt = new ListView();

    // buttons
    private final Button btnProduktCreate = new Button();
    private final Button btnProduktUpdate = new Button();
    private final Button btnProduktDelete = new Button();

    // texts

    private final Text txtProdukt = new Text("Produkter:");

    // textfields

    private final TextField txfProduktNavn = new TextField();
    private final TextField txfProduktBeskrivelse = new TextField();


    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(50));

        // set horizontal gap between components
        pane.setHgap(10);

        // set vertical gap between components
        pane.setVgap(10);

//Salgssituation

        // Listviews
        pane.add(lwSalgsSituation, 0, 1, 3, 1);
        lwSalgsSituation.setPrefWidth(50);
        lwSalgsSituation.setPrefHeight(150);


        // Buttons
        pane.add(btnSalgsSituationCreate, 2, 2);
        btnSalgsSituationCreate.setText("Create");
        btnSalgsSituationCreate.setPrefWidth(60);


        pane.add(btnSalgsSituationUpdate, 2, 3);
        btnSalgsSituationUpdate.setText("Update");
        btnSalgsSituationUpdate.setPrefWidth(60);


        pane.add(btnSalgsSituationDelete, 0, 3);
        btnSalgsSituationDelete.setText("Delete");
        btnSalgsSituationDelete.setPrefWidth(60);


        // Texts

        pane.add(txtSalgsSituation, 0, 0);

        // Textfields

        pane.add(txfSalgsSituationNavn, 0, 2);
        txfSalgsSituationNavn.setPromptText("Navn:");
        txfSalgsSituationNavn.setPrefWidth(100);

        pane.add(txfSalgsSituationBeskrivelse, 1, 2);
        txfSalgsSituationBeskrivelse.setPromptText("Beskrivelse:");
        txfSalgsSituationBeskrivelse.setPrefWidth(100);

//Pris

        // Listviews
        pane.add(lwPris, 0, 6, 3, 1);
        lwPris.setPrefWidth(50);
        lwPris.setPrefHeight(150);

        // Buttons
        pane.add(btnPrisCreate, 2, 7);
        btnPrisCreate.setText("Create");
        btnPrisCreate.setPrefWidth(60);


        pane.add(btnPrisUpdate, 2, 8);
        btnPrisUpdate.setText("Update");
        btnPrisUpdate.setPrefWidth(60);

        pane.add(btnPrisDelete, 0, 8);
        btnPrisDelete.setText("Delete");
        btnPrisDelete.setPrefWidth(60);

        // Texts

        pane.add(txtPris, 0, 5);

        // Textfields

        pane.add(txfPrisBeløb, 0, 7);
        txfPrisBeløb.setPromptText("Beløb:");
        txfPrisBeløb.setPrefWidth(100);
//        ChangeListener<> listenerPG = (ov, oldString, newString) -> this.selectionChangedProduktGruppe();
//        txfPrisBeløb.getSelectionModel().selectedItemProperty().addListener(listenerPG);


        pane.add(txfPrisKlip, 1, 7);
        txfPrisKlip.setPromptText("Klip:");
        txfPrisKlip.setPrefWidth(100);


//ProduktGruppe

        //Listview
        pane.add(lwProduktGruppe, 3, 1, 3, 1);
        lwProduktGruppe.setPrefWidth(50);
        lwProduktGruppe.setPrefHeight(150);

        // Buttons
        pane.add(btnProduktGruppeCreate, 5, 2);
        btnProduktGruppeCreate.setText("Create");
        btnProduktGruppeCreate.setPrefWidth(60);

        pane.add(btnProduktGruppeUpdate, 5, 3);
        btnProduktGruppeUpdate.setText("Update");
        btnProduktGruppeUpdate.setPrefWidth(60);

        pane.add(btnProduktGruppeDelete, 3, 3);
        btnProduktGruppeDelete.setText("Delete");
        btnProduktGruppeDelete.setPrefWidth(60);


        // Texts

        pane.add(txtProduktGruppe, 3, 0);

        // Textfields

        pane.add(txfProduktGruppeNavn, 3, 2, 2, 1);
        txfProduktGruppeNavn.setPromptText("Navn:");

//Produkt

        //Listview
        pane.add(lwProdukt, 3, 6, 3, 1);
        lwProdukt.setPrefWidth(50);
        lwProdukt.setPrefHeight(150);

        // Buttons
        pane.add(btnProduktCreate, 5, 7);
        btnProduktCreate.setText("Create");
//            btnSalgsSituationCreate.setOnAction(e -> SalgsSituationCreate());
        btnProduktCreate.setPrefWidth(60);



        pane.add(btnProduktUpdate, 5, 8);
        btnProduktUpdate.setText("Update");
        btnProduktUpdate.setPrefWidth(60);


        pane.add(btnProduktDelete, 3, 8);
        btnProduktDelete.setText("Delete");
        btnProduktDelete.setPrefWidth(60);


        // Texts

        pane.add(txtProdukt, 3, 5);

        // Textfields

        pane.add(txfProduktNavn, 3, 7);
        txfProduktNavn.setPromptText("Navn:");
        txfProduktNavn.setPrefWidth(100);


        pane.add(txfProduktBeskrivelse, 4, 7);
        txfProduktBeskrivelse.setPromptText("Beskrivelse:");
        txfProduktBeskrivelse.setPrefWidth(100);
    }

}
