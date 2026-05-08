package com.videogame.videojuegodissotfware.model.factories.cueva;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Dragon;
public class CuevaFactory {

    public static Monstruo crearBossFinal() {
        return new Dragon();
    }
}
