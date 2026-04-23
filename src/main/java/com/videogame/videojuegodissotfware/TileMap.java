package com.videogame.videojuegodissotfware;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileMap {
    private int[][] mapa;
    private Map<Integer, Image> tileset = new HashMap<>();
    private final int TILE_SIZE = 32; // Cámbialo a 16 o 64 si tus sprites se ven pequeños

    public TileMap() {
        cargarImagenes();
        // Cargamos el mapa usando getResourceAsStream para que funcione dentro del JAR
        this.mapa = leerTxt("/mapa/mapa.txt");
    }

    private void cargarImagenes() {
        // IMPORTANTE: Asegúrate de que las rutas coincidan con tu carpeta en resources
        try {
            tileset.put(0, new Image(getClass().getResourceAsStream("/mapa/Summer_Ground 01.png")));
            tileset.put(1, new Image(getClass().getResourceAsStream("/mapa/Summer_Ground 10.png")));
            tileset.put(2, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - Rock 01.png")));
            tileset.put(3, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - Tree Large.png")));
            tileset.put(4, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - House.png")));
            tileset.put(5, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - Campfire.png")));
            tileset.put(6, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - Tent.png")));
            tileset.put(7, new Image(getClass().getResourceAsStream("/mapa/Summer_Prop - Treasure Chest.png")));
        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
        }
    }

    private int[][] leerTxt(String ruta) {
        List<int[]> lineas = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(ruta);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividimos la línea por espacios
                String[] numeros = linea.trim().split("\\s+");
                int[] fila = new int[numeros.length];
                for (int i = 0; i < numeros.length; i++) {
                    fila[i] = Integer.parseInt(numeros[i]);
                }
                lineas.add(fila);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de mapa: " + e.getMessage());
            // Mapa de emergencia por si falla la lectura
            return new int[][]{{0}};
        }
        return lineas.toArray(new int[0][]);
    }

    public void render(GraphicsContext gc) {
        // Desactivar suavizado para que el Pixel Art se vea nítido
        gc.setImageSmoothing(false);

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                int id = mapa[i][j];
                Image img = tileset.get(id);

                // Primero dibujamos hierba de fondo (ID 0) si el objeto tiene transparencia
                if (id != 0 && id != 1) {
                    gc.drawImage(tileset.get(0), j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                if (img != null) {
                    gc.drawImage(img, j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    public int getTileAt(double x, double y) {
        int col = (int) (x / TILE_SIZE);
        int fila = (int) (y / TILE_SIZE);

        if (fila >= 0 && fila < mapa.length && col >= 0 && col < mapa[0].length) {
            return mapa[fila][col];
        }
        return -1;
    }

    public int getTileSize() { return TILE_SIZE; }
    public int getAnchoMapa() { return mapa[0].length * TILE_SIZE; }
    public int getAltoMapa() { return mapa.length * TILE_SIZE; }
}