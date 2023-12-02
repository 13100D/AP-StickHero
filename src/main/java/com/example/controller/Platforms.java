package com.example.controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Platforms {
    Random rand = new Random();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();

    public Platforms() {
        Rectangle rect1 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
        Rectangle rect2 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
        Rectangle rect3 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
        rect1.setLayoutY(500);
        rect2.setLayoutY(500);
        rect3.setLayoutY(500);
        rect1.setLayoutX(250-rect1.getWidth());
        rect2.setLayoutX(250-rect2.getWidth()+randomDistanceGenerator());
        rect3.setLayoutX(250-rect3.getWidth()+randomDistanceGenerator());
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
    }

    private double randomDistanceGenerator()
    {
        return rand.nextInt(400);
    }

    private double randomWidthGenerator()
    {
        return rand.nextInt(350);
    }


}
