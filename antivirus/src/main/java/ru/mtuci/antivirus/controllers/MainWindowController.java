package ru.mtuci.antivirus.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ButtonsPane;

    @FXML
    private ImageView HomeButton;

    @FXML
    private Pane HomeButtonBackground;

    @FXML
    private ImageView LicenseButton;

    @FXML
    private Pane LicenseButtonBackground;

    @FXML
    private AnchorPane LicensePage;

    @FXML
    private AnchorPane LoginPage;

    @FXML
    private AnchorPane MainPage;

    @FXML
    private AnchorPane MainPageBlocked;

    @FXML
    private AnchorPane MainWindow;

    @FXML
    private ImageView ProfileButton;

    @FXML
    private Pane ProfileButtonBackground;

    @FXML
    private AnchorPane ProfilePage;

    @FXML
    private Button licenseActivateButton;

    @FXML
    private TextField licenseActivateKeyText;

    @FXML
    private Text licenseText;

    @FXML
    private Button licenseUpdateButton;

    @FXML
    private TextField licenseUpdateKeyText;

    @FXML
    private TextField licenseUpdateLoginText;

    @FXML
    private TextField licenseUpdatePasswordText;

    @FXML
    private Button logButton;

    @FXML
    private TextField logLoginField;

    @FXML
    private TextField logPasswordField;

    @FXML
    private ToggleButton logShowPassword;

    @FXML
    private ImageView profileAvatar;

    @FXML
    private Button profileExitButton;

    @FXML
    private TextField profileNewEmailText;

    @FXML
    private TextField profileNewLoginText;

    @FXML
    private TextField profileNewPasswordText;

    @FXML
    private Text profileText;

    @FXML
    private Button profileUpdateButton;

    @FXML
    private Button profileUpdateInfoButton;

    @FXML
    private AnchorPane profileUpdatePane;

    @FXML
    private TextField profileUpdatePasswordText;

    @FXML
    private Button regButton;

    @FXML
    private TextField regEmailField;

    @FXML
    private TextField regLoginField;

    @FXML
    private TextField regPasswordField;

    @FXML
    private ToggleButton regShowPassword;

    @FXML
    void initialize() {
        initSettings();

        assert ButtonsPane != null : "fx:id=\"ButtonsPane\" was not injected: check your FXML file 'main-window.fxml'.";
        assert HomeButton != null : "fx:id=\"HomeButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert HomeButtonBackground != null : "fx:id=\"HomeButtonBackground\" was not injected: check your FXML file 'main-window.fxml'.";
        assert LicenseButton != null : "fx:id=\"LicenseButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert LicenseButtonBackground != null : "fx:id=\"LicenseButtonBackground\" was not injected: check your FXML file 'main-window.fxml'.";
        assert LicensePage != null : "fx:id=\"LicensePage\" was not injected: check your FXML file 'main-window.fxml'.";
        assert LoginPage != null : "fx:id=\"LoginPage\" was not injected: check your FXML file 'main-window.fxml'.";
        assert MainPage != null : "fx:id=\"MainPage\" was not injected: check your FXML file 'main-window.fxml'.";
        assert MainPageBlocked != null : "fx:id=\"MainPageBlocked\" was not injected: check your FXML file 'main-window.fxml'.";
        assert MainWindow != null : "fx:id=\"MainWindow\" was not injected: check your FXML file 'main-window.fxml'.";
        assert ProfileButton != null : "fx:id=\"ProfileButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert ProfileButtonBackground != null : "fx:id=\"ProfileButtonBackground\" was not injected: check your FXML file 'main-window.fxml'.";
        assert ProfilePage != null : "fx:id=\"ProfilePage\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseActivateButton != null : "fx:id=\"licenseActivateButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseActivateKeyText != null : "fx:id=\"licenseActivateKeyText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseText != null : "fx:id=\"licenseText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseUpdateButton != null : "fx:id=\"licenseUpdateButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseUpdateKeyText != null : "fx:id=\"licenseUpdateKeyText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseUpdateLoginText != null : "fx:id=\"licenseUpdateLoginText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert licenseUpdatePasswordText != null : "fx:id=\"licenseUpdatePasswordText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert logButton != null : "fx:id=\"logButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert logLoginField != null : "fx:id=\"logLoginField\" was not injected: check your FXML file 'main-window.fxml'.";
        assert logPasswordField != null : "fx:id=\"logPasswordField\" was not injected: check your FXML file 'main-window.fxml'.";
        assert logShowPassword != null : "fx:id=\"logShowPassword\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileAvatar != null : "fx:id=\"profileAvatar\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileExitButton != null : "fx:id=\"profileExitButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileNewEmailText != null : "fx:id=\"profileNewEmailText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileNewLoginText != null : "fx:id=\"profileNewLoginText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileNewPasswordText != null : "fx:id=\"profileNewPasswordText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileText != null : "fx:id=\"profileText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileUpdateButton != null : "fx:id=\"profileUpdateButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileUpdateInfoButton != null : "fx:id=\"profileUpdateInfoButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileUpdatePane != null : "fx:id=\"profileUpdatePane\" was not injected: check your FXML file 'main-window.fxml'.";
        assert profileUpdatePasswordText != null : "fx:id=\"profileUpdatePasswordText\" was not injected: check your FXML file 'main-window.fxml'.";
        assert regButton != null : "fx:id=\"regButton\" was not injected: check your FXML file 'main-window.fxml'.";
        assert regEmailField != null : "fx:id=\"regEmailField\" was not injected: check your FXML file 'main-window.fxml'.";
        assert regLoginField != null : "fx:id=\"regLoginField\" was not injected: check your FXML file 'main-window.fxml'.";
        assert regPasswordField != null : "fx:id=\"regPasswordField\" was not injected: check your FXML file 'main-window.fxml'.";
        assert regShowPassword != null : "fx:id=\"regShowPassword\" was not injected: check your FXML file 'main-window.fxml'.";

    }

    public void initSettings(){
        hideAndDisableAllPages(LoginPage);
    }

    public void hideAndDisableAllPages(AnchorPane visibleAndEnabledPage){
        LoginPage.setVisible(false);
        LoginPage.setDisable(true);

        MainPage.setVisible(false);
        MainPage.setDisable(true);

        MainPageBlocked.setVisible(false);
        MainPageBlocked.setDisable(true);

        MainPageBlocked.setVisible(false);
        MainPageBlocked.setDisable(true);

        LicensePage.setVisible(false);
        LicensePage.setDisable(true);

        visibleAndEnabledPage.setVisible(true);
        visibleAndEnabledPage.setDisable(false);
    }

}
