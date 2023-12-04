package com.example.controller;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class Platforms {
    private final Random rand = new Random();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();

    private static Platforms instance = null;

    public static void addToPane(AnchorPane maxpane) {

        for (Rectangle rectangle : instance.rectangles) {
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

        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(stickhero.getPlayerSprite());
        nodes.addAll(instance.rectangles);
        group.getChildren().addAll(nodes);

        double originalX = 200;

        TranslateTransition transition = new TranslateTransition(Duration.millis(1000), group);
        transition.setToX(originalX);
        transition.play();

//        stickhero.getStickSprite().setLayoutY(stickhero.getStickSprite().getLayoutY() + stickhero.getStickSprite().getHeight());
//        stickhero.getStickSprite().setLayoutX(stickhero.getStickSprite().getLayoutX() + stickhero.getStickSprite().getWidth());

//        KeyValue kv = new KeyValue(stick.translateXProperty(), stick.getHeight());
//        KeyFrame kf = new KeyFrame(Duration.millis(stick.getHeight()), kv);
//        Timeline timeline = new Timeline(kf);
//        timeline.play();
//        timeline.setOnFinished(actionEvent -> {
//        });
    }

    private Platforms() {
        Rectangle rect1 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
        Rectangle rect2 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
        Rectangle rect3 = new Rectangle(randomWidthGenerator(),250, Color.rgb(1,1,1));
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

    private double randomDistanceGenerator()
    {
        return rand.nextInt(400);
    }

    private double randomWidthGenerator()
    {
        return rand.nextInt(100);
    }
}
