package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverScreen extends ControllerBase {

    @FXML
    private Button reviveButton;

    @FXML
    private Button menuButton;

    private boolean isMuted = false;

    @FXML
    private void initialize() {
        reviveButton.setOnAction(e -> switchToGame());
        menuButton.setOnAction(e -> switchToMainScreen());
        setButtonImage(menuButton, "/redoicon.png");
        setButtonImage(reviveButton, "/playicon.png");
    }

    private void switchToGame() {
        System.out.println("Switching back to game");
        stage.getScene().setRoot(loadFXML("/GameScreen.fxml"));
    }
    private void switchToMainScreen() { // back to main menu
        System.out.println("Switching to mainScreen");
        stage.getScene().setRoot(loadFXML("/MainScreen.fxml"));
    }

//    private void toggleVolume() {
//        isMuted = !isMuted;
//        updateVolumeButtonImage();
//        // Add logic to control audio volume based on the 'isMuted' state
//        // For simplicity, we'll just print a message here
//        System.out.println("Volume is " + (isMuted ? "muted" : "unmuted"));
//    }

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
