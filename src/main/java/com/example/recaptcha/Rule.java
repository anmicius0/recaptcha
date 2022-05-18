package com.example.recaptcha;

import javafx.fxml.FXML;

import java.io.IOException;

public class Rule {

    @FXML
    protected void RuleButtonClink() {
        // Change scene from rule to welcome
        try {
            SceneSwitcher.switchScene("welcome");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
