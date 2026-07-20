package com.map.seatingmapgenerator.controller;

import com.map.seatingmapgenerator.database.ChartDAO;
import com.map.seatingmapgenerator.database.ClassroomDAO;
import com.map.seatingmapgenerator.database.StudentDAO;
import com.map.seatingmapgenerator.service.ClassroomService;
import com.map.seatingmapgenerator.service.SeatingService;
import com.map.seatingmapgenerator.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static final StudentDAO studentDAO = new StudentDAO();
    private static final ClassroomDAO classroomDAO = new ClassroomDAO();
    private static final ChartDAO chartDAO = new ChartDAO();

    private static final StudentService studentService = new StudentService(studentDAO);
    private static final ClassroomService classroomService = new ClassroomService(classroomDAO);
    private static final SeatingService seatingService = new SeatingService(chartDAO, studentDAO, classroomDAO);

    public static void switchScene(ActionEvent event,
                                   String fxml,
                                   String title) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                SceneManager.class.getResource(fxml));

        Parent root = loader.load();

        Object controller = loader.getController();

        SceneManager.assignService(controller);

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(
                SceneManager.class
                        .getResource("/css/app.css")
                        .toExternalForm());

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void assignService(Object controller) {
        if(controller instanceof StudentManagement studentController) {
            studentController.setStudentService(studentService);
        }

        if(controller instanceof ClassroomManagement classroomController) {
            classroomController.setClassroomService(classroomService);
        }

        if(controller instanceof ChartCreation seatingController) {
            seatingController.setSeatingService(seatingService);
        }
    }
}
