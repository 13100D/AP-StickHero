module com.example.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}