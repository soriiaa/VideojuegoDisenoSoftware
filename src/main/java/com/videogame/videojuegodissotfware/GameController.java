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

    @FXML
    private StackPane centralContent; // El hueco en el center del BorderPane
    public void initialize() {

        GameScene game = new GameScene(centralContent);
        centralContent.getChildren().add(game.getCanvas());
        game.start();



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
