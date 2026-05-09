package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class SelvaMago extends Mago {
    public SelvaMago(double x, double y){
        super("Mago de la Selva",
                80,
                new EstadoBasico(),
                20,
                5,
                new Image(SelvaMago.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/mago.png")),
                x,
                y,
                new Equilibrada(),
                50,
                1
        );
    }
}
