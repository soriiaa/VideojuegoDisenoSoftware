package com.videogame.videojuegodissotfware.model.items;

import javafx.scene.image.Image;

public abstract class Item {
    private String nombre;
    private int precio;
    private String rutaSprite;
    private Image sprite;

    public Item (String nombre, int precio, String rutaSprite) {
        this.nombre = nombre;
        this.precio = precio;
        this.rutaSprite = rutaSprite;
        this.sprite = new Image(getClass().getResourceAsStream(rutaSprite));
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public Image getSprite() {
        return sprite;
    }

    public String getRutaSprite() {
        return rutaSprite;
    }

    public abstract int getValorEfecto();
}
