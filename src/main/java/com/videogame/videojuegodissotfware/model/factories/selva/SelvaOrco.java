package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class SelvaOrco extends Mago {
    public SelvaOrco(double x, double y) {
        super("Orco de la Selva",
                150,
                new EstadoBasico(),
                10,
                40,
                new Image(SelvaOrco.class.getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/monstruosPradera/orcoPradera.png")),
                x,
                y,
                new Equilibrada(),
                150,
                2
        );
        System.out.println("DEBUG: Orco de la Selva creado en (" + x + ", " + y + ")");
    }
}
