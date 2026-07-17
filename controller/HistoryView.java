package com.map.seatingmapgenerator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HistoryView {
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/startMenu.fxml",
                "Seating Map Generator"
        );
    }
}
