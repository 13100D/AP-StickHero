package com.example.controller;

import javafx.scene.image.Image;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class Cherry{
    private static boolean collisiondetected = false;
    private static final Random rand = new Random();
    private static boolean cherrySpawned = false;
    private static int numCherries;
    private static final int spawnRate = 35;
    private static Cherry cherry = null;
    private static ImageView cherrySprite = new ImageView(new Image("/coin.png"));

    private Cherry(double low, double high)
    {
        double spawnloc=rand.nextInt((int)(high - low))+ low ;
        System.out.println("spawnloc: " + spawnloc);
        cherrySprite.setLayoutX(spawnloc);
        cherrySprite.setLayoutY(500);
        cherrySprite.setFitHeight(35);
        cherrySprite.setFitWidth(35);
    }

    public static void checkCollision() {
        collisiondetected=false;
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (cherrySpawned&&!collisiondetected&&Player.getPlayerSprite().getBoundsInParent().intersects(cherrySprite.getBoundsInParent())) {
                    System.out.println("cherry Collision detected !!!");
                    numCherries += 1;
                    fadeOutAnimation();
                    collisiondetected=true;
                }
            }
        };

        timer.start();
    }

    public static void spawnCherry(double low, double high, Pane parent)
    {
        System.out.println("provided cherry spawn range: " + low + " " + high);

        if (rand.nextInt(100) < spawnRate) {

            if (cherry == null)
            {
                cherry = new Cherry(low, high);
                cherrySprite.setOpacity(0.0);
                parent.getChildren().add(cherrySprite);
            }

            cherrySpawned = true;
            cherrySprite.setLayoutX(rand.nextInt((int) (high - low)) + low);
            fadeInAnimation();
        }
    }

    public static int getNumCherries()
    {
        return numCherries;
    }

    public static void reviveSubtractCherries() throws InsufficientCherriesException {
        if (numCherries >= 20)
        {
            numCherries -= 20;
        }

        else
        {
            throw new InsufficientCherriesException("Not enough cherries to revive");
        }
    }

    public static void fadeInAnimation()
    {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(10), cherrySprite);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public static void fadeOutAnimation()
    {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(10), cherrySprite);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

    public static boolean isCherrySpawned()
    {
        return cherrySpawned;
    }

    public static void setCherrySpawned(boolean cherrySpawned) {
        Cherry.cherrySpawned = cherrySpawned;
    }

    public static ImageView getCherrySprite() {
        return cherrySprite;
    }

    public static void setNumCherries(int numCherries) {
        Cherry.numCherries = numCherries;
    }
}


