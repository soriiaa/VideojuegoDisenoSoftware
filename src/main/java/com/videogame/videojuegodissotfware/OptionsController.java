package com.videogame.videojuegodissotfware;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class OptionsController {
    @FXML
    private Button resumeBtn;
    @FXML
    private Button restartBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private StackPane contentPane;

    public void initialize() {
        resumeBtn.setOnMouseClicked(event -> resume());
        restartBtn.setOnMouseClicked(event -> restart());
        exitBtn.setOnMouseClicked(event -> exit());
    }

    public void resume() {
        StackPane parent = (StackPane) contentPane.getParent();
        parent.getChildren().remove(contentPane); // se elimina al hijo (que es la pantalla de pausa) del stackpane general

    }
    public void restart() {
    }
    public void exit() {
    }


}


