package com.videogame.videojuegodissotfware.model.items;

public class Espada extends Item {

    private int incrementadorResistencia;

    public Espada(String nombre) {
        super(nombre, 60, "/com/videogame/videojuegodissotfware/images/items/espada.png");
        this.incrementadorResistencia = 15;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorResistencia;
    }
}
