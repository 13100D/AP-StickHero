package com.example.controller;

public class Player {

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

    public void extendStick()
    {

    }

    public void upsideDown()
    {

    }

    public void continueGame()
    {

    }

    public void gameOver()
    {

    }

    public void revive()
    {

    }
}
