package com.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
    private final Rectangle stick;
    private boolean upsideDown = false;

    public double getlength() {
        return stick.getHeight();
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
        playersprite=playerSprite;

    }

    public void extendStick() {
        if(stick.getHeight()>500 || !goup) { // if length has exceeded 500 or currently going down { set going down to true if not already and start reducing stick length}
            if(goup){goup=false;}
            stick.setHeight(stick.getHeight() - 5);
            stick.setTranslateY(stick.getTranslateY() + 5);
        }
        if (stick.getHeight()<50 || goup) {// if length is less than 50 or going upwards already ( set going upwards to true if not already ) and start increasing stick length
            if(!goup){goup=true;}
            stick.setHeight(stick.getHeight() + 5);
            stick.setTranslateY(stick.getTranslateY() - 5);
        }
    }

    public void startanim() {this.animation = true;}
    public void stopanim() {this.animation = false;}

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
            StickHero.startanim();
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
        idekwhyineedthisbutok += stick.getHeight();
        KeyValue kv = new KeyValue(playersprite.translateXProperty(), idekwhyineedthisbutok+25); // need to reset stick and player relative positioning too probably
        KeyFrame kf = new KeyFrame(Duration.millis(4*(stick.getHeight())+1), kv);
        Timeline timeline = new Timeline(kf);
        timeline.play();
        timeline.setOnFinished(actionEvent -> {
            StickHero.stopanim();
            //check for collision logic
//            Cherry.getCherrySprite().setOpacity(0.0);
            System.out.println("stick length: "+stick.getHeight());
            System.out.println("player x: "+playersprite.getTranslateX());
            System.out.println("rect0 x "+Platforms.getRectangles().get(0).getTranslateX() + " rect0 width: "+Platforms.getRectangles().get(0).getWidth());
            System.out.println("rect1 x "+Platforms.getRectangles().get(1).getTranslateX() + " rect1 width: "+Platforms.getRectangles().get(1).getWidth());
            double sticklengthlower=-(Platforms.getRectangles().get(0).getTranslateX()+Platforms.getRectangles().get(0).getWidth()-Platforms.getRectangles().get(1).getTranslateX());
            System.out.println("expected stick length="+ sticklengthlower + " and width margin from platform ?  " + (Platforms.getRectangles().get(1).getWidth()));
            currentScore++;
            GameScreenController.updateScore(this);
            Platforms.makePlatforms(this);
            flipback();
        });

    }
    public void upsideDown() {
        // Implement upsideDown animation logic
        int alternative = upsideDown ? 1 : -1;
        upsideDown = !upsideDown;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> playersprite.setScaleY(playersprite.getScaleY() * -1)),
                new KeyFrame(Duration.seconds(0.01), event -> playersprite.setTranslateY(playersprite.getTranslateY()-(alternative*playersprite.getFitHeight())))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public static ImageView getPlayerSprite() {
        return playersprite;
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
