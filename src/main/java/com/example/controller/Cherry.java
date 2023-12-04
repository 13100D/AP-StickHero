package com.example.controller;

import javafx.scene.image.ImageView;

import java.util.*;

public class Cherry {
    private static int numCherries;
    private static final int spawnRate = 43;
    private static Map<Integer, Cherry> instances = new HashMap<>();
    private static ImageView cherrySprite;

    private Cherry(int distance)
    {
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

    }

    public static void reviveSubtractCherries()
    {

    }
}
