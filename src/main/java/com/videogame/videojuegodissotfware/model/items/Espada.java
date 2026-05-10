package com.videogame.videojuegodissotfware.model.items;

public class Espada extends Item {

    private int incrementadorDano;

    public Espada(String nombre) {
        super(nombre, 60, "/com/videogame/videojuegodissotfware/images/items/espada.png");
        this.incrementadorDano = 10;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorDano;
    }
}
