module com.example.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.testng;
    requires org.junit.jupiter.api;
    requires junit;
    requires javafx.swing;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}