package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;


public interface EnemigoFactory {
    Monstruo crearOrco();
    Monstruo crearEsqueleto();
    Monstruo crearMago();
}