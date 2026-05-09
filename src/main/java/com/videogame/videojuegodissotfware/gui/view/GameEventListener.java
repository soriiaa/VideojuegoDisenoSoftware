package com.videogame.videojuegodissotfware.gui.view;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;

public interface GameEventListener {
    void onFightStarted(Monstruo monstruo);
}