package com.videogame.videojuegodissotfware.model.core.state;

import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.scene.input.KeyCode;

public class GameOverState implements EstadoPartida {
    @Override
    public void manejarInput(KeyCode tecla, GameFacade facade) {
        // Solo deberiamos manejar input de clicar en un botón que ponga, has muerto aceptar.
    }
}
