package com.example.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.example.controller.ControllerBase.loadFXML;

public class MainApp extends Application {

    private Stage primaryStage;
    private static ArrayList<Scene> scenes = new ArrayList<>();
    public static ArrayList<Scene> getscenes() {
        return scenes;
    }
    private static final ArrayList<String> screens = new ArrayList<>(List.of(new String[]{"/MainScreen.fxml", "/GameScreen.fxml", "/PauseScreen.fxml"}));
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ControllerBase.stage = primaryStage;
        primaryStage.setTitle("Stick Hero");
        for(String s : screens) {
            System.out.println(s);
            scenes.add(new Scene(loadFXML(s), 1280, 720));
        }
        showScreen("/MainScreen.fxml");
        primaryStage.show();
        MainScreenController.postInit();
    }

    public void showScreen(String fxmlFile) {
        try
        {
            ControllerBase.setStage(primaryStage);
            primaryStage.setScene(scenes.get(screens.indexOf(fxmlFile)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

