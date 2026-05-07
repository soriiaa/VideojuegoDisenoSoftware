package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Dragon;
import java.util.ArrayList;
import java.util.List;

public class MapaFinalFactory implements EnemigoFactory {

    @Override
    public List<Monstruo> createGrupoMapa1() {
        return new ArrayList<>(); // Lista vacía, ¡no hay bichos normales aquí!
    }

    @Override
    public Monstruo crearJefe() {
        return new Dragon(); // Aquí tienes a tu boss
    }
}