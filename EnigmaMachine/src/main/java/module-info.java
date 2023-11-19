module com.enigma.enigmamachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.enigma.enigmamachine to javafx.fxml;
    exports com.enigma.enigmamachine;
}