package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Defensiva;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class PraderaOrco extends Mago {
    public PraderaOrco(double x, double y) {
        super("Orco de la Selva",
                150,
                150,
                new EstadoBasico(),
                10,
                40,
                new Image(PraderaOrco.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/orco.png")),
                x,
                y,
                new Defensiva(),
                150,
                2
        );
        System.out.println("DEBUG: Orco de la Pradera creado en (" + x + ", " + y + ")");
    }
}
