package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Dragon;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;

public interface EnemigoFactory {
    Monstruo crearEsqueleto();
    Monstruo crearMago();
    Monstruo crearOrco();

    Monstruo crearDragon();
}