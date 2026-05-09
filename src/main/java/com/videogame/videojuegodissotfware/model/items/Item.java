package com.videogame.videojuegodissotfware.model.items;

public abstract class Item {
    private String nombre;
    private int precio;

    public Item (String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return this.precio;
    }

    public abstract int getValorEfecto();
}
