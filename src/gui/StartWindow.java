package gui;


import application.controller.Controller;
import application.model.Ordrelinje;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class StartWindow extends Application {

    private Controller controller;

    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Aarhus Bryghus IT-System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        controller = Controller.getInstance();
        controller.loadStorage();
        TabPane tabPane = new TabPane();
        this.initTapPane(tabPane);
        pane.setCenter(tabPane);
    }
    private void initTapPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabSalg = new Tab("Salg");
        tabPane.getTabs().add(tabSalg);

        SalgPane salgPane = new SalgPane();
        tabSalg.setContent(salgPane);

        Tab tabSystemObjek = new Tab("System Objekter");
        tabPane.getTabs().add(tabSystemObjek);

        SystemObjektPane systemObjektPane = new SystemObjektPane();
        tabSystemObjek.setContent(systemObjektPane);

        Tab tabStatistik = new Tab("Statistik");
        tabPane.getTabs().add(tabStatistik);

        StatistikPane statistikPane = new StatistikPane();
        tabStatistik.setContent(statistikPane);
    }
    @Override
    public void stop(){Controller.getInstance().saveStorage();}
}
