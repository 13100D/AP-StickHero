package com.example.controller;

import javafx.scene.input.KeyEvent;

public class PauseScreenController extends ControllerBase{
    public void handleKeyRelease(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ESCAPE:
                System.out.println("Unpausing");
                stage.setScene(MainApp.getscenes().get(1));
                break;
        }
    }
    //add boolean check in init to see if dead or just regular old paused
}
