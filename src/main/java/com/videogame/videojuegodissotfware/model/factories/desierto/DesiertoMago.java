package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class DesiertoMago extends Mago {
    public DesiertoMago(double x, double y){
        super("Mago del Desierto",
                80,
                new EstadoBasico(),
                20,
                5,
                new Image(DesiertoMago.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/mago.png")),
                x,
                y,
                new Equilibrada(),
                50,
                1
        );
    }
}
