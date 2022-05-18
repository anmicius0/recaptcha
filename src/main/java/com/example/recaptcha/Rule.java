package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
