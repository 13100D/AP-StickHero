package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class GameScreenController extends ControllerBase {

    @FXML
    private Button switchButton;
    @FXML
    private Button helpButton;

    private static long keyPressedTime = 0; // Time when the key was pressed
    private static boolean keydown = false;

    private static double sticklength = 0;

    @FXML
    private void initialize() {
        switchButton.setOnAction(e -> switchToPauseScreen());
        helpButton.setOnAction(e -> System.out.println("Help button pressed"));
        setButtonImage(helpButton, "/helpicon.png");
        setButtonImage(switchButton, "/pauseicon.png");
//        switchButton.setOnKeyReleased(this::handleKeyRelease);
    }

    private void switchToPauseScreen() {
        // Add logic specific to Screen 2
        System.out.println("Switching to Pause Screen ");
        Parent root = loadFXML("/PauseScreen.fxml");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
    }

    private Image getImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
    }

    private void setButtonImage(Button button, String imageName) {
        ImageView imageView = new ImageView(getImage(imageName));
        button.setGraphic(imageView);
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

            while(System.currentTimeMillis()-keyReleasedTime<3*duration){
//                if(System.currentTimeMillis()-keyReleasedTime<2*duration){
//                    System.out.println("runing animation dundundudun");
//                }
            }

        }

        //additional methods and event handlers for screen 2
    }

}

