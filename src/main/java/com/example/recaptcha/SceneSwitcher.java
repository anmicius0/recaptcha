package com.example.recaptcha;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
            scene.getStylesheets().add(Main.class.getResource("endgame.css").toExternalForm());
        }
        if (sceneName.equals("rule")) {
            loader = new FXMLLoader(Main.class.getResource("Rule.fxml"));
            scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
            scene.getStylesheets().add(Main.class.getResource("rule.css").toExternalForm());
        }
        if (sceneName.equals("welcome")) {
            loader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
            scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
            scene.getStylesheets().add(Main.class.getResource("welcome.css").toExternalForm());
            
    		//this is bgm	
    		String music = Main.class.getClassLoader().getResource("backgroundmusic.mp3").toExternalForm();
    		Media media = new Media(music);
    		MediaPlayer mediaplayer = new MediaPlayer(media);
    		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
    		mediaplayer.play();
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
            scene.getStylesheets().add(Main.class.getResource("welcome.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }
}
