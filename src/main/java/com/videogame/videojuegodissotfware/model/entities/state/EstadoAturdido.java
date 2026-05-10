package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoAturdido implements EstadoEntidad {
    private int turnosRestantes = 1;
    @Override
    public double modificarDano(int danoBase) {
        return 0; // si esta aturdido puede atacar pero no hace daño a su rival
    }

    @Override
    public EstadoEntidad actualizarEstado() {
        turnosRestantes--;
        if (turnosRestantes > 0) {
            return this; // sigue aturdido
        } else {
            System.out.println("DEBUG: El estado aturdido ha terminado, el personaje vuelve a estado basico.");
            return new EstadoBasico(); // vuelve a estado basico
        }
    }

    @Override
    public String getNombre() {
        return "Aturdido";
    }
}
