package com.example.recaptcha;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {
    private static Stage stage;
    private static FXMLLoader loader;
    private static Scene scene;

    public static void switchScene(String sceneName) {
        try {
            // Decide scene
            if (sceneName.equals("game")) {
                loader = new FXMLLoader(Main.class.getResource("game.fxml"));
                scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
            }
            if (sceneName.equals("end")) {
                loader = new FXMLLoader(Main.class.getResource("endgame.fxml"));
                scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
                scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("endgame.css")).toExternalForm());
            }
            if (sceneName.equals("rule")) {
                loader = new FXMLLoader(Main.class.getResource("Rule.fxml"));
                scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
                scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("rule.css")).toExternalForm());
            }
            if (sceneName.equals("welcome")) {
                loader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
                scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
                scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("welcome.css")).toExternalForm());

            }

            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Initialize the stage and show welcome screen
    public static void setStage(Stage stage) {
        // Set stage
        SceneSwitcher.stage = stage;
        stage.setX(960);
        stage.setY(540);

        // Load welcome scene
        loader = new FXMLLoader(SceneSwitcher.class.getResource("welcome.fxml"));
        try {
            scene = new Scene(loader.load());
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("welcome.css")).toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }
}
