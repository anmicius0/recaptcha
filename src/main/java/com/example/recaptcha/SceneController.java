package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    @FXML
    private static GameScene gameScene;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() throws IOException {
        // Change scene from welcome to game
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        gameScene = new GameScene();
        gameScene.init(stage);
    }
}