package com.example.controller;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class Cherry {
    private static final Random rand = new Random();
    private static boolean cherrySpawned = false;
    private static int numCherries;
    private static final int spawnRate = 100;
    private static Cherry cherry = null;
    private static ImageView cherrySprite = new ImageView(new Image("/coin.png"));;

    public static ImageView getCherrySprite() {
        return cherrySprite;
    }

    private Cherry(double low, double high)
    {
        cherrySprite.setLayoutX(rand.nextInt((int) ( (high - low) + low)));
        cherrySprite.setLayoutY(500);
        cherrySprite.setFitHeight(35);
        cherrySprite.setFitWidth(35);
    }

    public static void spawnCherry(double low, double high, Pane parent) {
        System.out.println("provided cherry spawn range: " + low + " " + high);
        if (cherry == null) {
            cherry = new Cherry(low, high);
            cherrySprite.setOpacity(0.0);
            parent.getChildren().add(cherrySprite);
        }
        if (rand.nextInt(100) > spawnRate) {
            cherrySpawned = false;
            fadeOutAnimation();
        } else {
            cherrySpawned = true;
            cherrySprite.setLayoutX(rand.nextInt((int) (high - low)) + low);
            fadeInAnimation();
        }

    }



    public static int getNumCherries() {
        return numCherries;
    }

    public static void incrCherries()
    {
        numCherries += 1;
    }

    public static void reviveSubtractCherries()
    {
        numCherries -= 20;
    }

    public static void fadeInAnimation() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(10), cherrySprite);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public static void fadeOutAnimation() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(10), cherrySprite);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            // Additional logic to handle animation completion, if needed
        });
        fadeOut.play();
    }

    public static boolean isCherrySpawned() {
        return cherrySpawned;
    }
}


