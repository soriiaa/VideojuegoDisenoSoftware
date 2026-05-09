package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;

public class SelvaFactory implements EnemigoFactory {

    @Override
    public Monstruo crearOrco(double x, double y) {
        return new SelvaOrco(x, y);
    }

    @Override
    public Monstruo crearEsqueleto(double x, double y) {
        return new SelvaEsqueleto(x, y);
    }

    @Override
    public Monstruo crearMago(double x, double y) {
        return new SelvaMago(x, y);
    }
}