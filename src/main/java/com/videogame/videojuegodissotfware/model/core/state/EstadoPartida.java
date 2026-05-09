package com.videogame.videojuegodissotfware.model.core.state;

import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.scene.input.KeyCode;

public interface EstadoPartida {
    void manejarInput(KeyCode tecla, GameFacade facade);
}
