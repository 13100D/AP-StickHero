module com.example.controller {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example to javafx.fxml;
    exports com.example;
}