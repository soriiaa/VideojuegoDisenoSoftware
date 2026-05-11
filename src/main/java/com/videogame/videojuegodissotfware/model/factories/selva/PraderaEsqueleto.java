package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class PraderaEsqueleto extends Esqueleto {
    public PraderaEsqueleto(double x, double y){
        super("Esqueleto de la Pradera",
                80,
                80,
                new EstadoBasico(),
                55,
                8,
                new Image(PraderaEsqueleto.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/esqueleto.png")),
                x,
                y,
                new Equilibrada(),
                100,
                3
        );
        System.out.println("DEBUG: Esqueleto de la Pradera creado en (" + x + ", " + y + ")");
    }
}
