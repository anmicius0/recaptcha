package com.example.recaptcha;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private static Stage stage;
    private static FXMLLoader loader;
    private static Scene scene;

    public static void switchScene(String sceneName) throws IOException {
        // Decide scene
        if (sceneName.equals("game")) {
            loader = new FXMLLoader(Main.class.getResource("game.fxml"));
            scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
        }
        if (sceneName.equals("end")) {
            loader = new FXMLLoader(Main.class.getResource("endgame.fxml"));
            scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
        }

        stage.setScene(scene);
    }

    // Initialize the stage and show welcome screen
    public static void setStage(Stage stage) {
        // Set stage
        SceneSwitcher.stage = stage;
        stage.setMaximized(true);

        // Load welcome scene
        loader = new FXMLLoader(SceneSwitcher.class.getResource("welcome.fxml"));
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }
}
