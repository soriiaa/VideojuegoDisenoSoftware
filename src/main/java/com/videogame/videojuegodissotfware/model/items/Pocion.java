package com.videogame.videojuegodissotfware.model.items;

import javafx.scene.image.Image;

public class Pocion extends Item {

    private int incrementadorResistencia;

    public Pocion (String nombre, int precio) {
        super(nombre, precio, "/com/videogame/videojuegodissotfware/images/items/pocion.png");
        this.incrementadorResistencia = 10;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorResistencia;
    }
}
