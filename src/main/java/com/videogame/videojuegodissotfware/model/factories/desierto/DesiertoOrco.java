package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Defensiva;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class DesiertoOrco extends Orco {
    public DesiertoOrco(double x, double y){
        super("Orco del Desierto",
                150,
                150,
                new EstadoBasico(),
                80,
                40,
                new Image(DesiertoOrco.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/orco.png")),
                x,
                y,
                new Defensiva(),
                150,
                2
        );
        System.out.println("DEBUG: Orco del Desierto creado en (" + x + ", " + y + ")");
    }
}
