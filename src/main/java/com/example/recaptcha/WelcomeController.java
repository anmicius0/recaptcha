package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    public Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() throws IOException {
        // Change scene from welcome to game
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        new GameScene(stage);
    }
}