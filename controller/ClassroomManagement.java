package com.map.seatingmapgenerator.controller;

import com.map.seatingmapgenerator.controller.dialogs.*;
import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.ClassroomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Comparator;

public class ClassroomManagement {

    private ClassroomService classroomService;

    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
        configureTable();
        configureSearch();
        configureSelection();
        loadClassrooms();
    }

    private final ObservableList<Classroom> classrooms = FXCollections.observableArrayList();

    private final FilteredList<Classroom> filtered = new FilteredList<>(classrooms, p -> true);

    private void loadClassrooms() {
        classrooms.setAll(classroomService.getClassrooms());
        classrooms.sort(Comparator.comparing(Classroom::getId));
    }

    @FXML
    private TableView<Classroom> classroomTable;
    @FXML
    private TableColumn<Classroom, String> idColumn;
    @FXML
    private TableColumn<Classroom, Integer> rowColumn;
    @FXML
    private TableColumn<Classroom, Integer> columnColumn;
    @FXML
    private TableColumn<Classroom, Integer> capacityColumn;

    @FXML
    private void configureTable() {

        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty("Sala " +
                        cellData.getValue().getId()
                )
        );

        rowColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getRow()
                ).asObject()
        );

        columnColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getColumn()
                ).asObject()
        );

        capacityColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getMaxStudents()
                ).asObject()
        );

        classroomTable.setItems(filtered);
    }

    @FXML
    private TextField searchField;

    @FXML
    private void configureSearch() {

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {

            String keyword = newVal.toLowerCase().trim();

            filtered.setPredicate(classroom -> {

                if (keyword.isEmpty()) {
                    return true;
                }

                return String.valueOf(classroom.getId()).contains(keyword);
            });
        });
    }

    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @FXML
    private void configureSelection() {
        editButton.disableProperty().bind(
                classroomTable.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        removeButton.disableProperty().bind(
                classroomTable.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );
    }

    @FXML
    private void onAdd() throws IOException {
        Classroom result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/classroomDialog.fxml",
                "Add Classroom",
                controller -> {
                    ClassroomDialogController c = (ClassroomDialogController) controller;
                    c.setClassroomService(classroomService);
                }
        );

        if(result != null) {
            classroomService.addClassroom(result);
            loadClassrooms();
        }
    }

    @FXML
    private void onEdit() throws IOException {
        Classroom selected = classroomTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected Classroom: " + selected.getId());

        if(selected == null) return;

        Classroom result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/classroomDialog.fxml",
                "Edit Classroom",
                controller -> {
                    ClassroomDialogController c = (ClassroomDialogController) controller;
                    c.setClassroomService(classroomService);
                    c.setEditingClassroom(selected);
                }
        );

        if(result != null) {
            classroomService.editClassroom(result, selected.getId());
            loadClassrooms();
        }
    }

    @FXML
    private void onRemove() throws IOException {
        Classroom selected = classroomTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected Classroom: " + selected.getId());

        if(selected == null) return;

        Classroom result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/deleteClassroomDialog.fxml",
                "Delete Classroom",
                controller -> {
                    RemoveClassroomDialogController c = (RemoveClassroomDialogController) controller;
                    c.setClassroomService(classroomService);
                    c.setDeletedClassroom(selected);
                }
        );

        if(result != null) {
            classroomService.deleteClassroom(result.getId());
            loadClassrooms();
        }
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
