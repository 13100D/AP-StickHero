package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PauseScreen extends ControllerBase {

    @FXML
    private Button menubutton;

    @FXML
    private Button resumebutton;
    @FXML
    private Button saveGame;

    @FXML
    private void initialize() {}

    private void saveGame()
    {
        Player.saveGame();
    }
    @FXML
    public void switchtoMainScreen(ActionEvent actionEvent) {
        System.out.println("Switching to mainScreen");
        Parent root = loadFXML("/MainScreen.fxml");
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
    }
    @FXML
    private void switchtoGameScreen(ActionEvent actionEvent) {
        System.out.println("Starting Game");
        Parent root = loadFXML("/GameScreen.fxml");
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
    }
}
