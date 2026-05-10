package com.videogame.videojuegodissotfware.model.items;

public class Armadura extends Item {

    private int incrementadorResistencia;

    public Armadura(String nombre, int precio) {
        super(nombre, precio, "/com/videogame/videojuegodissotfware/images/items/armadura.png");
        this.incrementadorResistencia = 10;
    }

    @Override
    public int getValorEfecto() {
        return incrementadorResistencia;
    }
}