package ru.mtuci.antivirus.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.mtuci.antivirus.MainApplication;
import ru.mtuci.antivirus.animations.AnimationButtonsPane;

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
        AnimationButtonsPane.moveRight(buttonsPane);
    }

    @FXML
    void showButtonsPane(MouseEvent event) {
        AnimationButtonsPane.moveLeft(buttonsPane);
    }

    @FXML
    void initialize() {

        initSettings();

        mainButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onMainButtonClicked());
        profileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onProfileButtonClicked());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onExitButtonClicked());

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
        buttonsPane.setLayoutX(-300);
        switchToPane(mainPane);
        recolorButtonBackground(mainButtonBackground);
    }

    private void switchToPane(AnchorPane chosenPane) {
        mainPane.setVisible(false);
        profilePane.setVisible(false);

        chosenPane.setVisible(true);
    }

    private void recolorButtonBackground(AnchorPane chosenButtonBackground) {
        mainButtonBackground.setStyle("-fx-background-color: #6F8FAF; -fx-background-radius: 25");
        profileButtonBackground.setStyle("-fx-background-color: #6F8FAF; -fx-background-radius: 25");

        chosenButtonBackground.setStyle("-fx-background-color: #667594; -fx-background-radius: 25");
    }

    private void onMainButtonClicked() {
        switchToPane(mainPane);
        recolorButtonBackground(mainButtonBackground);
    }

    private void onProfileButtonClicked() {
        switchToPane(profilePane);
        recolorButtonBackground(profileButtonBackground);
    }

    private void onExitButtonClicked() {

        MainApplication.switchScene("templates/login-window.fxml");
    }

}
