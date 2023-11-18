package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Screen2Controller extends ControllerBase {

    @FXML
    private Button switchButton;
    @FXML
    private Button helpButton;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToScreen1());
        helpButton.setOnAction(e -> System.out.println("Help button pressed"));
        setButtonImage(helpButton, "/helpicon.png");
        setButtonImage(switchButton, "/redoicon.png");
    }

    private void switchToScreen1() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Screen 1");
        stage.getScene().setRoot(loadFXML("/Screen1.fxml"));
    }



    private Image getImage(String imageName) {
        return new Image(getClass().getResourceAsStream(imageName));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    // Additional methods and event handlers for Screen 2 can be added here
}

