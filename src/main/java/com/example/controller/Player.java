package com.example.controller;

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

    public static void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGame.ser"))) {
            player = (Player) ois.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading the game.");
        }
    }
}
