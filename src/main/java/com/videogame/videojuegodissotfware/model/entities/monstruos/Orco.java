package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;

public class Orco extends Monstruo {
    public Orco() {
        super("Orco", 150, 20, 5, new Agresiva());
    }
}