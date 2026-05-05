module com.videogame.videojuegodissotfware {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.videogame.videojuegodissotfware to javafx.fxml;
    exports com.videogame.videojuegodissotfware;
    exports com.videogame.videojuegodissotfware.gui.controllers;
    opens com.videogame.videojuegodissotfware.gui.controllers to javafx.fxml;
    exports com.videogame.videojuegodissotfware.gui.view;
    opens com.videogame.videojuegodissotfware.gui.view to javafx.fxml;
    opens com.videogame.videojuegodissotfware.fxml to javafx.fxml;
}