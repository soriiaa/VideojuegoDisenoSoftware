package com.videogame.videojuegodissotfware.model.items;

public class Pocion extends Item {
    private int puntosCuracion;

    public Pocion (String nombre, int precio) {
        super(nombre, precio);
        this.puntosCuracion = 10;
    }

    public int getValorEfecto() {
        return this.puntosCuracion;
    }
}
