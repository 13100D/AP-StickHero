package com.example.controller;
import java.util.Random;

public class Platform {

    private double distance;
    private double width;

    public Platform(double distance, double width) {
        this.distance = randomDistanceGenerator();
        this.width = randomWidthGenerator();
    }

    private double randomDistanceGenerator()
    {
        return 0;
    }

    private double randomWidthGenerator()
    {
        return 0;
    }

    private double getMidPoint()
    {
        return 0;
    }
}
