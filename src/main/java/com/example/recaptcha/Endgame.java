package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Endgame implements Initializable {
    @FXML
    private Label scoreLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setScore();
    }

    public void setScore() {
        scoreLabel.setText("Score: %d".formatted(Game.score));
    }

    @FXML
    protected void onPlayAgainButtonClick() throws IOException {
        // Change scene from welcome to game
        Game.score = -1;
        SceneSwitcher.switchScene("game");
    }
}
