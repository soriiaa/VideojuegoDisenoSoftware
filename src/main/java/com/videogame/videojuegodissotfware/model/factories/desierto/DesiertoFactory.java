package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;

public class DesiertoFactory  implements EnemigoFactory {
    @Override
    public Monstruo crearOrco(double x, double y) {
        return new DesiertoOrco(x, y);
    }

    @Override
    public Monstruo crearEsqueleto(double x, double y) {
        return new DesiertoEsqueleto(x, y);
    }

    @Override
    public Monstruo crearMago(double x, double y) {
        return new DesiertoMago(x, y);
    }
}
