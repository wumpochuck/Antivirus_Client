package ru.mtuci.antivirus.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane buttonsPane;

    @FXML
    private Button exitButton;

    @FXML
    private Button mainButton;

    @FXML
    private AnchorPane mainButtonBackground;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button profileButton;

    @FXML
    private AnchorPane profileButtonBackground;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Text welcomeText;

    @FXML
    void hideButtonsPane(MouseEvent event) {

    }

    @FXML
    void showButtonsPane(MouseEvent event) {

    }

    @FXML
    void initialize() {

        initSettings();

        assert buttonsPane != null : "fx:id=\"buttonsPane\" was not injected: check your FXML file 'main-window.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert mainButton != null : "fx:id=\"mainButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert mainButtonBackground != null : "fx:id=\"mainButtonBackground\" was not injected: check your FXML file 'main-window.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileButton != null : "fx:id=\"profileButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileButtonBackground != null : "fx:id=\"profileButtonBackground\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profilePane != null : "fx:id=\"profilePane\" was not injected: check your FXML file 'main-window.fxml'.";
        assert welcomeText != null : "fx:id=\"welcomeText\" was not injected: check your FXML file 'main-window.fxml'.";

    }

    private void initSettings() {
        hideAllPanes();
        mainPane.setVisible(true);
    }

    private void hideAllPanes(){
        mainPane.setVisible(false);
        profilePane.setVisible(false);
    }

}
