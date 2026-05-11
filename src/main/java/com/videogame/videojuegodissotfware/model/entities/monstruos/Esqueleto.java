package com.videogame.videojuegodissotfware.model.entities.monstruos;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.image.Image;

public class Esqueleto extends Monstruo {
    public Esqueleto(String nombre, int puntosVida, int vidaMaxima, EstadoEntidad estado, int dano,
                int resistencia, Image sprite, double x, double y,
                EstrategiaCombate estrategiaCombate, int botin, int type) {

        super(nombre, puntosVida, vidaMaxima, estado, dano, resistencia, sprite, x, y, estrategiaCombate, botin, type);
    }

    @Override
    public Accion decidirAccion() {
        // esqueleto: si tiene más del 40% de vida, ataca (es agresivo), si no, se protege
        if (this.getPuntosVida() > (this.getVidaMaxima() * 0.6)) {
            return Accion.ATACAR;
        }
        return Accion.PROTEGER;
    }

}
