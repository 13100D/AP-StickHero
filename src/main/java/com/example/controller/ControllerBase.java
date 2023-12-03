package com.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ControllerBase {

    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected Parent loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Set the controller's stage reference
            ControllerBase controller = loader.getController();
            controller.setStage(stage);

            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

