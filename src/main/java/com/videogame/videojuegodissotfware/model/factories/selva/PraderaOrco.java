package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Defensiva;
import javafx.scene.image.Image;

public class PraderaOrco extends Orco {
    public PraderaOrco(double x, double y) {
        super("Orco de la Pradera",
                160,
                160,
                new EstadoBasico(),
                45,
                16,
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
