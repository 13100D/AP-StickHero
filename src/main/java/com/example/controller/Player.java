package com.example.controller;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;

import static java.lang.Math.abs;


public class Player implements Serializable {
    private static boolean anyanimation = false;

    public static boolean isAlive() {
        return alive;
    }

    private static boolean alive = true;

    public static void noneanimationplaying() {
        anyanimation = false;
    }
    public void someanimationplaying() {
        anyanimation = true;
    }
    public boolean isanyAnimation() {
        return anyanimation;
    }

    private static boolean traversalanimation = false;

    public boolean istraversalAnimation() {
        return traversalanimation;
    }
    private static double idekwhyineedthisbutok =0;
    private static ImageView playersprite;
    static Text floatingText = new Text("PERFECT!");
    private static int currentScore;
    private static int highScore;
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
    public static Player getInstance() {
        return StickHero;
    }

    private Player(Rectangle stick, ImageView playerSprite) {
        idekwhyineedthisbutok = 0;
        currentScore = 0;
        this.stick=stick;
        playersprite =playerSprite;
        boolean goup = true;

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
    public static void setPlayerSprite(String filepath) {
        playersprite.setImage(new Image(filepath));
    }

    public void starttraversalanim() {traversalanimation = true;}
    public static void stoptraversalanim() {traversalanimation = false;}

    public void rotatestick() {//use pivot point and flip stick about bottom most point
        someanimationplaying();
        stick.getTransforms().clear();
        Rotate flip90deg = new Rotate();
        flip90deg.setPivotY(stick.getY() + stick.getHeight());
        flip90deg.setPivotX(stick.getX());
        stick.getTransforms().add(flip90deg);
        KeyFrame key2 = new KeyFrame(Duration.millis(550), new KeyValue(flip90deg.angleProperty(), 90));
        Timeline rotation = new Timeline(key2);
        rotation.play();
        rotation.setOnFinished(actionEvent -> {
            StickHero.starttraversalanim();
            traversestick();
        });
    }
    public static void flipback(){
        stoptraversalanim();
        Rotate flipback = new Rotate();
        flipback.setPivotY(Player.getInstance().stick.getY() + Player.getInstance().stick.getHeight());
        flipback.setPivotX(Player.getInstance().stick.getX());
        flipback.setAngle(-90);
        Player.getInstance().stick.setY(Player.getInstance().stick.getY()+Player.getInstance().stick.getHeight());
        Player.getInstance().stick.setHeight(0);
        Player.getInstance().stick.getTransforms().add(flipback);
    }

    //Strategy - Design Practice
    public void traversestick() {
        //move the player across stick between one platform to other and repeatedly check for collision logic
        //timeline that moves player in +ve x-axis by stick.getlength distance
        idekwhyineedthisbutok += stick.getHeight();
        KeyValue kv = new KeyValue(playersprite.translateXProperty(), idekwhyineedthisbutok+25); // need to reset stick and player relative positioning too probably
        KeyFrame kf = new KeyFrame(Duration.millis(4*(stick.getHeight())+1), kv);
        Timeline timeline = new Timeline(kf);
        timeline.play();
        PlatformHandler.checkCollision();
        Cherry.checkCollision();
        PlatformHandler.setstickoffset(stick.getHeight());
        System.out.println("coordinates of latest perfect point and player traversal are " + PlatformHandler.getideallength() + " " + PlatformHandler.getPlayernetdistance());
        double cooking = abs(PlatformHandler.getideallength() - PlatformHandler.getPlayernetdistance());
        timeline.setOnFinished(actionEvent -> {
            stoptraversalanim();
            if(cooking< PlatformHandler.getwidth()/2) {
                if(cooking<7.5) {
                    System.out.println("HKJADSHKJDSAHKJDSAJHK");
                    perfection();
                }

                currentScore++;
                GameScreenController.updateScore(this);
                PlatformHandler.makePlatforms(this);
                flipback();
            }
            else{
                System.out.println("\n\n\nstick insufficient SUFFER\n\n\n");
//              GameScreenController.gameOver();
                death();
            }
        });

    }
    public static void fixposition(){
        if(StickHero.upsideDown){
            StickHero.upsideDown();
        }
        idekwhyineedthisbutok = PlatformHandler.getideallength();
        KeyValue kv = new KeyValue(playersprite.translateXProperty(), idekwhyineedthisbutok+25); // need to reset stick and player relative positioning too probably
        KeyFrame kf = new KeyFrame(Duration.millis(4000), kv);
        Timeline timeline = new Timeline(kf);
        timeline.play();
        Player.getInstance().stick.setHeight(PlatformHandler.getideallength());
        System.out.println("fixposition called");
        timeline.setOnFinished(actionEvent -> {
            System.out.println("fixposition called");
            PlatformHandler.makePlatforms(StickHero);
            System.out.println("fixposition called");
            flipback();
            System.out.println("fixposition called");
        });
    }
    public void upsideDown() {
        int alternative = upsideDown ? 1 : -1;
        upsideDown = !upsideDown;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> playersprite.setScaleY(playersprite.getScaleY() * -1)),
                new KeyFrame(Duration.seconds(0.01), event -> playersprite.setTranslateY(playersprite.getTranslateY()-(alternative* playersprite.getFitHeight())))
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

    public void perfection()
    {
        currentScore++;
        floatingText.setFont(new Font(20));
        floatingText.setOpacity(1);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), floatingText);
        fadeTransition.setFromValue(1.0); // Fully visible
        fadeTransition.setToValue(0.0);   // Completely transparent
        fadeTransition.setCycleCount(1);  // Play once

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), floatingText);
        translateTransition.setFromY(playersprite.getLayoutY());  // Initial Y position
        translateTransition.setToY(playersprite.getLayoutY()-50);  // Move up by 50 pixels
        translateTransition.setCycleCount(1);  // Play once

        // Creating a ParallelTransition to combine FadeTransition and TranslateTransition
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.play();
    }

    public static void death()
    {
        stoptraversalanim();
        alive = false;
        writeNumCherriesToFile();
        writeHighScoreToFile();
        System.out.println("Player died");
        deathAnimation();
        switchtoPauseScreen();
        PauseScreenController.postinit();
    }

    private static void switchtoPauseScreen() {
        ControllerBase.stage.setScene(MainApp.getscenes().get(2));
    }

    public static void deathAnimation()
    {

    }

    private static void writeNumCherriesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("com/example/controller/cherries.txt"))) {
            writer.write(String.valueOf(Cherry.getNumCherries()));
            System.out.println("NumCherries written to cherries.txt");
        } catch (IOException ignored) {
        }
    }

    static void writeHighScoreToFile() {
        if (currentScore > highScore)
        {
            try (PrintWriter writer = new PrintWriter(new FileWriter("com/example/controller/highScore.txt"))) {
                writer.write(String.valueOf(currentScore));
                System.out.println("High Score written to highScore.txt");
            } catch (IOException ignored) {
            }
        }
    }

    public static void setHighScore(int highScore) {
        Player.highScore = highScore;
    }
}
