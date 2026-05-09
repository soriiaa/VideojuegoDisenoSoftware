package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.model.core.GameFacade;
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
    private TextField name;
    @FXML
    private ImageView enterImg;
    @FXML
    private RadioButton pradera;
    @FXML
    private RadioButton desierto;
    @FXML
    private Label error;

    GameFacade facade;
    ToggleGroup typeMap;

    public void initialize() {
        facade = GameFacade.getInstance(); // creado con MenuState

        typeMap = new ToggleGroup(); // inicializan los generos del los radio buttons
        pradera.setToggleGroup(typeMap);
        desierto.setToggleGroup(typeMap);

        enterImg.setOnMouseClicked(event -> enter());
    }

    public boolean fieldsEmpty() {
        String nameKnight = name.getText();

        if (nameKnight.isEmpty()) {
            error.setText("Porfavor, introduce el nombre de tu guerrero/a");
            error.setVisible(true);
            return true;
        } else if (typeMap.getSelectedToggle() == null) {
            error.setText("Porfavor, selecciona el tipo de mapa");
            error.setVisible(true);
            return true;
        } else {
            error.setText("");
            error.setVisible(false);
            return false;
        }

    }
    
    public void enter() {
        if (!fieldsEmpty()) {
            try {
                String nombreGuerrero = name.getText();
                RadioButton seleccionado = (RadioButton) typeMap.getSelectedToggle();
                String typeMapSelected = seleccionado.getText();

                facade.inicializarNuevaPartida(nombreGuerrero, typeMapSelected); // al iniciar se pasa a PlayState

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/game-view.fxml"));
                Parent menuRoot = loader.load();
                root.getScene().setRoot(menuRoot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}