package com.map.seatingmapgenerator.controller;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.ClassroomService;
import com.map.seatingmapgenerator.service.SeatingService;
import com.map.seatingmapgenerator.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Comparator;

public class ChartCreation {

    private SeatingService seatingService;

    public void setSeatingService(SeatingService seatingService) {
        this.seatingService = seatingService;

        //initializing student table
        configureStudentTable();
        loadStudents();

        //initializing classroom table
        configureClassroomTable();
        loadClassrooms();
    }

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    private void loadStudents() {
        students.setAll(seatingService.getStudents());
        students.sort(Comparator.comparing(Student::getGrade).thenComparing(Student::getName));
    }

    private final ObservableList<Classroom> classrooms = FXCollections.observableArrayList();

    private void loadClassrooms() {
        classrooms.setAll(seatingService.getClassrooms());
        classrooms.sort(Comparator.comparing(Classroom::getId));
    }


    @FXML
    private void goBack(ActionEvent event) throws IOException {
        SceneManager.switchScene(
                event,
                "/com/map/fxml/startMenu.fxml",
                "Seating Map Generator"
        );
    }
}
