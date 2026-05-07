package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;

import java.util.List;


public interface EnemigoFactory {
    List<Monstruo> createGrupoMapa1(); // Te devuelve todos los monstruos normales del mapa
    Monstruo crearJefe();              // Te devuelve el boss del mapa
}