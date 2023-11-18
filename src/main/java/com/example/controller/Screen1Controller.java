package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Screen1Controller extends ControllerBase {

    @FXML
    private Button switchButton;

    @FXML
    private Button volumeButton;

    private boolean isMuted = false;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToScreen2());
        volumeButton.setOnAction(e -> toggleVolume());
        setButtonImage(volumeButton, "/soundyesicon.png");
    }

    private void switchToScreen2() {
        System.out.println("Switching to Screen 2");
        stage.getScene().setRoot(loadFXML("/Screen2.fxml"));
    }

    private void toggleVolume() {
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
}
