package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import java.util.ArrayList;
import java.util.List;

public class Mapa1Factory implements EnemigoFactory {

    @Override
    public List<Monstruo> createGrupoMapa1() {
        List<Monstruo> enemigos = new ArrayList<>();
        enemigos.add(new Orco());
        enemigos.add(new Mago());
        enemigos.add(new Esqueleto());
        return enemigos;
    }

    @Override
    public Monstruo crearJefe() {
        return null;
    }
}