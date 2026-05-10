package com.videogame.videojuegodissotfware.model.items;

import javafx.scene.image.Image;

public class Espada extends Item {

    private int incrementadorResistencia;

    public Espada(String nombre) {
        super(nombre, 60, "/com/videogame/videojuegodissotfware/images/items/espada.png");
        this.incrementadorResistencia = 10;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorResistencia;
    }
}
