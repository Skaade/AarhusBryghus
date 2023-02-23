package gui;

import application.controller.Controller;
import application.model.Pris;
import application.model.Produkt;
import application.model.ProduktGruppe;
import application.model.Salgssituation;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.Serializable;

public class SystemObjektPane extends GridPane{

    private Controller controller;

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

    public SystemObjektPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Listviews
        this.add(lwSalgsSituation, 0, 1, 3, 1);
        lwSalgsSituation.setPrefWidth(50);
        lwSalgsSituation.setPrefHeight(150);
        ChangeListener<Salgssituation> listenerSS = (ov, oldString, newString) -> this.selectionChangedSalgssituation();
        lwSalgsSituation.getSelectionModel().selectedItemProperty().addListener(listenerSS);
        lwSalgsSituation.getItems().setAll(Controller.getInstance().getSalgssituationer());

        // Buttons
        this.add(btnSalgsSituationCreate, 2, 2);
        btnSalgsSituationCreate.setText("Create");
        btnSalgsSituationCreate.setPrefWidth(60);
        btnSalgsSituationCreate.setOnAction(e -> createSalgssituation());


        this.add(btnSalgsSituationUpdate, 2, 3);
        btnSalgsSituationUpdate.setText("Update");
        btnSalgsSituationUpdate.setPrefWidth(60);
        btnSalgsSituationUpdate.setOnAction(e -> updateSalgssituation());


        this.add(btnSalgsSituationDelete, 0, 3);
        btnSalgsSituationDelete.setText("Delete");
        btnSalgsSituationDelete.setPrefWidth(60);
        btnSalgsSituationDelete.setOnAction(e -> deleteSalgssituation());


        // Texts

        this.add(txtSalgsSituation, 0, 0);

        // Textfields

        this.add(txfSalgsSituationNavn, 0, 2);
        txfSalgsSituationNavn.setPromptText("Navn:");
        txfSalgsSituationNavn.setPrefWidth(100);

        this.add(txfSalgsSituationBeskrivelse, 1, 2);
        txfSalgsSituationBeskrivelse.setPromptText("Beskrivelse:");
        txfSalgsSituationBeskrivelse.setPrefWidth(100);

//Pris

        // Listviews
        this.add(lwPris, 0, 6, 3, 1);
        lwPris.setPrefWidth(50);
        lwPris.setPrefHeight(150);
        ChangeListener<Pris> listenerPri = (ov, oldString, newString) -> this.selectionChangedPris();
        lwPris.getSelectionModel().selectedItemProperty().addListener(listenerPri);


        // Buttons
        this.add(btnPrisCreate, 2, 7);
        btnPrisCreate.setText("Create");
        btnPrisCreate.setOnAction(e -> createPris());
        btnPrisCreate.setPrefWidth(60);


        this.add(btnPrisUpdate, 2, 8);
        btnPrisUpdate.setText("Update");
        btnPrisUpdate.setOnAction(e -> updatePris());
        btnPrisUpdate.setPrefWidth(60);

        this.add(btnPrisDelete, 0, 8);
        btnPrisDelete.setText("Delete");
        btnPrisDelete.setOnAction(e -> deletePris());
        btnPrisDelete.setPrefWidth(60);

        // Texts

        this.add(txtPris, 0, 5);

        // Textfields

        this.add(txfPrisBeløb, 0, 7);
        txfPrisBeløb.setPromptText("Beløb:");
        txfPrisBeløb.setPrefWidth(100);
//        ChangeListener<> listenerPG = (ov, oldString, newString) -> this.selectionChangedProduktGruppe();
//        txfPrisBeløb.getSelectionModel().selectedItemProperty().addListener(listenerPG);


        this.add(txfPrisKlip, 1, 7);
        txfPrisKlip.setPromptText("Klip:");
        txfPrisKlip.setPrefWidth(100);


//ProduktGruppe

        //Listview
        this.add(lwProduktGruppe, 3, 1, 3, 1);
        lwProduktGruppe.setPrefWidth(50);
        lwProduktGruppe.setPrefHeight(150);
        ChangeListener<ProduktGruppe> listenerPG = (ov, oldString, newString) -> this.selectionChangedProduktGruppe();
        lwProduktGruppe.getSelectionModel().selectedItemProperty().addListener(listenerPG);
        lwProduktGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());


        // Buttons
        this.add(btnProduktGruppeCreate, 5, 2);
        btnProduktGruppeCreate.setText("Create");
        btnProduktGruppeCreate.setPrefWidth(60);
        btnProduktGruppeCreate.setOnAction(e -> createProduktGruppe());

        this.add(btnProduktGruppeUpdate, 5, 3);
        btnProduktGruppeUpdate.setText("Update");
        btnProduktGruppeUpdate.setPrefWidth(60);
        btnProduktGruppeUpdate.setOnAction(e -> updateProduktGruppe());

        this.add(btnProduktGruppeDelete, 3, 3);
        btnProduktGruppeDelete.setText("Delete");
        btnProduktGruppeDelete.setPrefWidth(60);
        btnProduktGruppeDelete.setOnAction(e -> deleteProduktGruppe());


        // Texts

        this.add(txtProduktGruppe, 3, 0);

        // Textfields

        this.add(txfProduktGruppeNavn, 3, 2, 2, 1);
        txfProduktGruppeNavn.setPromptText("Navn:");

//Produkt

        //Listview
        this.add(lwProdukt, 3, 6, 3, 1);
        lwProdukt.setPrefWidth(50);
        lwProdukt.setPrefHeight(150);
        ChangeListener<Produkt> listenerPro = (ov, oldString, newString) -> this.selectionChangedProdukt();
        lwProdukt.getSelectionModel().selectedItemProperty().addListener(listenerPro);



        // Buttons
        this.add(btnProduktCreate, 5, 7);
        btnProduktCreate.setText("Create");
        btnProduktCreate.setPrefWidth(60);
        btnProduktCreate.setOnAction(e -> createProdukt());


        this.add(btnProduktUpdate, 5, 8);
        btnProduktUpdate.setText("Update");
        btnProduktUpdate.setPrefWidth(60);
        btnProduktUpdate.setOnAction(e -> updateProdukt());


        this.add(btnProduktDelete, 3, 8);
        btnProduktDelete.setText("Delete");
        btnProduktDelete.setPrefWidth(60);
        btnProduktDelete.setOnAction(e -> deleteProdukt());


        // Texts

        this.add(txtProdukt, 3, 5);

        // Textfields

        this.add(txfProduktNavn, 3, 7);
        txfProduktNavn.setPromptText("Navn:");
        txfProduktNavn.setPrefWidth(100);


        this.add(txfProduktBeskrivelse, 4, 7);
        txfProduktBeskrivelse.setPromptText("Beskrivelse:");
        txfProduktBeskrivelse.setPrefWidth(100);
    }

// ProduktGruppe funktioner

    private void selectionChangedProduktGruppe() {
        ProduktGruppe selected = lwProduktGruppe.getSelectionModel().getSelectedItem();
        txfProduktGruppeNavn.setText(selected.getNavn());
        lwProdukt.getItems().setAll(selected.getProdukter());

    }

    private void createProduktGruppe() {
        if (txfProduktGruppeNavn.getText() != null) {
            Controller.getInstance().createProduktGruppe(txfProduktGruppeNavn.getText());
            lwProduktGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());
        }
    }

    private void updateProduktGruppe() {
        ProduktGruppe selected = lwProduktGruppe.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //TODO brug update
            selected.setNavn(txfProduktGruppeNavn.getText());
            lwProduktGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());
        }
    }

    private void deleteProduktGruppe() {
        ProduktGruppe selected = lwProduktGruppe.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Controller.getInstance().removeProduktGruppe(selected);
            lwProduktGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());
        }
    }

// Salgssituation funktioner

    private void selectionChangedSalgssituation() {
        Salgssituation selected = lwSalgsSituation.getSelectionModel().getSelectedItem();
        txfSalgsSituationNavn.setText(selected.getNavn());
        txfSalgsSituationBeskrivelse.setText(selected.getBeskrivelse());
        //TODO (Tjek om given produkt har pris til salgsituation)
        lwPris.getItems().setAll(selected.getPriser());
    }

    private void createSalgssituation() {
        if (txfSalgsSituationNavn.getText() != null && txfSalgsSituationBeskrivelse.getText() != null) {
            Controller.getInstance().createSalgssituation(txfSalgsSituationNavn.getText(), txfSalgsSituationBeskrivelse.getText());
            lwSalgsSituation.getItems().setAll(Controller.getInstance().getSalgssituationer());
        }
    }

    private void updateSalgssituation() {
        Salgssituation selected = lwSalgsSituation.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //TODO brug update
            selected.setNavn(txfSalgsSituationNavn.getText());
            selected.setBeskrivelse(txfSalgsSituationBeskrivelse.getText());
            lwSalgsSituation.getItems().setAll(Controller.getInstance().getSalgssituationer());
        }
    }

    private void deleteSalgssituation() {
        Salgssituation selected = lwSalgsSituation.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Controller.getInstance().removeSalgssituation(selected);
            lwSalgsSituation.getItems().setAll(Controller.getInstance().getSalgssituationer());
        }
    }

// Produkt funktioner

    private void selectionChangedProdukt() {
        Produkt selected = lwProdukt.getSelectionModel().getSelectedItem();
        if (selected !=null) {
            txfProduktNavn.setText(selected.getNavn());
            txfProduktBeskrivelse.setText(selected.getBeskrivelse());
            //TODO (Tjek om given produkt har pris til salgsituation)
//        lwProdukt.getItems().setAll(selected.getProdukter());
        }
    }

    private void createProdukt() {
        ProduktGruppe selected = lwProduktGruppe.getSelectionModel().getSelectedItem();
        if (txfProduktNavn.getText() != null && txfProduktBeskrivelse.getText() != null && selected !=null) {
            selected.createProdukt(txfProduktNavn.getText(), txfProduktBeskrivelse.getText());
            lwProdukt.getItems().setAll(selected.getProdukter());
        }
    }

    private void updateProdukt() {
        Produkt selected = lwProdukt.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //TODO brug update
            selected.setNavn(txfProduktNavn.getText());
            selected.setBeskrivelse(txfProduktBeskrivelse.getText());
            lwProdukt.getItems().setAll(selected.getProduktGruppe().getProdukter());
        }
    }

    private void deleteProdukt() {
        Produkt selected = lwProdukt.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.getProduktGruppe().removeProdukt(selected);
            lwProdukt.getItems().setAll(selected.getProduktGruppe().getProdukter());
        }
    }

// Pris funktioner

    private void selectionChangedPris() {
        Pris selected = lwPris.getSelectionModel().getSelectedItem();
        txfPrisBeløb.setText(selected.getBeloeb() + "");
        txfPrisKlip.setText(selected.getAntalKlip() + "");
    }

    private void createPris() {
        Salgssituation selectedSS = lwSalgsSituation.getSelectionModel().getSelectedItem();
        Produkt selectedPro = lwProdukt.getSelectionModel().getSelectedItem();
        if (selectedSS != null && selectedPro != null) {
            if (txfPrisBeløb.getText() != null && txfPrisKlip.getText().isEmpty()) {
                selectedSS.createPris(Double.parseDouble(txfPrisBeløb.getText()), selectedPro);
                lwPris.getItems().setAll(selectedSS.getPriser());}
            else if (txfPrisBeløb.getText() != null && txfPrisKlip.getText() != null) {
                selectedSS.createPris(Double.parseDouble(txfPrisBeløb.getText()), Integer.parseInt(txfPrisKlip.getText()), selectedPro);
                lwPris.getItems().setAll(selectedSS.getPriser());}
            }
        }
    

    private void updatePris() {
        Pris selected = lwPris.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //TODO brug update
            selected.setBeloeb(Double.parseDouble(txfPrisBeløb.getText()));
            if (txfPrisKlip.getText() != null) {
                selected.setAntalKlip(Integer.parseInt(txfProduktBeskrivelse.getText()));
            } else {
                selected.setAntalKlip(0);
            }
            lwPris.getItems().setAll(selected.getProdukt().getPriser());
        }
    }

    private void deletePris() {
        Pris selected = lwPris.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.getSalgssituation().removePris(selected);
            lwPris.getItems().setAll(selected.getSalgssituation().getPriser());
        }
    }

}

