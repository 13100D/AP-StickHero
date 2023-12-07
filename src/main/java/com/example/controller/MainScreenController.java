package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class MainScreenController extends ControllerBase {

    @FXML
    public ImageView volumeicon;
    @FXML
    private Button switchButton;
    @FXML
    private Button volumeButton;
    @FXML
    private Button helpButton;
    private static boolean isMuted = false;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToGameScreen());
        helpButton.setOnAction(e -> System.out.println("Help button pressed"));
        volumeButton.setOnAction(e -> toggleVolume());
    }

    private void switchToGameScreen() {
        System.out.println("Starting Game");
        Parent root = loadFXML("/GameScreen.fxml");
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
    }

    private void toggleVolume() {
        isMuted = !isMuted;
        updateVolumeButtonImage();
        System.out.println("Volume is " + (isMuted ? "muted" : "unmuted"));
    }

    private void updateVolumeButtonImage() {
        String imageName = isMuted ? "/soundmutedicon.png" : "/soundyesicon.png";
        setImage(volumeicon, imageName);
    }

    private Image getImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setImage(ImageView image, String imageName) {
        image.setImage(getImage(imageName));
    }
}
