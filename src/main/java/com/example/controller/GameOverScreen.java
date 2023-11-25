package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class GameOverScreen extends ControllerBase {

    @FXML
    private Button reviveButton;

    @FXML
    private Button menuButton;

    private Button restartButton;

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
        Parent root = loadFXML("/GameScreen.fxml");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
    }

    private void switchToMainScreen() { // back to the main menu
        System.out.println("Switching to mainScreen");
        Parent root = loadFXML("/MainScreen.fxml");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
    }

    private void saveCherries() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("cherries.txt"))) {
            int cherries = Cherry.getNumCherries();
            writer.println(cherries);
            System.out.println("Cherries saved to file: " + cherries);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
    }

    private void updateBestScore()
    {

    }
}
