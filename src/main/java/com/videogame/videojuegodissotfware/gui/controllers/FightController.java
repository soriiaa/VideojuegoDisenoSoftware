package com.videogame.videojuegodissotfware.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class FightController {

    @FXML
    private Button atackBtn;
    @FXML
    private Button protectBtn;
    @FXML
    private ScrollPane scrollLog;
    @FXML
    private VBox vboxMensajes;

    public void initialize() {
        atackBtn.setOnMouseClicked(event -> handleAttack());
        protectBtn.setOnMouseClicked(event -> handleProtect());
    }
    @FXML
    private void handleAttack() {
        // Lógica de cálculo de daño...
        addMsj("¡Has atacado al Ogro causando 100 de daño!", "ATAQUE");
    }
    @FXML
    private void handleProtect() {
        // Lógica de cálculo de daño...
        addMsj("¡El guerrero se ha protegido (+ 20 de HP)!", "PROTEGER");
    }
    public void addMsj(String texto, String tipo) {
        // 1. Crear el Label con el texto
        Label nuevoMensaje = new Label(texto);

        // 2. Asignar clases CSS según el tipo (ataque, daño recibido, pocion)
        nuevoMensaje.getStyleClass().add("mensaje-base");
        if (tipo.equals("ATAQUE")) nuevoMensaje.getStyleClass().add("mensaje-ataque");
        if (tipo.equals("PROTEGER")) nuevoMensaje.getStyleClass().add("mensaje-proteger");

        // 3. Añadir al VBox
        vboxMensajes.getChildren().add(nuevoMensaje);

        // 4. Auto-scroll al final (Truco: esperar a que JavaFX renderice el nuevo nodo)
        vboxMensajes.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollLog.setVvalue(1.0);
        });
    }
}
