package com.example.controller;

import javafx.stage.Stage;

public abstract class ControllerBase {

    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

