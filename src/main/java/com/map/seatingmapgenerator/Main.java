package com.map.seatingmapgenerator;

import com.map.seatingmapgenerator.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/map/fxml/startMenu.fxml")
        );

        Font.loadFont(
                getClass().getResourceAsStream("/fonts/GoogleSansFlex-VariableFont_GRAD,ROND,opsz,slnt,wdth,wght.ttf"),12
        );

        Scene scene = new Scene(loader.load(), 900, 600);

        scene.getStylesheets().add(
                getClass().getResource("/css/app.css").toExternalForm());
        stage.setTitle("Seating Map Generator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Application is closing...");

        // cleanup code goes here
        // save files, close database, stop threads, etc.
    }

    public static void main(String[] args) {
        Database.initialize();

        launch(args);
    }
}