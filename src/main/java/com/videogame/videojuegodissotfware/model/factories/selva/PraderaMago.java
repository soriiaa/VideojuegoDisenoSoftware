package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class PraderaMago extends Mago {
    public PraderaMago(double x, double y){
        super("Mago de la Selva",
                80,
                new EstadoBasico(),
                20,
                5,
                new Image(PraderaMago.class.getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/monstruosPradera/magoPradera.png")),
                x,
                y,
                new Equilibrada(),
                50,
                1
        );
    }
}
