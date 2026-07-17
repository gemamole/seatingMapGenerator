package com.map.seatingmapgenerator.controller.dialogs;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.service.ClassroomService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RemoveClassroomDialogController implements DialogController<Classroom> {

    private Stage stage;
    private Classroom result;
    private ClassroomService classroomService;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setClassroomService(ClassroomService service) {
        this.classroomService = service;
    }

    @FXML
    private Label classroomRemoved = new Label();

    public void setDeletedClassroom(Classroom classroom) {
        if(classroom != null) {
            classroomRemoved.setText("Sala " + classroom.getId());
            result = new Classroom(classroom.getId(), classroom.getRow(), classroom.getColumn(), classroom.getMaxStudents());
        }
    }

    @FXML
    private void onDelete() {
        stage.close();
    }

    @FXML
    private void onCancel() {
        result = null;
        stage.close();
    }

    @Override
    public Classroom getResult() {
        return result;
    }
}