module ru.mtuci.antivirus_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens ru.mtuci.antivirus_client to javafx.fxml;
    opens ru.mtuci.antivirus_client.Controllers to javafx.fxml;
//    opens ru.mtuci.antivirus_client.Templates to javafx.fxml;
    exports ru.mtuci.antivirus_client;
}