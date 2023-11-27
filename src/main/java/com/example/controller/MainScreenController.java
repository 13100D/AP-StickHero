package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainScreenController extends ControllerBase {
    @FXML
    public ImageView volumeicon;
    @FXML
    private Button switchButton;

    @FXML
    private Button volumeButton;

    @FXML
    private Button helpButton;

    private boolean isMuted = false;

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
        System.out.println("switched screens supposedly???");
        stage.setScene(scene);
        stage.getScene().setOnKeyPressed(GameScreenController::handleKeyPress);
        stage.getScene().setOnKeyReleased(GameScreenController::handleKeyRelease);
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
        setImage(volumeicon, imageName);
    }

    private Image getImage(String imageName) {
        return new Image(getClass().getResourceAsStream(imageName));
    }

    private void setImage(ImageView image, String imageName) {
        image.setImage(getImage(imageName));
    }

    private void reloadGame()
    {
        Player.loadGame(this);
    }

    private void buySprites()
    {

    }
}
