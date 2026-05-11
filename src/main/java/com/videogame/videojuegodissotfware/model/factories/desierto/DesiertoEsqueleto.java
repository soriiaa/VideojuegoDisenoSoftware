package com.videogame.videojuegodissotfware.model.factories.desierto;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class DesiertoEsqueleto extends Esqueleto {
    public DesiertoEsqueleto(double x, double y){
        super("Esqueleto del Desierto",
                80,
                80,
                new EstadoBasico(),
                55,
                8,
                new Image(DesiertoEsqueleto.class.getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/monstruosDesierto/esqueletoDesierto.png")),
                x,
                y,
                new Equilibrada(),
                100,
                3
        );
        System.out.println("DEBUG: Esqueleto del Desierto creado en (" + x + ", " + y + ")");
    }
}
