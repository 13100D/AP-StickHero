package com.example.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.*;

public class Player implements Serializable {

    private int currentScore;
    private static Player player = null;

    //singleton - Design Practice
    public static Player getInstance()
    {
        if (player == null) {
            player = new Player();
        }

        return player;
    }

    private Player() {
        this.currentScore = 0;
    }

    public void extendStick() {
        // Implement extendStick method logic
    }

    public void upsideDown() {
        // Implement upsideDown method logic
    }

    public void continueGame() {
        // Implement continueGame method logic
    }

    public void gameOver() {
        // Implement gameOver method logic
    }

    public void revive() {
        // Implement revive method logic
    }

    public static void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGame.ser"))) {
            oos.writeObject(player);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving the game.");
        }
    }

    public static void loadGame(MainScreenController mainScreenController) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGame.ser"))) {
            player = (Player) ois.readObject();
            Parent root = mainScreenController.loadFXML("/GameScreen.fxml");
            Scene scene = new Scene(root, 1280, 720);
            System.out.println("switched screens supposedly???");
            mainScreenController.stage.setScene(scene);
//            mainScreenController.stage.getScene().setOnKeyPressed(GameScreenController::handleKeyPress);
//            mainScreenController.stage.getScene().setOnKeyReleased(GameScreenController::handleKeyRelease);
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading the game.");
        }
    }
}
