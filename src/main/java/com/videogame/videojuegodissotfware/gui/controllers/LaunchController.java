package com.videogame.videojuegodissotfware.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LaunchController {
    @FXML
    private StackPane root; // panel principal
    @FXML
    private Label title;
    @FXML
    private TextField name;
    @FXML
    private ImageView enterImg;
    @FXML
    private RadioButton menKnight;
    @FXML
    private RadioButton womanKnight;
    @FXML
    private Label error;

    ToggleGroup genderCaracter;
    public void initialize() {
        genderCaracter = new ToggleGroup(); // inicializan los generos del los radio buttons
        menKnight.setToggleGroup(genderCaracter);
        womanKnight.setToggleGroup(genderCaracter);

        enterImg.setOnMouseClicked(event -> enter());
    }

    public boolean fieldsEmpty() {
        String nameKnight = name.getText();

        if (nameKnight.isEmpty()) {
            error.setText("Porfavor, introduce el nombre de tu guerrero/a");
            error.setVisible(true);
            return false;
        } else if (genderCaracter.getSelectedToggle() == null) {
            error.setText("Porfavor, selecciona el genero de tu guerrero/a");
            error.setVisible(true);
            return false;
        } else {
            error.setText("");
            error.setVisible(false);
            return true;
        }
    }
    
    public void enter() {
        if (fieldsEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/game-view.fxml"));
                Parent menuRoot = loader.load();
                root.getScene().setRoot(menuRoot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}