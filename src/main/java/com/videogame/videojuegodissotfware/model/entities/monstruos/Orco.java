package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.image.Image;

public class Orco extends Monstruo {
    public Orco(String nombre, int puntosVida, EstadoEntidad estado, int dano,
                int resistencia, Image sprite, double x, double y,
                EstrategiaCombate estrategiaCombate, int botin, int type) {

        super(nombre, puntosVida, estado, dano, resistencia, sprite, x, y, estrategiaCombate, botin, type);
    }


    @Override
    public int atacar(Entidad enemigo) {
        return 0;
    }

    @Override
    public void proteger() {

    }

    @Override
    public void usarPocion() {

    }
}