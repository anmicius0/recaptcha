package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Welcome {
    @FXML
    public Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() {
        // Change scene from welcome to game
        try {
            SceneSwitcher.switchScene("game");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}