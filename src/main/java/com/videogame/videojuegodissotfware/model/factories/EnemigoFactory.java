package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;


public interface EnemigoFactory {
    Monstruo crearOrco(double x, double y);
    Monstruo crearEsqueleto(double x, double y);
    Monstruo crearMago(double x, double y);
}