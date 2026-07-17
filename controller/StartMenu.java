package com.map.seatingmapgenerator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartMenu {
    @FXML
    private void generateMap(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/seatingGenerator.fxml",
                "Generate chart"
        );
    }

    @FXML
    private void openMaps(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/historyView.fxml",
                "History view"
        );
    }

    @FXML
    private void manageStudents(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/studentManagement.fxml",
                "Student management"
        );
    }

    @FXML
    private void manageRooms(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/classroomManagement.fxml",
                "Classroom management"
        );
    }
}
