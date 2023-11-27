package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class GameScreenController extends ControllerBase {

    public static Rectangle platform_current_standing;
    public static Rectangle platform_next_target;

    public static Rectangle perfecttarget;
    @FXML
    private static Rectangle stick = new Rectangle();

    public static Rectangle player; // add as a attribute to player class??? maybe also include the stick probably hm also make out proper methods there itself instead of the thread here ( proper formatting )

    @FXML
    private Button pauseButton;
    @FXML
    private Button helpButton;

    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;

    private static double sticklength = 0;

    @FXML
    private void initialize() {
        stick.setWidth(70.0);
        stick.setHeight(100.0);
        stick.setLayoutX(125.0);
        stick.setTranslateY(72.0);
        stick.setRotate(0);

    }

    @FXML
    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        Parent root = loadFXML("/PauseScreen.fxml");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
    }
    @FXML
    private void helpButton(){
        System.out.println("Help button pressed");
    }

    static void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            // Record the time when the space key is pressed
            if(!keydown) {
                System.out.println("pressed");
                keydown = true;
                keyPressedTime = System.currentTimeMillis();
            }
            else{
                Thread stickplay = new Thread(()->{
//                    stick.setX(500);
                    System.out.println(stick.getTranslateX());
                    stick.setHeight(stick.getHeight()+5);
                    //run 2 frames worth of stick animation till it reaches peak length climax and cums
                });
                stickplay.start();
                sticklength+= 1;
                // the below test statement proved that after 0.5 seconds ~ we enter long press state all presses smaller than this may be safely ignored under no animation
                //or we just start animation with a certain length so essentially we start animation to start extending stick here
                //as for the animation details the input repeats with a pretty much consistent delay of 32-34 ms meaning a 30 calls per second so for a smooth 60 fps animation we should call a 2 frame animation here
                //call animation and set a length then run logic for moving player that length and handling input taps for cherry flipping
//                System.out.println("for duration of time: "+(System.currentTimeMillis()-keyPressedTime)+" registered keypresses: " +sticklength);
                //handle the animations for the keypressed down here ( should repeatedly end up being called idk why )
            }
        }
    }

    static void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            // Calculate the duration of the key press
            long keyReleasedTime = System.currentTimeMillis();
            System.out.println("released");
            keydown=false;
            long duration = keyReleasedTime - keyPressedTime;
            System.out.println("Key pressed duration: " + duration + " milliseconds");
            keyPressedTime = 0;
            Thread running = new Thread(()->{
                while(System.currentTimeMillis()-keyReleasedTime<3*duration){
                    // send ninja to the shadow dimension then decide what to do with this cunt depending on how far he stuck his cock out

                }
                sticklength+= 1;


            });
            running.start();

        }

        //additional methods and event handlers for screen 2
    }

}

