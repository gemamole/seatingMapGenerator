package com.map.seatingmapgenerator.controller;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.Grade;
import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.ClassroomService;
import com.map.seatingmapgenerator.service.SeatingService;
import com.map.seatingmapgenerator.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartCreation {

    private SeatingService seatingService;

    public void setSeatingService(SeatingService seatingService) {
        this.seatingService = seatingService;

        //initializing student table
        configureStudentTable();

        //initializing classroom table
        configureClassroomTable();
    }

    @FXML
    private TreeTableView<Object> studentTable;
    @FXML
    private TreeTableColumn<Student, CheckBox> selectedStudentColumn;
    @FXML
    private TreeTableColumn<Classroom, Integer> studentIdColumn;
    @FXML
    private TreeTableColumn<Classroom, String> nameColumn;

    @FXML
    private void configureStudentTable() {

        TreeItem<Object> root = new TreeItem<>("Students");
        root.setExpanded(true);

        //Group students by grade
        Map<Grade, List<Student>> studentsByGrade =
                seatingService.getStudents().stream()
                        .collect(Collectors.groupingBy(Student::getGrade));

        //Create grade nodes
        for (Map.Entry<Grade, List<Student>> entry : studentsByGrade.entrySet()) {

            TreeItem<Object> gradeNode =
                    new TreeItem<>("Grade " + entry.getKey());

            // Add students under the grade
            for (Student student : entry.getValue()) {
                gradeNode.getChildren()
                        .add(new TreeItem<>(student));
            }

            root.getChildren().add(gradeNode);
        }

        studentTable.setRoot(root);
        studentTable.setShowRoot(false);
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
