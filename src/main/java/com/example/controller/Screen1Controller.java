package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Screen1Controller extends ControllerBase {

    @FXML
    private Button switchButton;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToScreen2());
    }

    private void switchToScreen2() {
        // Add logic specific to Screen 1
        System.out.println("Switching to Screen 2");
        stage.getScene().setRoot(loadFXML("Screen2.fxml"));
    }

    private Image getImage(String imageName) {
        return new Image(getClass().getResourceAsStream(imageName));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    // Additional methods and event handlers for Screen 1 can be added here
}
