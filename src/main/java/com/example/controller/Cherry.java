package com.example.controller;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;

public class Cherry {
    private static int numCherries;
    private static final int spawnRate = 43;
    private static Map<Integer, Cherry> instances = new HashMap<>();
    private static ImageView cherrySprite;
    private final int distance;

    private Cherry(int distance)
    {
        this.distance = distance;
    }

    public static Cherry getInstance(Integer distance) {
        if (!instances.containsKey(distance))
        {
            instances.put(distance, new Cherry(distance));
        }

        return instances.get(distance);
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

    public void fadeInAnimation() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), cherrySprite);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void fadeOutAnimation() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), cherrySprite);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            // Additional logic to handle animation completion, if needed
        });
        fadeOut.play();
    }
}
