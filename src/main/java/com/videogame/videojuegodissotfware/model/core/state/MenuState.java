package com.videogame.videojuegodissotfware.model.core.state;

import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.scene.input.KeyCode;

public class MenuState implements EstadoPartida {
    @Override
    public void manejarInput(KeyCode tecla, GameFacade facade) {
        // Aquí hay que definir que solo funcione el clic para seleccionar las opciones y eso
    }
}
