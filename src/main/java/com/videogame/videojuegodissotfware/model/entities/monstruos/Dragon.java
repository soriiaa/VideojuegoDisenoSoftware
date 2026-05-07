package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;

public class Dragon extends Monstruo {
    public Dragon() {
        super("Dragon", 150, 20, 5, new Agresiva());
    }
}
