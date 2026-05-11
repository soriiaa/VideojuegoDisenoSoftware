package com.videogame.videojuegodissotfware.model.items;

public class Armadura extends Item {

    private int incrementadorResistencia;

    public Armadura(String nombre) {
        super(nombre, 50, "/com/videogame/videojuegodissotfware/images/items/armadura.png");
        this.incrementadorResistencia = 15;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorResistencia;
    }
}