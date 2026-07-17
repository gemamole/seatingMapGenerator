package com.map.seatingmapgenerator.controller.dialogs;

import javafx.stage.Stage;

public interface DialogController<T> {

    void setStage(Stage stage);

    T getResult();
}
