package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class Platforms {
    private static final Random rand = new Random();
    private static ArrayList<Rectangle> rectangles = new ArrayList<>();
    private static Platforms instance = null;
    private static double stickMinLength;
    private static double stickMaxLength;
    private static double stickOptimalLength;
    private static boolean first = true;

    public static void addToPane(AnchorPane maxpane) {

        for (Rectangle rectangle : rectangles) {
            maxpane.getChildren().add(rectangle);
        }
    }

    public static void makePlatforms(Player Stickhero)
    {
        if (instance == null)
        {
            instance = new Platforms();
        }
        else
        {
            moveGroup(Stickhero);

        }
    }

    private static void moveGroup(Player stickhero) {
        //make a grouping of stick playersprite and platforms
        //move the grouping
        System.out.println("player sprite x: " + stickhero.getPlayerSprite().getTranslateX());
        System.out.println("player X: " + stickhero.getPlayerSprite().getX());
        System.out.println("player layout X: " + stickhero.getPlayerSprite().getLayoutX());
        System.out.println("\n\nsticklength: " + stickhero.getlength());
        System.out.println("rect2 X: " + rectangles.get(1).getX()+"rect2 width: "+rectangles.get(1).getWidth());
        System.out.println("rect2 layout X: " + rectangles.get(1).getLayoutX());
        System.out.println("rect2 translate X: " + rectangles.get(1).getTranslateX());
        Pane originpain = ((Pane) stickhero.getPlayerSprite().getParent());
        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>(rectangles);
        nodes.add(stickhero.getPlayerSprite());
        group.getChildren().addAll(nodes);
        originpain.getChildren().add(group);

        KeyValue kv = new KeyValue(group.translateXProperty(), group.getTranslateX() - stickhero.getlength());
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        Timeline timeline = new Timeline(kf);

        KeyValue kv2 = new KeyValue(stickhero.getPlayerSprite().translateXProperty(), stickhero.getPlayerSprite().getTranslateX()-25);
        KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv2);
        Timeline timeline2 = new Timeline(kf2);

        timeline.play();

        timeline.setOnFinished(event -> {
            timeline2.play();
            //probably check for which animation to play ( in case of collision / insufficient stick length )
            if(first){
                first = false;
            }
            else{
                Rectangle rect = rectangles.get(0);
                rectangles.remove(0);
                rect.setWidth(randomWidthGenerator() + 20);
                rectangles.add(rect);

                stickMinLength = rectangles.get(1).getLayoutX() - rectangles.get(0).getLayoutX();
                stickMaxLength = rectangles.get(2).getLayoutX() + rectangles.get(2).getWidth();
                stickOptimalLength = (stickMinLength + stickMaxLength) / 2;

                Rectangle rect3 = rectangles.get(2);
                rectangles.get(2).setTranslateX(rectangles.get(1).getTranslateX() + randomDistanceGenerator()+rectangles.get(1).getWidth());
                Cherry.spawnCherry(rectangles.get(1).getLayoutX() + rectangles.get(1).getWidth(), rectangles.get(2).getLayoutX() - 35, originpain);
            }
        });
    }

    private Platforms() {
        Rectangle rect1 = new Rectangle(250,250, Color.rgb(1,1,1));
        Rectangle rect2 = new Rectangle(randomWidthGenerator()+20,250, Color.rgb(1,1,1));
        Rectangle rect3 = new Rectangle(randomWidthGenerator()+20,250, Color.rgb(1,1,1));
        rect1.setLayoutY(500);
        rect2.setLayoutY(500);
        rect3.setLayoutY(500);
        rect1.setLayoutX(250-rect1.getWidth());
        rect2.setLayoutX(250+rect2.getWidth()+randomDistanceGenerator());
        rect3.setLayoutX(250+rect3.getWidth()+randomDistanceGenerator());
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
    }

    private static double randomDistanceGenerator()
    {
        return rand.nextInt(400);
    }

    private static double randomWidthGenerator()
    {
        return rand.nextInt(100);
    }

    public static double getStickMinLength() {
        return stickMinLength;
    }

    public static double getStickMaxLength() {
        return stickMaxLength;
    }

    public static double getStickOptimalLength() {
        return stickOptimalLength;
    }
}
