package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import java.io.IOException;

import com.example.recaptcha.SceneSwitcher;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        try {
			SceneSwitcher.ChangeScene(stage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}