package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainScreenController extends ControllerBase {

    @FXML
    public ImageView volumeicon;
    @FXML
    private Button switchButton;
    @FXML
    private Button volumeButton;
    private static boolean isMuted = false;
    private static MediaPlayer bgaudio;
    public static void postInit() {
        bgaudio= new MediaPlayer(new Media(Objects.requireNonNull(MainScreenController.class.getResource("/bgmusic.mp3")).toString()));
        bgaudio.setAutoPlay(true);
        bgaudio.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @FXML
    private void initialize() {
        readCherriesFromFile();
        switchButton.setOnAction(e -> switchToGameScreen());
        volumeButton.setOnAction(e -> toggleVolume());
    }

    private void switchToGameScreen() {
        System.out.println("Starting Game");
        ControllerBase.stage.setScene(MainApp.getscenes().get(1));
        GameScreenController.postInit();
    }

    private void toggleVolume() {
        isMuted = !isMuted;
        bgaudio.setMute(isMuted);
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

    private void readCherriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cherries.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                int cherries = Integer.parseInt(line.trim());
                Cherry.setNumCherries(cherries);
            }
        }
        catch (IOException | NumberFormatException e) {
            Cherry.setNumCherries(0);
            e.printStackTrace();
        }
    }

}
