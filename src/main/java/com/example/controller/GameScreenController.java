package com.example.controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameScreenController extends ControllerBase {
    @FXML
    public AnchorPane maxpane;
    public static AnchorPane maxpane_stat;
    public Text bestbox;
    public Text scorebox;
    public static Text scorebox_stat;
    private static Rectangle stick;
    public static ImageView playersprite; // add as an attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )
    private static boolean keydown = false;
    public Text cherrycount;
    public static Text cherrycount_stat;

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
        PlatformHandler.makePlatforms(StickHero);
        maxpane_stat.requestFocus();
    }

    @FXML
    private void initialize() {
        readHighScoreFromFile();
        maxpane_stat = maxpane;
        scorebox_stat = scorebox;
        cherrycount_stat = cherrycount;
    }

    @FXML
    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        stage.setScene(MainApp.getscenes().get(2));
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
        System.out.println("key "+ event.getCode() +" released");
        Player StickHero = Player.getInstance(stick,playersprite);
        if (event.getCode() == KeyCode.SPACE && !(StickHero.isanyAnimation())) {
            // Calculate the duration of the key press
            keydown=false;
            StickHero.rotatestick();
        }
        if(event.getCode() == KeyCode.ESCAPE){
            switchToPauseScreen();
        }
        if(event.getCode() == KeyCode.SPACE&&StickHero.istraversalAnimation()){
            System.out.println("flip key released");
            StickHero.upsideDown();
        }


        //additional methods and event handlers for screen 2
    }

    public static void updateScore(Player stickhero) {
        scorebox_stat.setText("Score: "+ stickhero.getCurrentScore());
        // Update the score on the screen
        // This method will be called from the Player class
    }

    public static void updateCherries() {
        cherrycount_stat.setText("Coins: "+ Cherry.getNumCherries());
        // Update the score on the screen
        // This method will be called from the Player class
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
                System.out.println("Cherries: " + Cherry.getNumCherries());
            }
        }
        catch (IOException | NumberFormatException e) {
            Cherry.setNumCherries(0);
            e.printStackTrace();
        }
    }
}

