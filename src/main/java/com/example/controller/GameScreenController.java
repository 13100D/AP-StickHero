package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameScreenController extends ControllerBase {

    @FXML
    private Button switchButton;
    @FXML
    private Button helpButton;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToPauseScreen());
        helpButton.setOnAction(e -> System.out.println("Help button pressed"));
        setButtonImage(helpButton, "/helpicon.png");
        setButtonImage(switchButton, "/pauseicon.png");
    }

    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        stage.getScene().setRoot(loadFXML("/PauseScreen.fxml"));
    }

    private Image getImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    //additional methods and event handlers for screen 2
}

