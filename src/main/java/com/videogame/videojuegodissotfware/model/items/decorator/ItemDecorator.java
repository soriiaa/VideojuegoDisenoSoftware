package com.videogame.videojuegodissotfware.model.items.decorator;

import com.videogame.videojuegodissotfware.model.items.Item;

public class ItemDecorator extends Item {
    protected Item itemEnvuelto;

    public ItemDecorator(Item item) {
        super(item.getNombre(), item.getPrecio());
        this.itemEnvuelto = item;
    }

    @Override
    public int getValorEfecto() {
        return itemEnvuelto.getValorEfecto();
    }

    @Override
    public String getNombre() {
        return itemEnvuelto.getNombre();
    }
}
