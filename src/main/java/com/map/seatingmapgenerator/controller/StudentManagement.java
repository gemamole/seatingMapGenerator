package com.map.seatingmapgenerator.controller;

import com.map.seatingmapgenerator.controller.dialogs.DialogUtil;
import com.map.seatingmapgenerator.controller.dialogs.RemoveStudentDialogController;
import com.map.seatingmapgenerator.controller.dialogs.StudentDialogController;
import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.StudentService;
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

public class StudentManagement {

    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
        configureTable();
        configureSearch();
        configureSelection();
        loadStudents();
    }

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    private final FilteredList<Student> filtered = new FilteredList<>(students, p -> true);

    private void loadStudents() {
        students.setAll(studentService.getStudents());
        students.sort(Comparator.comparing(Student::getGrade).thenComparing(Student::getName));
    }

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> gradeColumn;

    @FXML
    private void configureTable() {

        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getId()
                ).asObject()
        );

        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getName()
                )
        );

        gradeColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getGrade().getDisplayName()
                )
        );

        studentTable.setItems(filtered);
    }

    @FXML
    private TextField searchField;

    @FXML
    private void configureSearch() {

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {

            String keyword = newVal.toLowerCase().trim();

            filtered.setPredicate(student -> {

                if (keyword.isEmpty()) {
                    return true;
                }

                return student.getName().toLowerCase().contains(keyword)
                        || String.valueOf(student.getId()).contains(keyword)
                        || student.getGrade().getDisplayName().toLowerCase().contains(keyword);
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
                studentTable.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        removeButton.disableProperty().bind(
                studentTable.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );
    }

    @FXML
    private void onAdd() throws IOException {
        Student result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/studentDialog.fxml",
                "Add Student",
                controller -> {
                    StudentDialogController c = (StudentDialogController) controller;
                    c.setStudentService(studentService);
                }
        );

        if(result != null) {
            studentService.addStudent(result);
            loadStudents();
        }
    }

    @FXML
    private void onEdit() throws IOException {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected Student: " + selected.getId());

        if(selected == null) return;

        Student result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/studentDialog.fxml",
                "Edit Student",
                controller -> {
                    StudentDialogController c = (StudentDialogController) controller;
                    c.setStudentService(studentService);
                    c.setEditingStudent(selected);
                }
        );

        if(result != null) {
            studentService.editStudent(result, selected.getId());
            loadStudents();
        }
    }

    @FXML
    private void onRemove() throws IOException {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected Student: " + selected.getId());

        if(selected == null) return;

        Student result = DialogUtil.showDialog(
                "/com/map/fxml/dialogs/deleteStudentDialog.fxml",
                "Delete Student",
                controller -> {
                    RemoveStudentDialogController c = (RemoveStudentDialogController) controller;
                    c.setStudentService(studentService);
                    c.setDeletedStudent(selected);
                }
        );

        if(result != null) {
            studentService.deleteStudent(result.getId());
            loadStudents();
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
