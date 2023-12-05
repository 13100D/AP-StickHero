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
            // shuffle around existing platforms


            moveGroup(Stickhero);

        }
    }

    private static void moveGroup(Player stickhero) {
        //make a grouping of stick playersprite and platforms
        //move the grouping
        Pane originpain = ((Pane) stickhero.getPlayerSprite().getParent());
        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>(rectangles);
        nodes.add(stickhero.getPlayerSprite());
        group.getChildren().addAll(nodes);
        originpain.getChildren().add(group);
// Set initial position
//        group.setTranslateX(200);

        System.out.println("Initial translateX: " + group.getTranslateX());

        KeyValue kv = new KeyValue(group.translateXProperty(), group.getTranslateX() - stickhero.getlength());
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        Timeline timeline = new Timeline(kf);

        KeyValue kv2 = new KeyValue(stickhero.getPlayerSprite().translateXProperty(), stickhero.getPlayerSprite().getTranslateX()-25);
        KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv2);
        Timeline timeline2 = new Timeline(kf2);


        timeline.play();

        timeline.setOnFinished(event -> {
            System.out.println("moving backwards and yeeting pillars");
            timeline2.play();
            System.out.println("Updated translateX: " + group.getTranslateX());
            //probably check for which animation to play ( in case of collision / insufficient stick length )
            int size = rectangles.size();

            Rectangle rect = rectangles.get(0);
            rectangles.remove(0);
            rectangles.add(rect);

            Rectangle rect3 = rectangles.get(2);
            rect3.setTranslateX(rect3.getTranslateX() + 700);
        });


//        double originalX = group.getLayoutX();
//
//        TranslateTransition transition = new TranslateTransition(Duration.millis(100), group);
//        transition.setToX(originalX);
//        transition.play();

//        stickhero.getStickSprite().setLayoutY(stickhero.getStickSprite().getLayoutY() + stickhero.getStickSprite().getHeight());
//        stickhero.getStickSprite().setLayoutX(stickhero.getStickSprite().getLayoutX() + stickhero.getStickSprite().getWidth());

//        KeyValue kv = new KeyValue(stick.translateXProperty(), stick.getHeight());
//        KeyFrame kf = new KeyFrame(Duration.millis(stick.getHeight()), kv);
//        Timeline timeline = new Timeline(kf);
//        timeline.play();

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

    private double randomWidthGenerator()
    {
        return rand.nextInt(100);
    }
}
