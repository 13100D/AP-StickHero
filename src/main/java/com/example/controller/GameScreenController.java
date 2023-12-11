package com.example.controller;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameScreenController extends ControllerBase{
    @FXML
    public AnchorPane maxpane;
    private static AnchorPane maxpane_stat;
    public Text bestbox;
    public Text scorebox;
    private static Text scorebox_stat;
    private static Rectangle stick;
    private static ImageView playersprite; // add as an attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )
    private static boolean keydown = false;
    public Text cherrycount;
    private static Text cherrycount_stat;
    private static Text bestbox_stat;

    public static void postInit() {
        stick = new Rectangle(3,1, Color.rgb(15,15,15));
        stick.setLayoutX(250);
        stick.setLayoutY(500);
        playersprite = new ImageView(new Image("file:src/main/resources/dinonig.png"));
        playersprite.setFitHeight(50);
        playersprite.setFitWidth(50);
        playersprite.setLayoutY(449);
        playersprite.setLayoutX(200);
        Player StickHero = Player.getInstance(stick, playersprite);
        maxpane_stat.getChildren().add(stick);
        maxpane_stat.getChildren().add(playersprite);
        maxpane_stat.getChildren().add(Player.getfloatingText());
        PlatformHandler.makePlatforms(StickHero);
        System.out.println("Game init done");
        maxpane_stat.requestFocus();
    }

    public static AnchorPane getmaxpane() {
        return maxpane_stat;
    }


    @FXML
    private void initialize() {
        readHighScoreFromFile();
        maxpane_stat = maxpane;
        scorebox_stat = scorebox;
        cherrycount_stat = cherrycount;
        bestbox_stat = bestbox;
    }

    @FXML
    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        stage.setScene(MainApp.getscenes().get(2));
        PauseScreenController.postinit();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        Player StickHero = Player.getInstance(stick, playersprite);
        if (event.getCode() == KeyCode.SPACE) {
            // Record the time when the space key is pressed
            if(!keydown) {
                keydown = true;
            }
            else{
                if(!(StickHero.isanyAnimation())) {
                    Thread stickplay = new Thread(StickHero::extendStick);
                    stickplay.start();
                }

            }
        }
    }

    @FXML
    private void handleKeyRelease(KeyEvent event) {
        System.out.println("key " + event.getCode() + " released");
        Player StickHero = Player.getInstance(stick, playersprite);
        if (event.getCode() == KeyCode.SPACE && !(StickHero.isanyAnimation())) {
            // Calculate the duration of the key press
            keydown = false;
            StickHero.rotatestick();
            }
        if (event.getCode() == KeyCode.ESCAPE) {
            switchToPauseScreen();
            }
        if (event.getCode() == KeyCode.SPACE && StickHero.istraversalAnimation()) {
            System.out.println("flip key released");
            StickHero.upsideDown();
            }
    }


    public static void updateScore(Player stickhero) {
        scorebox_stat.setText("Score: "+ stickhero.getCurrentScore());
        updateHighScore(stickhero);
    }
    public static void updateHighScore(Player stickhero) {
        if(stickhero.getCurrentScore() > Player.getHighScore())
        {
            Player.setHighScore(stickhero.getCurrentScore());
            bestbox_stat.setText("High Score: "+ stickhero.getCurrentScore());
        }
    }
    public static void updateCherries() {
        cherrycount_stat.setText("Coins: "+ Cherry.getNumCherries());
    }

    public static void perfection()
    {
        Text floatingText = Player.getfloatingText();
        floatingText.setText("PERFECT");
        floatingText.setFill(Color.rgb(255, 255, 255));
        floatingText.setLayoutX(playersprite.getLayoutX());
        floatingText.setLayoutY(playersprite.getLayoutY());
        floatingText.setTranslateX(playersprite.getTranslateX());
        floatingText.setFont(new Font(20));
        floatingText.setOpacity(1);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), floatingText);
        fadeTransition.setFromValue(1.0); // Fully visible
        fadeTransition.setToValue(0.0);   // Completely transparent
        fadeTransition.setCycleCount(1);  // Play once

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), floatingText);
        translateTransition.setFromY(playersprite.getLayoutY());  // Initial Y position
        translateTransition.setToY(playersprite.getLayoutY()-50);  // Move up by 50 pixels
        translateTransition.setCycleCount(1);  // Play once

        // Creating a ParallelTransition to combine FadeTransition and TranslateTransition
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.play();
    }

    private void readHighScoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highScore.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                int highScore = Integer.parseInt(line.trim());
                Player.setHighScore(highScore);
                bestbox.setText("High Score: " + highScore);
                cherrycount.setText("Coins: " + Cherry.getNumCherries());
                System.out.println("High Score: " + highScore);
                System.out.println("Coins: " + Cherry.getNumCherries());
            }
        }
        catch (IOException | NumberFormatException e) {
            Player.setHighScore(1);
        }
    }
}

