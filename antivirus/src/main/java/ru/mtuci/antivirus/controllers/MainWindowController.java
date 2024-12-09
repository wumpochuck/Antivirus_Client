package ru.mtuci.antivirus.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ru.mtuci.antivirus.animations.AnimationHover;
import ru.mtuci.antivirus.animations.AnimationPageTransition;

public class MainWindowController {

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
    void onHomeButtonEntered(MouseEvent event) {
        AnimationHover.startFadeTransition(HomeButtonBackground, HomeButtonBackground.getOpacity(), 0.7);
    }

    @FXML
    void onHomeButtonExited(MouseEvent event) {
        AnimationHover.startFadeTransition(HomeButtonBackground, HomeButtonBackground.getOpacity(), 0.0);
    }

    @FXML
    void onLicenseButtonEntered(MouseEvent event) {
        AnimationHover.startFadeTransition(LicenseButtonBackground, LicenseButtonBackground.getOpacity(), 0.7);
    }

    @FXML
    void onLicenseButtonExited(MouseEvent event) {
        AnimationHover.startFadeTransition(LicenseButtonBackground, LicenseButtonBackground.getOpacity(), 0.0);
    }

    @FXML
    void onProfileButtonEntered(MouseEvent event) {
        AnimationHover.startFadeTransition(ProfileButtonBackground, ProfileButtonBackground.getOpacity(), 0.7);
    }

    @FXML
    void onProfileButtonExited(MouseEvent event) {
        AnimationHover.startFadeTransition(ProfileButtonBackground, ProfileButtonBackground.getOpacity(), 0.0);
    }

    @FXML
    void initialize() {
        initSettings();

        HomeButton.setOnMouseClicked(event -> onHomeButtonClicked());
        LicenseButton.setOnMouseClicked(event -> onLicenseButtonClicked());
        ProfileButton.setOnMouseClicked(event -> onProfileButtonClicked());

    }

    public void initSettings(){
        AnimationHover.applyHoverAnimation(HomeButtonBackground);
        AnimationHover.applyHoverAnimation(LicenseButtonBackground);
        AnimationHover.applyHoverAnimation(ProfileButtonBackground);

        onProfileButtonClicked();
    }

    public void onHomeButtonClicked(){
        AnimationPageTransition.animatePageTransition(MainPageBlocked, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        setAllButtonsDark();
        HomeButton.setImage(new Image("/static/home-white.png"));
    }

    public void onProfileButtonClicked(){
        AnimationPageTransition.animatePageTransition(LoginPage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        setAllButtonsDark();
        ProfileButton.setImage(new Image("/static/profile-white.png"));
    }

    public void onLicenseButtonClicked(){
        AnimationPageTransition.animatePageTransition(LicensePage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        setAllButtonsDark();
        LicenseButton.setImage(new Image("/static/license-white.png"));
    }

    public void setAllButtonsDark(){
        HomeButton.setImage(new Image("/static/home-dark.png"));
        LicenseButton.setImage(new Image("/static/license-dark.png"));
        ProfileButton.setImage(new Image("/static/profile-dark.png"));
    }

}
