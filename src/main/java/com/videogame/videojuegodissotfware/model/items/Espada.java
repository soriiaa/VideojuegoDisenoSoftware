package com.videogame.videojuegodissotfware.model.items;

public class Espada extends Item {
    private int incrementadorDano;

    public Espada (String nombre, int precio) {
        super(nombre, precio);
        this.incrementadorDano = 10;
    }

    @Override
    public int getValorEfecto () {
        return incrementadorDano;
    }
}
