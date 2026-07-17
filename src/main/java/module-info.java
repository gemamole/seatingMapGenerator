module com.map.seatingmapgenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.map.seatingmapgenerator.controller to javafx.fxml;
    exports com.map.seatingmapgenerator;
    exports com.map.seatingmapgenerator.model;
    opens com.map.seatingmapgenerator.controller.dialogs to javafx.fxml;
}