package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;

public class EnemigoFactoryManager {
    private static EnemigoFactoryManager instance;

    private EnemigoFactory enemigoFactory;

    private EnemigoFactoryManager() {}

    public static EnemigoFactoryManager getInstance() {
        if (instance == null) {
            instance = new EnemigoFactoryManager();
        }
        return instance;
    }

    public void setEnemigoFactory(EnemigoFactory factory) {
        this.enemigoFactory = factory;
    }

    public EnemigoFactory getEnemigoFactory() {
        return this.enemigoFactory;
    }

    public Monstruo crearOrco(double x, double y) {
        return enemigoFactory.crearOrco(x, y);
    }
    public Monstruo crearMago(double x, double y) {
        return enemigoFactory.crearMago(x, y);
    }
    public Monstruo crearEsqueleto(double x, double y) {
        return enemigoFactory.crearEsqueleto(x, y);
    }
}