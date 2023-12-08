package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class Platformhandler {

    private static class Platform {
        private final Rectangle pillar;
        private Rectangle perfectionredblob;
        private final double idealstickdistance;
        private final double width;

        public Platform() {
            this.idealstickdistance = randomDistanceGenerator();
            this.width = randomWidthGenerator();
            this.pillar = new Rectangle(width, 250, Color.rgb(1, 1, 1));
            this.perfectionredblob = new Rectangle(15, 5, Color.rgb(255, 0, 0));
            this.pillar.setLayoutY(500);
            this.pillar.setLayoutX(250+idealstickdistance-width/2);
            this.perfectionredblob.setLayoutX(250+idealstickdistance-perfectionredblob.getWidth()/2);
            this.perfectionredblob.setLayoutY(500);
            maxpane.getChildren().add(pillar);
            maxpane.getChildren().add(perfectionredblob);
            platforms.add(this);
        }
        public Platform(double width) {
            this.width = randomWidthGenerator();
            this.idealstickdistance = -width/2;
            this.pillar = new Rectangle(width, 250, Color.rgb(1, 1, 1));
            this.pillar.setLayoutY(500);
            this.pillar.setLayoutX(250+idealstickdistance-width/2);
            maxpane.getChildren().add(pillar);
            platforms.add(this);
        }
    }
    private static AnchorPane maxpane;
    private static final Random rand = new Random();
//    private static ArrayList<Rectangle> rectangles = new ArrayList<>();
    private static final ArrayList<Platform> platforms = new ArrayList<>();
    private static boolean initialized = false;
    private static boolean first = true;

//    public static void addToPane(AnchorPane maxpane) {
//
//        for (Rectangle rectangle : rectangles) {
////            maxpane.getChildren().add(rectangle);
//        }
//    }

    public static void makePlatforms(Player Stickhero)
    {
        if (!initialized)
        {
            initialized=true;
            maxpane = ((AnchorPane) Player.getPlayerSprite().getParent());
            Platformhandlerinit();
        }
        else
        {
            moveGroup(Stickhero);
        }
    }



    private static void Platformhandlerinit() {
        Platform base_starting_platform = new Platform(200);
        Platform first_random_platform = new Platform();
//        Rectangle rect1 = new Rectangle(250,250, Color.rgb(1,1,1));
//        Rectangle rect2 = new Rectangle(randomWidthGenerator()+30,250, Color.rgb(1,1,1));
//        Rectangle rect3 = new Rectangle(randomWidthGenerator()+30,250, Color.rgb(1,1,1));
//        rect1.setLayoutY(500);
//        rect2.setLayoutY(500);
//        rect3.setLayoutY(500);
//        rect1.setLayoutX(250-rect1.getWidth());
//        rect2.setLayoutX(250+rect2.getWidth()+randomDistanceGenerator());
//        rect3.setLayoutX(250+500+randomDistanceGenerator()); // ensure the third platform is always out of reach of stick length
//        rectangles.add(rect1);
//        rectangles.add(rect2);
//        rectangles.add(rect3);
    }




    private static void moveGroup(Player stickhero) {
        //make a grouping of stick playersprite and platforms
        //move the grouping
        Pane originpain = ((Pane) Player.getPlayerSprite().getParent());
        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>();
        for (Platform platform : platforms) {
            System.out.println("platform: " + platform);
            nodes.add(platform.pillar);
            nodes.add(platform.perfectionredblob);
        }
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
                rectanglesshuffle((AnchorPane) originpain, stickhero);

            }
            stickhero.noneanimationplaying();
        });
    }


    //
    public static void rectanglesshuffle(AnchorPane originpain, Player stickhero){
        originpain.getChildren().remove(platforms.get(0).pillar);
        originpain.getChildren().remove(platforms.get(0).perfectionredblob);
        platforms.remove(0);
        Platform newplat = new Platform();
        System.out.println("\ndistance: " + newplat.idealstickdistance+ "sticklength "+stickhero.getlength()+"\n");
        originpain.getChildren().add(newplat.perfectionredblob);
        originpain.getChildren().add(newplat.pillar);
    }










    private static double randomDistanceGenerator()
    {
        return 150+rand.nextInt(350);
    }

    private static double randomWidthGenerator()
    {
        return 40+rand.nextInt(80);
    }

//    public static ArrayList<Rectangle> getRectangles() {
//        return rectangles;
//    }
}
