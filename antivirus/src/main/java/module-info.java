module ru.mtuci.antivirus {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens ru.mtuci.antivirus to javafx.fxml;
    exports ru.mtuci.antivirus;
    exports ru.mtuci.antivirus.controllers;
    opens ru.mtuci.antivirus.controllers to javafx.fxml;
    exports ru.mtuci.antivirus.animations;
    opens ru.mtuci.antivirus.animations to javafx.fxml;
}