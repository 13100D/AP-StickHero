package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainScreenController extends ControllerBase {

    @FXML
    private Button switchButton;

    @FXML
    private Button volumeButton;

    private Button restartButton;

    private boolean isMuted = false;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToScreen2());
        volumeButton.setOnAction(e -> toggleVolume());
        setButtonImage(volumeButton, "/soundyesicon.png");
        setButtonImage(switchButton, "/start.png");
        switchButton.setLayoutX(1500);
        switchButton.setLayoutY(1300);
    }

    private void switchToScreen2() {
        System.out.println("Switching to Screen 2");
        stage.getScene().setRoot(loadFXML("/GameScreen.fxml"));
    }

    private void toggleVolume() {
        switchButton.setLayoutX(switchButton.getLayoutX()+50);
        isMuted = !isMuted;
        updateVolumeButtonImage();
        // Add logic to control audio volume based on the 'isMuted' state
        // For simplicity, we'll just print a message here
        System.out.println("Volume is " + (isMuted ? "muted" : "unmuted"));
    }

    private void updateVolumeButtonImage() {
        String imageName = isMuted ? "/soundmutedicon.png" : "/soundyesicon.png";
        setButtonImage(volumeButton, imageName);
    }

    private Image getImage(String imageName) {
        return new Image(getClass().getResourceAsStream(imageName));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    private void reloadGame()
    {

    }

    private void buySprites()
    {

    }
}