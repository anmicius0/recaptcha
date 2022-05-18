package com.example.recaptcha;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        //this is icon and title
        Image icon = new Image("file:src/main/resources/com/example/recaptcha/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Recaptcha game");

        //this is bgm
        String music = this.getClass().getClassLoader().getResource("BGM.mp3").toExternalForm();
        Media media = new Media(music);
        MediaPlayer mediaplayer = new MediaPlayer(media);
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.play();

        SceneSwitcher.setStage(stage);
    }
}