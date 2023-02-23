package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class StatistikPane extends GridPane {

    // datepicker
    private final DatePicker dpDateStart = new DatePicker();
    private final DatePicker dpDateSlut = new DatePicker();
    private final DatePicker dpDagsOpgoer = new DatePicker();

    // combobox
    private final ComboBox<Salgssituation> cbSalgssituation = new ComboBox();
    private final ComboBox<ProduktGruppe> cbProductGruppe = new ComboBox();



    // listviews
    private final ListView<String> lwAntalKlipPrProduktPeriode = new ListView();
    private final ListView<Salg> lwDagsOpgoer = new ListView();
    private final ListView<Leje> lwLejedeUafleveredeProdukter = new ListView();
    private final ListView<Ordrelinje> lwBestemtKvittering = new ListView();
    private final ListView<String> lwProGruSalgsSit = new ListView();


    // texts
    private final Text txtPeriodeForKlip = new Text("Klip");
    private final Text txtPeriodeForKlipProdukt = new Text("Antal klip pr produkt periode:");
    private final Text txtDagsOpgoer = new Text("Dagsopgør");
    private final Text txtKviteringer = new Text("Kviteringer:");
//    private final Text txtUdeje = new Text("Udlejede Produkter:");
    private final Text txtLeje = new Text("Leje");
    private final Text txtProGruSalgsSit = new Text("Antal solgte produkter");


    // textfields
    private final TextField txfSumDagsOpgoer = new TextField();
    private final TextField txfSumKlip = new TextField();
    private final TextField txfSumProGruSalgsSit = new TextField();


    public StatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(lwProGruSalgsSit, 8,3,1,2);
        lwProGruSalgsSit.setPrefWidth(25);
        lwProGruSalgsSit.setPrefHeight(200);
        lwProGruSalgsSit.setScaleX(1.1);

        this.add(lwAntalKlipPrProduktPeriode, 0, 3, 2, 2);
        lwAntalKlipPrProduktPeriode.setPrefWidth(25);
        lwAntalKlipPrProduktPeriode.setPrefHeight(200);

        this.add(lwDagsOpgoer, 3, 3, 1, 2);
        lwDagsOpgoer.setPrefWidth(25);
        lwDagsOpgoer.setPrefHeight(200);
        ChangeListener<Salg> listenerBK = (ov, oldString, newString) -> this.selectionChangedKvittering();
        lwDagsOpgoer.getSelectionModel().selectedItemProperty().addListener(listenerBK);


        this.add(lwLejedeUafleveredeProdukter, 6, 3, 1, 3);
        lwLejedeUafleveredeProdukter.setPrefWidth(175);
        lwLejedeUafleveredeProdukter.setPrefHeight(100);
        lwLejedeUafleveredeProdukter.getItems().setAll(Controller.getInstance().getIkkeAfleveredeUdlejedeProdukter());
        lwLejedeUafleveredeProdukter.setOnMouseClicked(e -> eventLeje());

        this.add(lwBestemtKvittering, 4, 3, 1, 3);
        lwBestemtKvittering.setPrefWidth(200);
        lwBestemtKvittering.setScaleX(1.1);
        lwBestemtKvittering.setPrefHeight(100);

        // Texts

        this.add(txtPeriodeForKlip, 0, 0);
        this.add(txtPeriodeForKlipProdukt, 0, 2,2,1);
        this.add(txtDagsOpgoer, 3, 0);
        this.add(txtKviteringer, 3, 2);
        this.add(txtLeje, 6, 2);
        this.add(txtProGruSalgsSit, 8,0);

        // DatePicker

        this.add(dpDateSlut, 1, 1);
        dpDateSlut.setPromptText("Slut dato");
        dpDateSlut.setEditable(false);
        dpDateSlut.setPrefWidth(100);
        ChangeListener<LocalDate> listenerDSl = (ov, oldString, newString) -> this.selectionChangedKlip();
        dpDateSlut.valueProperty().addListener(listenerDSl);

        this.add(dpDateStart, 0, 1);
        dpDateStart.setPromptText("Start dato");
        dpDateStart.setEditable(false);
        dpDateStart.setPrefWidth(100);
        ChangeListener<LocalDate> listenerDSt = (ov, oldString, newString) -> this.selectionChangedKlip();
        dpDateStart.valueProperty().addListener(listenerDSt);


        this.add(dpDagsOpgoer, 3, 1);
        dpDagsOpgoer.setPromptText("Dato Dagsopgør");
        dpDagsOpgoer.setEditable(false);
        ChangeListener<LocalDate> listenerDO = (ov, oldString, newString) -> this.selectionChangedDagsOpgoer();
        dpDagsOpgoer.valueProperty().addListener(listenerDO);

        // Textfields


        this.add(txfSumKlip, 0, 5, 2, 1);
        txfSumKlip.setPromptText("sum:");
        txfSumKlip.setPrefWidth(20);
        txfSumKlip.setEditable(false);

        this.add(txfSumDagsOpgoer, 3, 5, 1, 1);
        txfSumDagsOpgoer.setPromptText("sum:");
        txfSumDagsOpgoer.setPrefWidth(20);
        txfSumDagsOpgoer.setEditable(false);

        this.add(txfSumProGruSalgsSit,8,5);
        txfSumProGruSalgsSit.setPromptText("sum:");
        txfSumProGruSalgsSit.setPrefWidth(20);
        txfSumProGruSalgsSit.setEditable(false);
        txfSumProGruSalgsSit.setScaleX(1.1);

        // combobox

        this.add(cbSalgssituation, 8,1);
        cbSalgssituation.setPromptText("Salgssituation");
        cbSalgssituation.setEditable(false);
        cbSalgssituation.setPrefWidth(125);
        ChangeListener<Salgssituation> listenerSS= (ov, oldString, newString) -> this.selectionChangedSSogPG();
        cbSalgssituation.valueProperty().addListener(listenerSS);
        cbSalgssituation.getItems().setAll(Controller.getInstance().getSalgssituationer());
        cbSalgssituation.setScaleX(1.1);


        this.add(cbProductGruppe, 8,2);
        cbProductGruppe.setPromptText("Produktgruppe");
        cbProductGruppe.setEditable(false);
        cbProductGruppe.setPrefWidth(125);
        ChangeListener<ProduktGruppe> listenerPG= (ov, oldString, newString) -> this.selectionChangedSSogPG();
        cbProductGruppe.valueProperty().addListener(listenerPG);
        cbProductGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());
        cbProductGruppe.setScaleX(1.1);

    }

// funktioner

    private void selectionChangedKvittering() {
        Salg selected = lwDagsOpgoer.getSelectionModel().getSelectedItem();
        if (selected != null)
            lwBestemtKvittering.getItems().setAll(selected.getOrdrelinjer());
        }

    private void selectionChangedSSogPG() {
        Salgssituation selectedSS = cbSalgssituation.getSelectionModel().getSelectedItem();
        ProduktGruppe selectedPG = cbProductGruppe.getSelectionModel().getSelectedItem();
        if (selectedPG != null&&selectedSS != null)
            lwProGruSalgsSit.getItems().setAll(Controller.getInstance().getProduktSolgtPrSalgssituationPrProduktGruppe(selectedSS,selectedPG));
    }



    private void selectionChangedDagsOpgoer() {
        if (dpDagsOpgoer.getValue() != null) {
            lwDagsOpgoer.getItems().setAll(Controller.getInstance().getDagsKvitteringer(dpDagsOpgoer.getValue()));
            txfSumDagsOpgoer.setText("sum: " + Controller.getInstance().getDagsopgoer(dpDagsOpgoer.getValue()));
        }
    }

    //TODO
    private void selectionChangedKlip() {
        if (dpDateStart.getValue() != null && dpDateSlut.getValue() != null) {
            txfSumKlip.setText("Antal: " + Controller.getInstance().getAntalBrugteKlip(dpDateStart.getValue(), dpDateSlut.getValue()));
            if (Controller.getInstance().getPrProduktAntalKlipIPeriode(dpDateStart.getValue(), dpDateSlut.getValue()) != null) {
                lwAntalKlipPrProduktPeriode.getItems().setAll(Controller.getInstance().getPrProduktAntalKlipIPeriode(dpDateStart.getValue(), dpDateSlut.getValue()));
            }

        }
    }

    private void eventLeje() {
        lwLejedeUafleveredeProdukter.getItems().setAll(Controller.getInstance().getIkkeAfleveredeUdlejedeProdukter());
    }

}
