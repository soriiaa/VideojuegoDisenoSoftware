package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {
    private int[][] matrizMapa;
    private Map<Integer, Image> tileset = new HashMap<>();
    private List<Monstruo> enemigos = new ArrayList<>();
    private final int TILE_SIZE = 32;
    private String tipoMapa;

    public Mapa(String tipoMapa) {
        this.tipoMapa = tipoMapa;
        cargarPixeles();
        // crgamos el mapa usando getResourceAsStream para que funcione dentro del JAR
        if (tipoMapa.equalsIgnoreCase("Desierto")) {
            this.matrizMapa = leerTxt("/com/videogame/videojuegodissotfware/mapa/mapaDesierto.txt");
        } else {
            this.matrizMapa = leerTxt("/com/videogame/videojuegodissotfware/mapa/mapaPradera.txt");
        }
    }

    private void cargarPixeles() {
        try {
            if (tipoMapa.equalsIgnoreCase("Pradera")) {
                String pathPradera = "/com/videogame/videojuegodissotfware/mapa/assetsPradera/";

                int[] tilesPradera = {
                        0, 1, 2, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 33, 34, 35
                };

                for (int id : tilesPradera) {
                    tileset.put(id, new Image(getClass().getResourceAsStream(pathPradera + "tile" + id + ".png")));
                }

            } else if (tipoMapa.equalsIgnoreCase("Desierto")) {

                String pathDesierto = "/com/videogame/videojuegodissotfware/mapa/assetsDesierto/";

                int[] tilesDesierto = {
                        0, 45, 48,61,126 ,127 ,128, 129, 140, 141, 142, 143, 154, 155, 156, 157, 168, 169, 170, 171, 182, 183, 184 ,185
                };

                for (int id : tilesDesierto) {
                    tileset.put(id, new Image(getClass().getResourceAsStream(pathDesierto + "tile" + id + ".png")));
                }
            }

        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
            e.printStackTrace();
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
            // mapa de emergencia por si falla la lectura
            return new int[][]{{0}};
        }
        return lineas.toArray(new int[0][]);
    }

    public void setEnemigos(List<Monstruo> lista) {
        this.enemigos = lista;
    }

    public void render(GraphicsContext gc) {
        gc.setImageSmoothing(false); // Pixel Art nítido

        // Obtenemos el tamaño actual del Canvas para saber hasta dónde dibujar
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        // Calculamos cuántas filas y columnas necesitamos para llenar el 100%
        int cols = (int) Math.ceil(canvasWidth / TILE_SIZE);
        int rows = (int) Math.ceil(canvasHeight / TILE_SIZE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Obtenemos el ID del mapa, si nos pasamos del array, ponemos suelo (ID 0)
                int id = 0;
                if (i < matrizMapa.length && j < matrizMapa[i].length) {
                    id = matrizMapa[i][j];
                }

                Image img = tileset.get(id);

                // Dibujar siempre suelo de fondo (ID 0) para evitar huecos negros
                gc.drawImage(tileset.get(0), j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (img != null && id != 0) {
                    gc.drawImage(img, j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        if (enemigos != null) {
            for (Monstruo monstruo : enemigos) {
                monstruo.render(gc);
            }
        }
    }

    public int getTileAt(double x, double y) {
        int col = (int) (x / TILE_SIZE);
        int fila = (int) (y / TILE_SIZE);

        if (fila >= 0 && fila < matrizMapa.length && col >= 0 && col < matrizMapa[0].length) {
            return matrizMapa[fila][col];
        }
        return -1;
    }

    public double[] getPosicionValidaAleatoria() {
        int filas = matrizMapa.length;
        int columnas = matrizMapa[0].length;
        int filaAleatoria, colAleatoria;
        int tipoTile;

        do {
            filaAleatoria = (int) (Math.random() * filas);
            colAleatoria = (int) (Math.random() * columnas);

            // vemos que hay en el tile
            tipoTile = matrizMapa[filaAleatoria][colAleatoria];
            // Repetimos mientras sea un obstáculo
        } while (tipoTile == 2);

        double xPixel = colAleatoria * TILE_SIZE;
        double yPixel = filaAleatoria * TILE_SIZE;

        return new double[]{xPixel, yPixel};
    }

    public String getTipoMapa() {
        return this.tipoMapa;
    }
    public int getTileSize() { return TILE_SIZE; }
    public List<Monstruo> getEnemigos() { return enemigos; }
    public int getAnchoMapa() { return matrizMapa[0].length * TILE_SIZE; }
    public int getAltoMapa() { return matrizMapa.length * TILE_SIZE; }
}
