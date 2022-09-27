module com.example.c195task1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c195task1 to javafx.fxml;
    opens com.example.c195task1.controller to javafx.fxml;
    opens com.example.c195task1.model to javafx.fxml;
    opens com.example.c195task1.helper to javafx.fxml;
    exports com.example.c195task1;
    exports com.example.c195task1.controller;
    exports com.example.c195task1.model;
    exports com.example.c195task1.helper;
}