package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class DesiertoEsqueleto extends Esqueleto {
    public DesiertoEsqueleto(double x, double y){
        super("Esqueleto del Desierto",
                100,
                new EstadoBasico(),
                30,
                20,
                new Image(DesiertoEsqueleto.class.getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/esqueleto.png")),
                x,
                y,
                new Equilibrada(),
                100,
                3
        );
    }
}
