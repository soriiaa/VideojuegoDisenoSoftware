package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Agresiva;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class DesiertoMago extends Mago {
    public DesiertoMago(double x, double y){
        super("Mago del Desierto",
                60,
                60,
                new EstadoBasico(),
                65,
                4,
                new Image(DesiertoMago.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/mago.png")),
                x,
                y,
                new Agresiva(),
                50,
                1
        );
        System.out.println("DEBUG: Mago del Desierto creado en (" + x + ", " + y + ")");
    }
}
