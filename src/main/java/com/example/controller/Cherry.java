package com.example.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class Cherry {
    private static int numCherries;
    private static final int spawnRate = 43;
    private static Map<Integer, Cherry> instances = new HashMap<>();
    private static ImageView cherrySprite = new ImageView(new Image("com/example/assets/coin.png"));
    private int distance;

    public static ImageView getCherrySprite() {
        return cherrySprite;
    }

    private Cherry(int distance)
    {
        this.distance = distance;
        cherrySprite.setFitHeight(35);
        cherrySprite.setFitWidth(35);
        cherrySprite.setLayoutY(500);
        cherrySprite.setLayoutX((distance)+200); //random upper bound as distance
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
}
