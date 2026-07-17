package com.map.seatingmapgenerator.controller.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class DialogUtil {

    public static <T> T showDialog(String fxmlPath,
                                   String title,
                                   Consumer<Object> controllerConfigurer) throws IOException {

        FXMLLoader loader = new FXMLLoader(DialogUtil.class.getResource(fxmlPath));
        Parent root = loader.load();

        Object controller = loader.getController();

        if(controllerConfigurer != null) {
            controllerConfigurer.accept(controller);
        }

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                DialogUtil.class.getResource("/css/dialog.css").toExternalForm());

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        if(controller instanceof DialogController dc) {
            dc.setStage(stage);
        }

        stage.showAndWait();

        if(controller instanceof DialogController dc) {
            return (T) dc.getResult();
        }

        return null;
    }
}
