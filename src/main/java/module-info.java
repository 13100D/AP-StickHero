module com.example.stickhero {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires com.almasb.fxgl.all;

    opens com.example.stickhero to javafx.fxml;
    exports com.example.stickhero;
}