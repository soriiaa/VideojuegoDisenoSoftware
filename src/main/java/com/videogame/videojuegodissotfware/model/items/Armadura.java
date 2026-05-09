package com.videogame.videojuegodissotfware.model.items;

public class Armadura extends Item {
    private int incrementadorResistencia;

    public Armadura (String nombre, int precio) {
        super(nombre, precio);
        this.incrementadorResistencia = 10;
    }

    @Override
    public int getValorEfecto () {
        return this.incrementadorResistencia;
    }
}
