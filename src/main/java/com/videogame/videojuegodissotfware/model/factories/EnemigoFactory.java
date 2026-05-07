package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;

import java.util.List;


public interface EnemigoFactory {
    List<Monstruo> createGrupoMapa1();
    Monstruo crearJefe();
}