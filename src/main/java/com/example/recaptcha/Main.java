package com.example.recaptcha;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        //set icon and title
        Image icon = new Image("file:src/main/resources/com/example/recaptcha/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Recaptcha game");
        
        SceneSwitcher.setStage(stage);
    }
}