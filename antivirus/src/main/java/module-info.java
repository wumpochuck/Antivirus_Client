module ru.mtuci.antivirus {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.mtuci.antivirus to javafx.fxml;
    exports ru.mtuci.antivirus;
}