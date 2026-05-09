package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import static com.videogame.videojuegodissotfware.model.actions.Accion.*;

public class FightController {
    @FXML
    private Label enemyName;
    @FXML
    private Label enemyHp;
    @FXML
    private Label enemyRes;
    @FXML
    private Label enemyDamage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Label enemyState;
    @FXML
    private Label enemyStrategy;
    @FXML
    private Label enemyReward;
    @FXML
    private Button atackBtn;
    @FXML
    private Button protectBtn;
    @FXML
    private Button potionBtn;
    @FXML
    private ScrollPane scrollLog;
    @FXML
    private VBox vboxMensajes;
    @FXML
    private VBox rootPane;

    GameFacade facade;

    public void initialize() {
        this.facade = GameFacade.getInstance();
        Combate combate = facade.getMundo().getCombateActual();
        rellenarEtiquetasEnemigo(combate.getEnemigo());
        atackBtn.setOnMouseClicked(event -> handleAttack());
        protectBtn.setOnMouseClicked(event -> handleProtect());
        potionBtn.setOnMouseClicked(event -> handlePotion());

        String tipoMapa = facade.getMundo().getMapa().getTipoMapa();

        if (tipoMapa.equalsIgnoreCase("Desierto")) {
            rootPane.getStyleClass().add("backgroundFightDesierto");
        } else {
            rootPane.getStyleClass().add("backgroundFightPradera");
        }
    }

    public void rellenarEtiquetasEnemigo(Monstruo enemigo) {
        enemyName.setText(enemigo.getNombre());
        enemyHp.setText(String.valueOf(enemigo.getPuntosVida()));
        enemyRes.setText(String.valueOf(enemigo.getResistencia()));
        enemyDamage.setText(String.valueOf(enemigo.getDano()));
        enemyState.setText(enemigo.getEstado().getNombre());
        enemyStrategy.setText(enemigo.getEstrategiaCombate().getEstrategia());
        enemyReward.setText(String.valueOf(enemigo.getBotin()));
        enemyImage.setImage(enemigo.getSprite());
    }

    @FXML
    private void handleAttack() {
        Accion accion = ATACAR;
        int danoInflingido = facade.ejecutarTurno(accion);
        addMsj("¡Has atacado al " + facade.getCombateActual().getEnemigo().getNombre() +" causando " + danoInflingido + " de daño!", "ATAQUE");
    }
    @FXML
    private void handleProtect() {
        Accion accion = PROTEGER;
        facade.ejecutarTurno(accion);
        addMsj("¡El guerrero se ha protegido (+ 20 de HP)!", "PROTEGER");
    }

    @FXML
    private void handlePotion() {
        Accion accion = USAR_POCION;
        facade.ejecutarTurno(accion);
        addMsj("¡El guerrero se ha echado una poción (+ 20 de HP)!", "PROTEGER");
    }

    public void addMsj(String texto, String tipo) {
        // 1. Crear el Label con el texto
        Label nuevoMensaje = new Label(texto);

        // 2. Asignar clases CSS según el tipo (ataque, daño recibido, pocion)
        nuevoMensaje.getStyleClass().add("mensaje-base");
        if (tipo.equals("ATAQUE")) nuevoMensaje.getStyleClass().add("mensaje-ataque");
        if (tipo.equals("PROTEGER")) nuevoMensaje.getStyleClass().add("mensaje-proteger");

        // 3. Añadir al VBox
        vboxMensajes.getChildren().add(nuevoMensaje);

        // 4. Auto-scroll al final (Truco: esperar a que JavaFX renderice el nuevo nodo)
        vboxMensajes.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollLog.setVvalue(1.0);
        });
    }
}
