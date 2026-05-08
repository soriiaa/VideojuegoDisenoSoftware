package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;

import java.util.ArrayList;
import java.util.List;

public class SelvaFactory implements EnemigoFactory {


    @Override
    public Monstruo crearOrco() {
        return null;
    }

    @Override
    public Monstruo crearEsqueleto() {
        return null;
    }

    @Override
    public Monstruo crarMago() {
        return null;
    }
}