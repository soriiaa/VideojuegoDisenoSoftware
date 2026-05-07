package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;

public class Esqueleto  extends Monstruo {
    public Esqueleto() {
        super("Mago", 150, 20, 5, new Agresiva());
    }
}
