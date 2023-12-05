package com.example.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class MainApp extends Application {

    private Stage primaryStage;
    String bgMusicPath = "bgmusic.mp3";
    //Media bgMusic = new Media(new File(bgMusicPath).toURI().toString());
    //MediaPlayer mediaPlayer = new MediaPlayer(bgMusic);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Stick Hero");
        showScreen("/MainScreen.fxml");
        //mediaPlayer.setAutoPlay(!MainScreenController.getIsMuted());
        primaryStage.show();
    }

    public void showScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);

            // Set the controller's stage reference
            ControllerBase controller = loader.getController();
            controller.setStage(primaryStage);

            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

