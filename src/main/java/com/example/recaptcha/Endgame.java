package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Endgame {
    @FXML
    private Label scoreLabel;

    @FXML
    public void initialize() {
        scoreLabel.setText("Your score is 0");
    }
    
    @FXML
    protected void onPlayAgainButtonClick() throws IOException {
        // Change scene from welcome to game
        SceneSwitcher.switchScene("game");
    }

}
