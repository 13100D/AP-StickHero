package com.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ControllerBase {

    protected static Stage stage;

    public static void setStage(Stage stage) {
        stage = stage;
    }

    protected static Parent loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(ControllerBase.class.getResource(fxmlFile));
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

