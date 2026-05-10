package com.videogame.videojuegodissotfware.model.items;

public class Pocion extends Item {

    private int incrementoPuntosVida;

    public Pocion (String nombre) {
        super(nombre, 40, "/com/videogame/videojuegodissotfware/images/items/pocion.png");
        this.incrementoPuntosVida = 80;
    }

    @Override
    public int getValorEfecto() {
        return incrementoPuntosVida;
    }
}
