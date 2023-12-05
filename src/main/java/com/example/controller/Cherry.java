package com.example.controller;

import javafx.scene.image.Image;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;

public class Cherry {
    private static final Random rand = new Random();
    private static boolean cherrySpawned = false;
    private static int numCherries;
    private static final int spawnRate = 43;
    private static Cherry cherry = null;
    private static ImageView cherrySprite = new ImageView(new Image("com/example/assets/coin.png"));;

    public static ImageView getCherrySprite() {
        return cherrySprite;
    }

    private Cherry(int low, int high)
    {
        cherrySprite.setLayoutX(rand.nextInt(high - low) + low);
        cherrySprite.setLayoutY(500);
        cherrySprite.setFitHeight(35);
        cherrySprite.setFitWidth(35);
    }

    public static void spawnCherry(int low, int high)
    {
        if (rand.nextInt(100) > spawnRate)
        {
            cherrySpawned = false;
            fadeOutAnimation();
        }

        else
        {
            cherrySpawned = true;

            if (cherry == null)
            {
                cherry = new Cherry(low, high);
                fadeInAnimation();
            }

            else
            {
                cherrySprite.setLayoutX(rand.nextInt(high - low) + low);
                fadeInAnimation();
            }
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
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), cherrySprite);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public static void fadeOutAnimation() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), cherrySprite);
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
