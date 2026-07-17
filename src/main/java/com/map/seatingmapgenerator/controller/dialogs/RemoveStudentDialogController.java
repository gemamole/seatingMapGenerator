package com.map.seatingmapgenerator.controller.dialogs;

import com.map.seatingmapgenerator.model.Student;
import com.map.seatingmapgenerator.service.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RemoveStudentDialogController implements DialogController<Student> {

    private Stage stage;
    private Student result;
    private StudentService studentService;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStudentService(StudentService service) {
        this.studentService = service;
    }

    @FXML
    private Label studentRemoved = new Label();

    public void setDeletedStudent(Student student) {
        if(student != null) {
            studentRemoved.setText(student.getId() + ": " + student.getName() + " - " + student.getGrade().getDisplayName());
            result = new Student(student.getName(), student.getId(), student.getGrade());
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
    public Student getResult() {
        return result;
    }
}
