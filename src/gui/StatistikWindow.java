package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class StatistikWindow extends Stage {

    private Object o;

    public StatistikWindow(String title, Object o) {
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

    // datepicker

    private final DatePicker dpDateStart = new DatePicker();
    private final DatePicker dpDateSlut = new DatePicker();
    private final DatePicker dpDagsOpgoer = new DatePicker();

    // comboboxes
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


    public StatistikWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        pane.add(lwProGruSalgsSit, 8,3,1,2);
        lwProGruSalgsSit.setPrefWidth(25);
        lwProGruSalgsSit.setPrefHeight(200);
        lwProGruSalgsSit.setScaleX(1.1);

        pane.add(lwAntalKlipPrProduktPeriode, 0, 3, 2, 2);
        lwAntalKlipPrProduktPeriode.setPrefWidth(25);
        lwAntalKlipPrProduktPeriode.setPrefHeight(200);

        pane.add(lwDagsOpgoer, 3, 3, 1, 2);
        lwDagsOpgoer.setPrefWidth(25);
        lwDagsOpgoer.setPrefHeight(200);

        pane.add(lwLejedeUafleveredeProdukter, 6, 3, 1, 3);
        lwLejedeUafleveredeProdukter.setPrefWidth(175);
        lwLejedeUafleveredeProdukter.setPrefHeight(100);

        pane.add(lwBestemtKvittering, 4, 3, 1, 3);
        lwBestemtKvittering.setPrefWidth(200);
        lwBestemtKvittering.setScaleX(1.1);
        lwBestemtKvittering.setPrefHeight(100);

        // Texts

        pane.add(txtPeriodeForKlip, 0, 0);
        pane.add(txtPeriodeForKlipProdukt, 0, 2,2,1);
        pane.add(txtDagsOpgoer, 3, 0);
        pane.add(txtKviteringer, 3, 2);
        pane.add(txtLeje, 6, 2);
        pane.add(txtProGruSalgsSit, 8,0);

        // DatePicker

        pane.add(dpDateSlut, 1, 1);
        dpDateSlut.setPromptText("Slut dato");
        dpDateSlut.setEditable(false);
        dpDateSlut.setPrefWidth(100);

        pane.add(dpDateStart, 0, 1);
        dpDateStart.setPromptText("Start dato");
        dpDateStart.setEditable(false);
        dpDateStart.setPrefWidth(100);

        pane.add(dpDagsOpgoer, 3, 1);
        dpDagsOpgoer.setPromptText("Dato Dagsopgør");
        dpDagsOpgoer.setEditable(false);

        // Textfields

        pane.add(txfSumKlip, 0, 5, 2, 1);
        txfSumKlip.setPromptText("sum:");
        txfSumKlip.setPrefWidth(20);
        txfSumKlip.setEditable(false);

        pane.add(txfSumDagsOpgoer, 3, 5, 1, 1);
        txfSumDagsOpgoer.setPromptText("sum:");
        txfSumDagsOpgoer.setPrefWidth(20);
        txfSumDagsOpgoer.setEditable(false);

        pane.add(txfSumProGruSalgsSit,8,5);
        txfSumProGruSalgsSit.setPromptText("sum:");
        txfSumProGruSalgsSit.setPrefWidth(20);
        txfSumProGruSalgsSit.setEditable(false);
        txfSumProGruSalgsSit.setScaleX(1.1);

        // combobox

        pane.add(cbSalgssituation, 8,1);
        cbSalgssituation.setPromptText("Salgssituation");
        cbSalgssituation.setEditable(false);
        cbSalgssituation.setPrefWidth(125);
        cbSalgssituation.getItems().setAll(Controller.getInstance().getSalgssituationer());
        cbSalgssituation.setScaleX(1.1);

        pane.add(cbProductGruppe, 8,2);
        cbProductGruppe.setPromptText("Produktgruppe");
        cbProductGruppe.setEditable(false);
        cbProductGruppe.setPrefWidth(125);
        cbProductGruppe.getItems().setAll(Controller.getInstance().getProduktGrupper());
        cbProductGruppe.setScaleX(1.1);

    }
}
