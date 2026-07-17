package com.map.seatingmapgenerator.controller.dialogs;

import com.map.seatingmapgenerator.model.Grade;
import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.StudentService;
import com.map.seatingmapgenerator.service.ValidationService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class StudentDialogController implements DialogController<Student> {

    private Stage stage;
    private Student result;
    private Student selectedStudent;
    private StudentService studentService;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStudentService(StudentService service) {
        this.studentService = service;
    }

    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private ComboBox<String> gradeBox = new ComboBox<>();
    @FXML
    private Label errorLabel = new Label();

    @FXML
    public void initialize() {
        gradeBox.setItems(
                FXCollections.observableArrayList(
                    Arrays.stream(Grade.values())
                        .map(Grade::getDisplayName)
                        .toList()
                )
        );

        idField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });

        nameField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });

        gradeBox.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });
    }

    public void setEditingStudent(Student student) {
        if(student != null) {
            selectedStudent = student;
            nameField.setText(student.getName());
            idField.setText(String.valueOf(student.getId()));
            gradeBox.setValue(student.getGrade().getDisplayName());
        }
    }

    @FXML
    private void onSave() {

        try {
            ValidationService.requireNonEmpty(idField.getText(), "Matrícula");
            ValidationService.requireNonEmpty(nameField.getText(), "Nome");
            ValidationService.requireNonNull(gradeBox.getValue(), "Série");

            ValidationService.isInt(idField.getText(), "Matrícula");
            ValidationService.requireUniqueStudentId(Integer.parseInt(idField.getText()), selectedStudent, studentService);

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String grade = gradeBox.getValue();

            result = new Student(name, id, Grade.fromDisplayName(grade));

            stage.close();

        } catch(Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        stage.close();
    }

    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setVisible(true);
    }

    @Override
    public Student getResult() {
        return result;
    }
}
