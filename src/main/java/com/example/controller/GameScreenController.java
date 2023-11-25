package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class GameScreenController extends ControllerBase {

    @FXML
    private Button switchButton;
    @FXML
    private Button helpButton;

    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;
    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToPauseScreen());
        helpButton.setOnAction(e -> System.out.println("Help button pressed"));
        setButtonImage(helpButton, "/helpicon.png");
        setButtonImage(switchButton, "/pauseicon.png");
//        switchButton.setOnKeyReleased(this::handleKeyRelease);
    }

    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        Parent root = loadFXML("/PauseScreen.fxml");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
    }

    private Image getImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    static void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            // Record the time when the space key is pressed
            if(!keydown) {
                System.out.println("pressed");
                keydown = true;
                keyPressedTime = System.currentTimeMillis();
            }
        }
    }

    static void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            // Calculate the duration of the key press
            long keyReleasedTime = System.currentTimeMillis();
            System.out.println("released");
            keydown=false;
            long duration = keyReleasedTime - keyPressedTime;
            System.out.println("Key pressed duration: " + duration + " milliseconds");
            keyPressedTime = 0;

        }
        //additional methods and event handlers for screen 2
    }

}

