package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScreenController extends ControllerBase {
    @FXML
    public AnchorPane maxpane;
    private boolean truly_init=false;

    private static Rectangle platform_current_standing;
    private static Rectangle platform_next_target;
    private static Rectangle perfecttarget;


    private static Rectangle stick;
    public static Image player; // add as a attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )
    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;

    @FXML
    private void initialize() {
        stick = new Rectangle(3,1, Color.rgb(0,0,0));
        stick.setLayoutX(250);
        stick.setLayoutY(620);
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
//            maxpane.getChildren().add(platform_current_standing);
            maxpane.getChildren().add(stick);
//            group.getChildren().add(platform_next_target);
//            group.getChildren().add(player);
//            group.getChildren().add(stick);
//            maxpane.getChildren().add(group);
            truly_init=true;
        }
        if (event.getCode() == KeyCode.A) {
            // Record the time when the space key is pressed
            if(!keydown) {
                System.out.println("pressed");
                keydown = true;
                keyPressedTime = System.currentTimeMillis();
            }
            else{
                Thread stickplay = new Thread(()->{
                    Player StickHero = Player.getInstance(stick,player);
                    StickHero.extendStick();
                    //run 2 frames worth of stick animation till it reaches peak length climax and cums
                });
                stickplay.start();
            }
        }
    }

    @FXML
    private void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            // Calculate the duration of the key press
            Player StickHero = Player.getInstance(stick,player);
            long keyReleasedTime = System.currentTimeMillis();
            System.out.println("released");
            keydown=false;
            long duration = keyReleasedTime - keyPressedTime;
            System.out.println("Key pressed duration: " + duration + " milliseconds");
            keyPressedTime = 0;
            StickHero.rotatestick();
//            Thread running = new Thread(()->{
//                while(System.currentTimeMillis()-keyReleasedTime<3*duration){
//                    // send ninja to the shadow dimension then decide what to do with this cunt depending on how far he stuck his cock out
//                    if(System.currentTimeMillis()-keyReleasedTime<duration) {
//
//
//                        stick.getTransforms().add(rotate);
//                    }
//                }
//
//
//
//            });
//            running.start();

        }

        //additional methods and event handlers for screen 2
    }

}

