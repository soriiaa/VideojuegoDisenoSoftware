package com.videogame.videojuegodissotfware;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GameController {
    @FXML
    private ImageView pauseBtn;

    @FXML
    private StackPane contentPane;

    public void initialize() {
        pauseBtn.setOnMouseClicked(event -> pause());
    }

    @FXML
    public void pause() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/options-view.fxml"));
            Parent pauseMenu = loader.load();

            contentPane.getChildren().add(pauseMenu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
