package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MazeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}