package com.videogame.videojuegodissotfware;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LaunchController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application! hola");
    }
}