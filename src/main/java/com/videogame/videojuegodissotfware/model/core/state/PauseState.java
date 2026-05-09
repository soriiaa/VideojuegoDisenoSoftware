package com.videogame.videojuegodissotfware.model.core.state;

import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.scene.input.KeyCode;

public class PauseState implements EstadoPartida {
    @Override
    public void manejarInput(KeyCode tecla, GameFacade facade) {
        // Manejar solo los clics del menú de pausa.
    }
}
