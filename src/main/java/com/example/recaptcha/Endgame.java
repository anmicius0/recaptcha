package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Endgame {
    @FXML
    private Label scoreLabel;
    @FXML
    private Button playAgain;

    public Endgame(int score) throws IOException {
        Stage stage = (Stage) playAgain.getScene().getWindow();

        scoreLabel.setText("Your score is: " + score);
        playAgain.setOnMouseClicked(event -> {
            try {
                new Game(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Load endgame scene.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("endgame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }
}
