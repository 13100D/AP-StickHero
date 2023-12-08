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

public class Platforms{
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
            AnchorPane maxpane = ((AnchorPane) Player.getPlayerSprite().getParent());
            instance = new Platforms();
        }
        else
        {

            moveGroup(Stickhero);
        }
    }



    private Platforms() {
        Rectangle rect1 = new Rectangle(250,250, Color.rgb(1,1,1));
        Rectangle rect2 = new Rectangle(randomWidthGenerator()+30,250, Color.rgb(1,1,1));
        Rectangle rect3 = new Rectangle(randomWidthGenerator()+30,250, Color.rgb(1,1,1));
        rect1.setLayoutY(500);
        rect2.setLayoutY(500);
        rect3.setLayoutY(500);
        rect1.setLayoutX(250-rect1.getWidth());
        rect2.setLayoutX(250+rect2.getWidth()+randomDistanceGenerator());
        rect3.setLayoutX(250+500+randomDistanceGenerator()); // ensure the third platform is always out of reach of stick length
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
    }




    private static void moveGroup(Player stickhero) {
        //make a grouping of stick playersprite and platforms
        //move the grouping
        Pane originpain = ((Pane) Player.getPlayerSprite().getParent());
        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>(rectangles);
        nodes.add(Player.getPlayerSprite());
        group.getChildren().addAll(nodes);
        originpain.getChildren().add(group);

        KeyValue kv = new KeyValue(group.translateXProperty(), group.getTranslateX() - stickhero.getlength());
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        Timeline timeline = new Timeline(kf);

        KeyValue kv2 = new KeyValue(Player.getPlayerSprite().translateXProperty(), Player.getPlayerSprite().getTranslateX()-25);
        KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv2);
        Timeline timeline2 = new Timeline(kf2);

        timeline.play();

        timeline.setOnFinished(event -> {
            Cherry.setCherrySpawned(false);
            timeline2.play();
            //probably check for which animation to play ( in case of collision / insufficient stick length )
            if(first){
                System.out.println("first stick done");
                first = false;
            }
            else{
                originpain.getChildren().remove(rectangles.get(0));
                rectangles.remove(0);
                Rectangle rectnew = new Rectangle(randomWidthGenerator()+30,250, Color.rgb(1,1,1));
                rectnew.setLayoutY(500);
                double distance = randomDistanceGenerator();
                System.out.println("\ndistance: " + distance+ "sticklength "+stickhero.getlength()+"\n");
                rectnew.setLayoutX(rectangles.get(1).getLayoutX()+rectangles.get(1).getWidth()+distance);
                rectangles.add(rectnew);
                originpain.getChildren().add(rectnew);

            }
            stickhero.noneanimationplaying();
        });
    }



    public static void rectanglesshuffle(){
//        Rectangle rect = rectangles.get(0);
//        rectangles.remove(0);
//        rect.setWidth(randomWidthGenerator() + 20);
//        rectangles.add(rect);

        stickMinLength = rectangles.get(1).getLayoutX() - rectangles.get(0).getLayoutX();
        stickMaxLength = rectangles.get(2).getLayoutX() + rectangles.get(2).getWidth();
        stickOptimalLength = (stickMinLength + stickMaxLength) / 2;



        Rectangle rect3 = rectangles.get(2);
        rectangles.get(2).setTranslateX(rectangles.get(1).getTranslateX() + randomDistanceGenerator()+rectangles.get(1).getWidth());
//                Cherry.spawnCherry(rectangles.get(1).getLayoutX() + rectangles.get(1).getWidth(), rectangles.get(2).getLayoutX() - 35, originpain);

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

    public static ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}
