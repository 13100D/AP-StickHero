package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PauseScreen extends ControllerBase {

    @FXML
    private Button menubutton;

    @FXML
    private Button resumebutton;

    private boolean isMuted = false;

    @FXML
    private void initialize() {
        menubutton.setOnAction(e -> switchToMainScreen());
//        volumeButton.setOnAction(e -> toggleVolume());
        setButtonImage(menubutton, "/redoicon.png");
        setButtonImage(resumebutton, "/playicon.png");
        resumebutton.setOnAction(e -> switchToGameScreen());
    }

    private void switchToMainScreen() { // back to main menu
        System.out.println("Switching to mainScreen");
        stage.getScene().setRoot(loadFXML("/MainScreen.fxml"));
    }
    private void switchToGameScreen() { //resume
        System.out.println("Switching to gameScreen");
        stage.getScene().setRoot(loadFXML("/GameScreen.fxml"));
    }
//    private void toggleVolume() {
//        isMuted = !isMuted;
//        updateVolumeButtonImage();
//        // Add logic to control audio volume based on the 'isMuted' state
//        // For simplicity, we'll just print a message here
//        System.out.println("Volume is " + (isMuted ? "muted" : "unmuted"));
//    }
//
//    private void updateVolumeButtonImage() {
//        String imageName = isMuted ? "/soundmutedicon.png" : "/soundyesicon.png";
//        setButtonImage(volumeButton, imageName);
//    }

    private Image getImage(String imageName) {
        return new Image(getClass().getResourceAsStream(imageName));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }
}
