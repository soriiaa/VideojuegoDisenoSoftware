package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.image.Image;

public class Dragon extends Monstruo {
    public Dragon (String nombre, int puntosVida, EstadoEntidad estadoDragon, int dano, int resistencia,
                   Image sprite, double x, double y, String categoria, EstrategiaCombate estrategiaCombate,
                   int botin, int type) {
        super(nombre, puntosVida, estadoDragon, dano, resistencia, sprite, x, y, categoria, estrategiaCombate, botin, type);
    }
}
