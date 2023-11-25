package com.example.controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayerExample extends Application {
    private Player player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 700);

        Image playerImage = new Image("playicon.png");  // Replace with your player image file

        // Create Player
        player = new Player(playerImage);

        root.getChildren().add(player);

        // Handle player movement
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                player.startMoving();
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                player.stopMoving();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        // Start animation timer for updating player
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update player
                player.update();
            }
        };
        timer.start();
    }

    public static class Player extends ImageView {
        private double initialX;
        private double targetX;
        private double velocityX;
        private static final double ACCELERATION = 0.5;
        private static final double MAX_VELOCITY = 5.0;

        private boolean isMoving = false;

        public Player(Image image) {
            super(image);
            initialX = 100;  // Set the initial X position
            targetX = initialX;
            velocityX = 0.0;
            setX(initialX);
            setY(200);  // Set the Y position
        }

        public void startMoving() {
            if (!isMoving) {
                targetX = getX() + 100;  // Adjust the target distance
                velocityX = 0.0;
                isMoving = true;
            }
        }

        public void stopMoving() {
            isMoving = false;
        }

        public void update() {
            if (isMoving) {
                double distance = targetX - getX();
                if (distance > 0) {
                    velocityX = Math.min(velocityX + ACCELERATION, MAX_VELOCITY);
                    setX(getX() + velocityX);
                } else {
                    // Smoothly return to the initial position with an accelerated animation
                    velocityX = Math.max(velocityX - ACCELERATION, -MAX_VELOCITY);
                    setX(getX() + velocityX);
                }

                // Check if the player has reached the target position
                if (Math.abs(getX() - targetX) < 1) {
                    isMoving = false;
                    setX(initialX);  // Reset to the initial position
                }
            }
        }
    }
}
