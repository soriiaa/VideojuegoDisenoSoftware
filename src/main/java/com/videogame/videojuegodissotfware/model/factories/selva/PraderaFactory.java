package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;

public class PraderaFactory implements EnemigoFactory {

    @Override
    public Monstruo crearOrco(double x, double y) {
        return new PraderaOrco(x, y);
    }

    @Override
    public Monstruo crearEsqueleto(double x, double y) {
        return new PraderaEsqueleto(x, y);
    }

    @Override
    public Monstruo crearMago(double x, double y) {
        return new PraderaMago(x, y);
    }
}