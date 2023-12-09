module com.example.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.junit.jupiter.api;
    requires org.testng;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}