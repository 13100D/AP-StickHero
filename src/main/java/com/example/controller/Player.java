package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;

public class Player implements Serializable {
    private boolean animation;

    public boolean isAnimation() {
        return animation;
    }
    private double idekwhyineedthisbutok =0;
    private static ImageView playersprite;
    private int currentScore;
    boolean goup = true;
    private static Player StickHero = null;
    private Rectangle stick;
    private boolean upsideDown = false;

    public double getlength() {
        return (double) stick.getHeight();
    }

    //singleton - Design Practice
    public static Player getInstance(Rectangle stick, ImageView playerSprite)
    {
        if (StickHero == null) {
            StickHero = new Player(stick, playerSprite);
        }

        return StickHero;
    }

    private Player(Rectangle stick, ImageView playerSprite) {
        this.currentScore = 0;
        this.stick=stick;
        this.playersprite=playerSprite;

    }

    public void extendStick() {
        if(stick.getHeight()>500 || !goup) { // if length has exceeded 500 or currently going down { set going down to true if not already and start reducing stick length}
            if(goup){goup=!goup;}
            stick.setHeight(stick.getHeight() - 5);
            stick.setTranslateY(stick.getTranslateY() + 5);
        }
        if (stick.getHeight()<50 || goup) {// if length is less than 50 or going upwards already ( set going upwards to true if not already ) and start increasing stick length
            if(!goup){goup=!goup;}
            stick.setHeight(stick.getHeight() + 5);
            stick.setTranslateY(stick.getTranslateY() - 5);
        }
    }

    public void startanim() {
        this.animation = true;
    }

    public void rotatestick() {//use pivot point and flip stick about bottom most point
        stick.getTransforms().clear();
        Rotate flip90deg = new Rotate();
        flip90deg.setPivotY(stick.getY() + stick.getHeight());
        flip90deg.setPivotX(stick.getX());
        stick.getTransforms().add(flip90deg);
        KeyFrame key2 = new KeyFrame(Duration.millis(550), new KeyValue(flip90deg.angleProperty(), 90));
        Timeline rotation = new Timeline(key2);
        rotation.play();
        rotation.setOnFinished(actionEvent -> {
            traversestick();
        });
    }
    public void flipback(){
        Rotate flipback = new Rotate();
        flipback.setPivotY(stick.getY() + stick.getHeight());
        flipback.setPivotX(stick.getX());
        flipback.setAngle(-90);
        stick.setY(stick.getY()+stick.getHeight());
        stick.setHeight(0);
        stick.getTransforms().add(flipback);
        animation = false;
    }
    public void traversestick() {
        //move the player across stick between one platform to other and repeatedly check for collision logic
        //timeline that moves player in +ve x-axis by stick.getlength distance
        System.out.println("stick traversal work in progress");
        idekwhyineedthisbutok += stick.getHeight();
        KeyValue kv = new KeyValue(playersprite.translateXProperty(), idekwhyineedthisbutok+25); // need to reset stick and player relative positioning too probably
        KeyFrame kf = new KeyFrame(Duration.millis(4*(stick.getHeight())+1), kv);
        Timeline timeline = new Timeline(kf);
        timeline.play();
        timeline.setOnFinished(actionEvent -> {
            //check for collision logic
            //if collision logic is true
            //call gameover
            //else
            //call continuegame
            Cherry.getCherrySprite().setOpacity(0.0);
            Platforms.makePlatforms(this);
            flipback();
        });

    }
    public void upsideDown() {
        // Implement upsideDown animation logic
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> playersprite.setScaleY(playersprite.getScaleY() * -1)),
                new KeyFrame(Duration.ZERO, event -> playersprite.setTranslateY(playersprite.getTranslateY()+100)),
                new KeyFrame(Duration.seconds(0.1))
        );
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> playersprite.setScaleY(playersprite.getScaleY() * -1)),
                new KeyFrame(Duration.ZERO, event -> playersprite.setTranslateY(playersprite.getTranslateY()-200)),
                new KeyFrame(Duration.seconds(0.1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        if (upsideDown) {
            upsideDown = false;
            timeline2.play();
        } else {
            upsideDown = true;
            timeline.play();
        }
    }

    public static ImageView getPlayerSprite() {
        return playersprite;
    }
}
