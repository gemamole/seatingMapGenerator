package com.map.seatingmapgenerator.controller.dialogs;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.service.ClassroomService;
import com.map.seatingmapgenerator.service.ValidationService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClassroomDialogController implements DialogController<Classroom> {

    private Stage stage;
    private Classroom result;
    private Classroom selectedClass;
    private ClassroomService classroomService;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @FXML
    private TextField idField;
    @FXML
    private ComboBox<Integer> rowBox = new ComboBox<>();
    @FXML
    private ComboBox<Integer> columnBox = new ComboBox<>();
    @FXML
    private TextField capacityField;
    @FXML
    private Label errorLabel = new Label();

    @FXML
    public void initialize() {
        for (int i = 1; i <= 10; i++) {
            rowBox.getItems().add(i);
            columnBox.getItems().add(i);
        }

        idField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });

        rowBox.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });

        columnBox.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });

        capacityField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                errorLabel.setVisible(false);
                errorLabel.setText("");
            }
        });
    }

    public void setEditingClassroom(Classroom classroom) {
        if(classroom != null) {
            selectedClass = classroom;
            idField.setText(String.valueOf(classroom.getId()));
            rowBox.setValue(classroom.getRow());
            columnBox.setValue(classroom.getColumn());
            capacityField.setText(String.valueOf(classroom.getMaxStudents()));
        }
    }

    @FXML
    private void onSave() {

        try {
            ValidationService.requireNonEmpty(idField.getText(), "Número da sala");
            ValidationService.requireNonNull(rowBox.getValue(), "Fileiras");
            ValidationService.requireNonNull(columnBox.getValue(), "Colunas");
            ValidationService.requireNonNull(capacityField.getText(), "Capacidade de alunos");

            ValidationService.isInt(idField.getText(), "Número da sala");
            ValidationService.isInt(capacityField.getText(), "Capacidade de alunos");
            ValidationService.requireUniqueClassroomId(Integer.parseInt(idField.getText()), selectedClass, classroomService);
            ValidationService.requireValidCapacity(capacityField.getText(), rowBox.getValue(), columnBox.getValue(), "Capacidade de alunos");

            int id = Integer.parseInt(idField.getText());
            int row = rowBox.getValue();
            int column = columnBox.getValue();
            int maxStudents = Integer.parseInt(capacityField.getText());

            result = new Classroom(id, row, column, maxStudents);

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
    public Classroom getResult() {
        return result;
    }
}
