package ru.mtuci.antivirus.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.mtuci.antivirus.MainApplication;
import ru.mtuci.antivirus.animations.*;
import ru.mtuci.antivirus.utils.MessageHandler;
import ru.mtuci.antivirus.utils.PipeHandler;

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
    private Button backButton; // Кнопка переключения на логин

    @FXML
    private Button loginButton; // Кнопка логина

    @FXML
    private TextField loginField1; // Логин - имя

    @FXML
    private TextField loginField2; // Регистрация - имя

    @FXML
    private PasswordField passwordField1; // Логин - пароль

    @FXML
    private PasswordField passwordField2; // Регистрация - пароль

    @FXML
    private TextField emailField; // Регистрация - пароль2

    @FXML
    private Button registerButton; // Кнопка регистрации

    @FXML
    private Button switchRegistrationButton; // Кнопка переключения на регистрацию

    private AnimationShake shake;

    @FXML
    void initialize() {


        initSettings();

        switchRegistrationButton.setOnAction(event -> {
            onSwitchToRegistration();
            clearText();
        });
        backButton.setOnAction(event -> {
            onSwitchToLogin();
            clearText();
        });
        loginButton.setOnAction(event -> {
            onLoginButtonClick();
            clearText();
        });
        registerButton.setOnAction(event -> {
            onRegisterButtonClick();
            clearText();
        });

        assert LoginPane != null : "fx:id=\"LoginPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert RegistrationPane != null : "fx:id=\"RegistrationPane\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField1 != null : "fx:id=\"loginField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginField2 != null : "fx:id=\"loginField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField1 != null : "fx:id=\"passwordField1\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordField2 != null : "fx:id=\"passwordField2\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert emailField != null : "fx:id=\"passwordField3\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert switchRegistrationButton != null : "fx:id=\"switchRegistrationButton\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }

    private void initSettings() {
        LoginPane.setVisible(true);
        LoginPane.setDisable(false);
        RegistrationPane.setVisible(true);
        RegistrationPane.setDisable(true);
    }

    private void clearText() {
        loginField1.setText("");
        loginField2.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
        emailField.setText("");
    }

    private void onSwitchToRegistration() {
        AnimationSwitchWindow.switchToRegistration(LoginPane, RegistrationPane);
    }

    private void onSwitchToLogin() {
        AnimationSwitchWindow.switchToLogin(LoginPane, RegistrationPane);
    }

    public void onLoginButtonClick() {
        String login = loginField1.getText();
        String password = passwordField1.getText();

        // DELETE WHEN CLIENT WILL BE READY
        if(login.equals("admin") && password.equals("admin")){
            MessageHandler.showOk("Login completed");
            MainApplication.switchScene("templates/main-window.fxml");
            return;
        }
        // DELETE WHEN CLIENT WILL BE READY

        PipeHandler pipeHandler = new PipeHandler();
        String response = pipeHandler.sendLoginData(login, password);
        System.out.println("onLoginButtonClick: response: " + response);

        if (response == null){
            MessageHandler.showError("Server is not available");
            return;
        }
        if (response.contains("Validation error: User not found")) {
            MessageHandler.showError("User not found");
            return;
        }
        if(response.contains("password cannot be empty") || response.contains("login cannot be empty")){
            MessageHandler.showWarning("Fields cannot be empty");
            return;
        }
        if(response.contains("Validation error: Password is incorrect")) {
            PlayShakeAnimation();
            return;
        }
        if(response.contains("JWT{")){
            MessageHandler.showOk(response);
            MainApplication.switchScene("templates/main-window.fxml");
            return;
        }

        MessageHandler.showError("Internal server error");


    }

    public void onRegisterButtonClick() {
        String login = loginField2.getText();
        String password = passwordField2.getText();
        String email = emailField.getText();

        PipeHandler pipeHandler = new PipeHandler();
        String response = pipeHandler.sendRegistrationData(login, password, email);
        System.out.println("onRegisterButtonClick: response: " + response);

        if(response == null){
            MessageHandler.showError("Server is not available");
            return;
        }
        if(response.contains("password cannot be empty") || response.contains("login cannot be empty") || response.contains("email cannot be empty")){
            MessageHandler.showWarning("Fields cannot be empty");
            return;
        }
        if(response.contains("email should be valid")) {
            MessageHandler.showError("Email should be valid");
            return;
        }
        if(response.contains("Validation error:  User with this login already exists")) {
            MessageHandler.showError("User already exists");
            return;
        }
        if(response.contains("JWT{")){
            MessageHandler.showOk("Registration completed,\nYou can now login");
            return;
        }

        MessageHandler.showError("Internal server error");

    }

    public void PlayShakeAnimation() {
        new AnimationShake(passwordField1).play();
    }


}