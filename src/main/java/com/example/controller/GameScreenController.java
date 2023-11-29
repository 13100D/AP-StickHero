package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Objects;

public class GameScreenController extends ControllerBase {
    boolean goup = true;
    @FXML
    public AnchorPane maxpane;
    private boolean truly_init=false;
    private static Parent NewSceneRoot;
    private static Timeline timeline;
    private static Scene scene;
    private static Group group;

    private static Rectangle platform_current_standing;
    private static Rectangle platform_next_target;

    private static Rectangle perfecttarget;
    private static int length=0;
    @FXML
    private static Rectangle stick;
    private static Rotate rotate = new Rotate();
    public static Rectangle player; // add as a attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )

    @FXML
    private Button pauseButton;
    @FXML
    private Button helpButton;

    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;


    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        GameScreenController.scene = scene;
    }

    public static void setNewSceneRoot(Parent newSceneRoot) {
        NewSceneRoot = newSceneRoot;
    }

    public static void setGroup(Group group) {
        GameScreenController.group = group;
    }


    @FXML
    private void initialize() {
        stick = new Rectangle(600,300, Color.rgb(0,0,0));
        stick.setLayoutX(250);
        stick.setLayoutY(620);
        stick.setWidth(3);
        stick.setHeight(1);



        // Create a Timeline for animation


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
                    if(length>500 || !goup) { // if length has exceeded 500 or currently going down { set going down to true if not already and start reducing stick length)
                        if(goup){
                            goup=!goup;
                        }
                        stick.setHeight(stick.getHeight() - 5);
                        length -= 5;
                        stick.setTranslateY(stick.getTranslateY() + 5);

                    }
                    if (length<50 || goup) {// if length is less than 50 or going upwards already ( set going up wards to true if not already ) and start increasing stick length
                        if(!goup){
                            goup=!goup;
                        }
                        stick.setHeight(stick.getHeight() + 5);
                        length += 5;
                        stick.setTranslateY(stick.getTranslateY() - 5);
                    }
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
            long keyReleasedTime = System.currentTimeMillis();
            System.out.println("released");
            keydown=false;
            long duration = keyReleasedTime - keyPressedTime;
            System.out.println("Key pressed duration: " + duration + " milliseconds");
            keyPressedTime = 0;
            stick.setLayoutX(250);
            stick.setLayoutY(620);
            rotate.setPivotX(stick.getWidth() / 2 + stick.getLayoutX());
            rotate.setPivotY(stick.getHeight() + stick.getLayoutY());
            Rotate rotate = new Rotate();
            stick.getTransforms().add(rotate);
            double initialPivotX = stick.getWidth() / 2;
            double initialPivotY = stick.getHeight();
            stick.setTranslateX(initialPivotX);
            stick.setTranslateY(initialPivotY);
            // Create a Timeline for animation
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(rotate.angleProperty(), 0),
                            new KeyValue(stick.translateXProperty(), initialPivotX),
                            new KeyValue(stick.translateYProperty(), initialPivotY)
                    ),
                    new KeyFrame(Duration.seconds(2),
                            new KeyValue(rotate.angleProperty(), 90)
                    )
            );
            timeline.play();
            stick.setLayoutX(250);
            stick.setLayoutY(620);

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

