package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;

public class Player implements Serializable {

    private final Image playersprite;
    private int currentScore;
    private static int length=0;
    boolean goup = true;
    private static Player StickHero = null;
    private Rectangle stick;

    //singleton - Design Practice
    public static Player getInstance(Rectangle stick, Image playersprite)
    {
        if (StickHero == null) {
            StickHero = new Player(stick, playersprite);
        }

        return StickHero;
    }

    private Player(Rectangle stick, Image playersprite) {
        this.currentScore = 0;
        this.stick=stick;
        this.playersprite=playersprite;

    }
    Thread stickextend = new Thread(()->{
        if(length>500 || !goup) { // if length has exceeded 500 or currently going down { set going down to true if not already and start reducing stick length)
            if(goup){goup=!goup;}
            stick.setHeight(stick.getHeight() - 5);
            length -= 5;
            stick.setTranslateY(stick.getTranslateY() + 5);
        }
        if (length<50 || goup) {// if length is less than 50 or going upwards already ( set going up wards to true if not already ) and start increasing stick length
            if(!goup){goup=!goup;}
            stick.setHeight(stick.getHeight() + 5);
            length += 5;
            stick.setTranslateY(stick.getTranslateY() - 5);
        }
    });
    public void rotatestick() {//use pivot point and flip stick about bottom most point
        stick.getTransforms().clear();
        Rotate flip90deg = new Rotate(0, stick.getX(), stick.getY() + stick.getHeight());
        stick.getTransforms().add(flip90deg);
        KeyValue key1 = new KeyValue(flip90deg.angleProperty(), 90);
        KeyFrame key2 = new KeyFrame(Duration.millis(550), key1);
        Timeline stickrotate = new Timeline(key2);
        stickrotate.play();
    }
    public void upsideDown() {
        // Implement upsideDown method logic
    }

    public void continueGame() {
        // Implement continueGame method logic
    }

    public void gameOver() {
        // Implement gameOver method logic
    }

    public void revive() {
        // Implement revive method logic
    }

    public static void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGame.ser"))) {
            oos.writeObject(StickHero);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving the game.");
        }
    }

    public static void loadGame(MainScreenController mainScreenController) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGame.ser"))) {
            StickHero = (Player) ois.readObject();
            Parent root = mainScreenController.loadFXML("/GameScreen.fxml");
            Scene scene = new Scene(root, 1280, 720);
            System.out.println("switched screens supposedly???");
            mainScreenController.stage.setScene(scene);
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading the game.");
        }
    }
}
