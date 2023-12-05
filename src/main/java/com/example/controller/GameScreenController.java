package com.example.controller;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScreenController extends ControllerBase {
    @FXML
    public AnchorPane maxpane;
    private boolean truly_init=false;
    private static Rectangle stick;
    public static ImageView playersprite; // add as an attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )
    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;

    @FXML
    private void initialize() {
        stick = new Rectangle(3,1, Color.rgb(15,15,15));
        stick.setLayoutX(250);
        stick.setLayoutY(500);
        playersprite = new ImageView(new Image("file:src/main/resources/dinonig.png"));
        playersprite.setFitHeight(50);
        playersprite.setFitWidth(50);
        playersprite.setLayoutY(450);
        playersprite.setLayoutX(200);
        Player StickHero = Player.getInstance(stick, playersprite);
        Platforms.makePlatforms(StickHero);
    }

    @FXML
    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        Parent root = loadFXML("/PauseScreen.fxml");
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
    }
    @FXML
    private void helpButton(){
        System.out.println("Help button pressed");
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        if(!truly_init) {
            maxpane.getChildren().add(stick);
            maxpane.getChildren().add(playersprite);
            Platforms.addToPane(maxpane);
            truly_init=true;
        }
        Player StickHero = Player.getInstance(stick, playersprite);
        if (event.getCode() == KeyCode.A) {
            // Record the time when the space key is pressed
            if(!keydown) {
                keydown = true;
                keyPressedTime = System.currentTimeMillis();
            }

            else{
                if(!(StickHero.isAnimation())) {
                    Thread stickplay = new Thread(StickHero::extendStick);
                    stickplay.start();
                }

            }
        }
    }

    @FXML
    private void handleKeyRelease(KeyEvent event) {
        Player StickHero = Player.getInstance(stick,playersprite);
        if(StickHero.isAnimation()){
            System.out.println("flipping");
            StickHero.upsideDown();
        }
        if (event.getCode() == KeyCode.A && !(StickHero.isAnimation())) {
            // Calculate the duration of the key press
            long keyReleasedTime = System.currentTimeMillis();
            keydown=false;
            StickHero.startanim(); // sets value for animation boolean to avoid keypresses mid animations

            long duration = keyReleasedTime - keyPressedTime;
            keyPressedTime = 0;
            StickHero.rotatestick();


        }


        //additional methods and event handlers for screen 2
    }

}

