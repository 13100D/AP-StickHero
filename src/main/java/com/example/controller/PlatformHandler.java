package com.example.controller;

import javafx.animation.FadeTransition;
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

public class PlatformHandler {

    private static boolean collisiondetected = false;

    public static void checkCollision() {
        // Use a Timeline or AnimationTimer to continuously check for collisions
        // For simplicity, a basic AnimationTimer is used in this example
        collisiondetected=false;
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                // Check if bounding boxes intersect
                if (!collisiondetected&&Player.getPlayerSprite().getBoundsInParent().intersects(platforms.get(1).pillar.getBoundsInParent())) {
                    System.out.println("platform player Collision detected !!!");
                    collisiondetected=true;
                    System.out.println("player is kil");
                    Player.death();
                }
            }
        };
        timer.start();

    }

    private static class Platform {
        private final Rectangle pillar;
        private final Rectangle perfectionredblob;
        private final double idealstickdistance;
        private final double width;


        public Platform() {
            this.idealstickdistance = randomDistanceGenerator();
            this.width = randomWidthGenerator();
            this.pillar = new Rectangle(width, 250, Color.rgb(1, 1, 1));
            this.perfectionredblob = new Rectangle(15, 5, Color.rgb(255, 0, 0));
            this.pillar.setLayoutY(500);
            double prevpoint = platforms.get(platforms.size()-1).pillar.getLayoutX()+platforms.get(platforms.size()-1).pillar.getWidth()/2;
            this.pillar.setLayoutX(prevpoint+idealstickdistance-width/2);
            this.perfectionredblob.setLayoutX(prevpoint+idealstickdistance-perfectionredblob.getWidth()/2);
            this.perfectionredblob.setLayoutY(500);
            this.perfectionredblob.setOpacity(0);
            this.pillar.setOpacity(0);
            maxpane.getChildren().add(pillar);
            maxpane.getChildren().add(perfectionredblob);
            platforms.add(this);
        }
        public Platform(double width) {
            this.width = randomWidthGenerator();
            this.idealstickdistance = -width/2;
            this.pillar = new Rectangle(width, 250, Color.rgb(1, 1, 1));
            this.perfectionredblob = new Rectangle(15, 5, Color.rgb(255, 0, 0));
            this.pillar.setLayoutY(500);
            this.pillar.setLayoutX(250+idealstickdistance-width/2);
            this.perfectionredblob.setLayoutY(500);
            this.perfectionredblob.setLayoutY(250+idealstickdistance-perfectionredblob.getWidth()/2);
            maxpane.getChildren().add(pillar);
            platforms.add(this);
        }
    }

    private static double playernetdistance = 250;
    private static AnchorPane maxpane;
    private static final Random rand = new Random();
    private static final ArrayList<Platform> platforms = new ArrayList<>();
    private static boolean initialized = false;


    public static double getPlayernetdistance() {
        return playernetdistance;
    }

    public static double getideallength(){
         return (platforms.get(1).perfectionredblob.getLayoutX());
    }
    public static double getcherryspawnupperbound(){
         return platforms.get(1).pillar.getLayoutX()-35;
         }
    public static double getcherryspawnlowerbound(){
         return platforms.get(0).pillar.getLayoutX()+platforms.get(0).pillar.getWidth();
    }

    public static void setstickoffset(double length){

        playernetdistance+=length;
    }

    public static double getwidth(){
        return platforms.get(platforms.size()-1).width;
    }


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
        new Platform(200);
        new Platform();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), platforms.get(platforms.size()-1).pillar);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.millis(100), platforms.get(platforms.size()-1).perfectionredblob);
        fadeIn2.setFromValue(0.0);
        fadeIn2.setToValue(1.0);
        fadeIn2.play();
    }

    private static void moveGroup(Player stickhero) {
        //make a grouping of stick playersprite and platforms
        //move the grouping
        new Platform();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), platforms.get(platforms.size()-1).pillar);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        FadeTransition fadeIn2 = new FadeTransition(Duration.millis(100), platforms.get(platforms.size()-1).perfectionredblob);
        fadeIn2.setFromValue(0.0);
        fadeIn2.setToValue(1.0);
        fadeIn2.play();
        Pane originpain = ((Pane) Player.getPlayerSprite().getParent());
        Pane group = new Pane();
        ArrayList<Node> nodes = new ArrayList<>();
        for (Platform platform : platforms) {
            nodes.add(platform.pillar);
            if(platform.perfectionredblob!=null){
                nodes.add(platform.perfectionredblob);
            }
        }
        nodes.add(Player.getPlayerSprite());
        group.getChildren().addAll(nodes);
        originpain.getChildren().add(group);

        KeyValue kv = new KeyValue(group.translateXProperty(), -stickhero.getlength());
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        Timeline timeline = new Timeline(kf);

        KeyValue kv2 = new KeyValue(Player.getPlayerSprite().translateXProperty(), Player.getPlayerSprite().getTranslateX()-25);
        KeyFrame kf2 = new KeyFrame(Duration.millis(20), kv2);
        Timeline timeline2 = new Timeline(kf2);

        timeline.play();

        timeline.setOnFinished(event -> {
            pillar_eliminator();
            System.out.println(getcherryspawnlowerbound()+"|"+getcherryspawnupperbound());
            Cherry.spawnCherry(getcherryspawnlowerbound(),getcherryspawnupperbound(),(Pane) Player.getPlayerSprite().getParent());
            timeline2.play();
            stickhero.noneanimationplaying();
        });
    }

    public static void pillar_eliminator(){
        ((Pane) Player.getPlayerSprite().getParent()).getChildren().remove(platforms.get(0).pillar);
        ((Pane) Player.getPlayerSprite().getParent()).getChildren().remove(platforms.get(0).perfectionredblob);
        platforms.remove(0);
    }

    private static double randomDistanceGenerator(){return 150+rand.nextInt(350);}
    private static double randomWidthGenerator(){return 40+rand.nextInt(80);}
}
