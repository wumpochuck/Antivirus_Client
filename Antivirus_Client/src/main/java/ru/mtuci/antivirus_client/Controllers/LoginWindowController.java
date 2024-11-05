package ru.mtuci.antivirus_client.Controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.mtuci.antivirus_client.Animations.*;

public class LoginWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private AnchorPane RegistrationPane;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField1;

    @FXML
    private TextField loginField2;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private PasswordField passwordField3;

    @FXML
    private Button registerButton;

    @FXML
    private Button switchRegistrationButton;

    @FXML
    void initialize() {
        initSettings();

        switchRegistrationButton.setOnAction(event -> { onSwitchToRegistration(); clearText(); });
        backButton.setOnAction(event -> { onSwitchToLogin(); clearText(); });
        loginButton.setOnAction(event ->  { onLoginButtonClick(); clearText(); });
        registerButton.setOnAction(event ->  { onRegisterButtonClick(); clearText(); });

        assert LoginPane != null : "fx:id=\"LoginPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert RegistrationPane != null : "fx:id=\"RegistrationPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField1 != null : "fx:id=\"loginField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField2 != null : "fx:id=\"loginField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField1 != null : "fx:id=\"passwordField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField2 != null : "fx:id=\"passwordField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField3 != null : "fx:id=\"passwordField3\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert switchRegistrationButton != null : "fx:id=\"switchRegistrationButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }

    private void initSettings(){
        LoginPane.setVisible(true);
        LoginPane.setDisable(false);
        RegistrationPane.setVisible(true);
        RegistrationPane.setDisable(true);
    }

    private void clearText(){
        loginField1.setText("");
        loginField2.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
        passwordField3.setText("");
    }

    private void onSwitchToRegistration() {
        AnimationSwitchWindow.switchToRegistration(LoginPane, RegistrationPane);
    }

    private void onSwitchToLogin() {
        AnimationSwitchWindow.switchToLogin(LoginPane, RegistrationPane);
    }

    public void onLoginButtonClick(){
        String login = loginField1.getText();
        String password = passwordField1.getText();
        System.out.println("Логин: " + login);
        System.out.println("Пароль: " + password);
    }

    public void onRegisterButtonClick(){
        String login = loginField2.getText();
        String password = passwordField2.getText();
        String repeatPassword = passwordField3.getText();
        System.out.println("Логин: " + login);
        System.out.println("Пароль: " + password);
        System.out.println("Повтор пароля: " + repeatPassword);
    }

    public void PlayShakeAnimation(){
        new AnimationShake(passwordField1).play();
    }



}