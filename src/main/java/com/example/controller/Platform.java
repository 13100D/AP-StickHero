package com.example.controller;
import java.util.Random;

public class Platform {
    Random rand = new Random();
    private double distance;
    private double width;

    public Platform() {
        this.distance = randomDistanceGenerator();
        this.width = randomWidthGenerator();
    }

    private double randomDistanceGenerator()
    {
        return rand.nextInt(500);
    }

    private double randomWidthGenerator()
    {
        return rand.nextInt(50);
    }


}
