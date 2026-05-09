package com.videogame.videojuegodissotfware.model.items.decorator;

import com.videogame.videojuegodissotfware.model.items.Item;

public class EncantamientoFortaleza extends ItemDecorator {
    public EncantamientoFortaleza (Item item) {
        super(item);
    }

    @Override
    public int getValorEfecto() {
        return super.getValorEfecto() + 5;
    }

    public String getNombre() {
        return super.getNombre() + " Fortalecida";
    }
}
