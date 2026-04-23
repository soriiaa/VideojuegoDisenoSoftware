module com.videogame.videojuegodissotfware {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.videogame.videojuegodissotfware to javafx.fxml;
    exports com.videogame.videojuegodissotfware;
}