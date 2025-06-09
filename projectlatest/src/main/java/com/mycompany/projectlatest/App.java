/*package com.mycompany.projectlatest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create TabPane
        TabPane tabPane = new TabPane();

        // Admin Tab
        Tab adminTab = new Tab("Admin");
        VBox adminBox = new VBox(10);
        adminBox.getChildren().add(new Label("Admin Panel Content Here"));
        adminTab.setContent(adminBox);
        adminTab.setClosable(false);

        // Zakat Payer Tab
        Tab zakatPayerTab = new Tab("Zakat Payer");
        VBox zakatPayerBox = new VBox(10);
        zakatPayerBox.getChildren().add(new Label("Zakat Payer Content Here"));
        zakatPayerTab.setContent(zakatPayerBox);
        zakatPayerTab.setClosable(false);

        // Zakat Application Tab
        Tab zakatApplicationTab = new Tab("Zakat Application");
        VBox zakatApplicationBox = new VBox(10);
        zakatApplicationBox.getChildren().add(new Label("Zakat Application Content Here"));
        zakatApplicationTab.setContent(zakatApplicationBox);
        zakatApplicationTab.setClosable(false);

        // Data Storage Tab
        Tab dataStorageTab = new Tab("Data Storage");
        VBox dataStorageBox = new VBox(10);
        dataStorageBox.getChildren().add(new Label("Data Storage Content Here"));
        dataStorageTab.setContent(dataStorageBox);
        dataStorageTab.setClosable(false);

        // Add tabs to tabPane
        tabPane.getTabs().addAll(adminTab, zakatPayerTab, zakatApplicationTab, dataStorageTab);

        // Scene and Stage
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zakat Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/