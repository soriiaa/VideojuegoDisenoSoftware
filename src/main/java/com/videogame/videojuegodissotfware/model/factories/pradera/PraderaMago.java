package com.videogame.videojuegodissotfware.model.factories.pradera;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;
import javafx.scene.image.Image;

public class PraderaMago extends Mago {
    public PraderaMago(double x, double y){
        super("Mago de la Pradera",
                60,
                60,
                new EstadoBasico(),
                50,
                4,
                new Image(PraderaMago.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/mago.png")),
                x,
                y,
                new Agresiva(),
                50,
                1
        );
        System.out.println("DEBUG: Mago de la Pradera creado en (" + x + ", " + y + ")");
    }
}
