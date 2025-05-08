package ru.mtuci.antivirus.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ru.mtuci.antivirus.MainApplication;
import ru.mtuci.antivirus.animations.AnimationHover;
import ru.mtuci.antivirus.animations.AnimationPageTransition;
import ru.mtuci.antivirus.utils.MessageHandler;
import ru.mtuci.antivirus.utils.PipeHandler;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

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
    private PasswordField logPasswordFieldHidden;

    @FXML
    private TextField logPasswordFieldShow;

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
    private AnchorPane licenseActivatePane;

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
    private PasswordField regPasswordFieldHidden;

    @FXML
    private TextField regPasswordFieldShow;

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

    // Global variables for rules checking

    private String LOGIN = "";
    private String TICKET = "";
    private String DEVICE_NAME = "";
    private String MAC_ADDRESS = "";
    private boolean IS_AUTHORIZED = false;
    private boolean IS_HAVE_LICENSE = false;

    // Other variables

    private ScheduledExecutorService scheduler;

    @FXML
    void initialize() {
        getDeviceNameAndMacAddress();
        initSettings();

        /// Control buttons
        HomeButton.setOnMouseClicked(event -> onHomeButtonClicked());
        LicenseButton.setOnMouseClicked(event -> onLicenseButtonClicked());
        ProfileButton.setOnMouseClicked(event -> onProfileButtonClicked());

        /// Profile buttons
        logShowPassword.setOnMouseClicked(event -> toggleHidePassword(logPasswordFieldShow, logPasswordFieldHidden));
        regShowPassword.setOnMouseClicked(event -> toggleHidePassword(regPasswordFieldShow, regPasswordFieldHidden));
        logButton.setOnMouseClicked(event -> onLoginButtonClicked());
        regButton.setOnMouseClicked(event -> onRegisterButtonClicked());
        profileUpdateInfoButton.setOnMouseClicked(event -> onUpdateProfileInfoClicked());
        profileExitButton.setOnMouseClicked(event -> onProfileExitClicked());

        profileUpdateButton.setOnMouseClicked(event -> onUpdateButtonClicked());

        /// License buttons
        licenseActivateButton.setOnMouseClicked(event -> onLicenseActivateClicked());
        licenseUpdateButton.setOnMouseClicked(event -> onLicenseUpdateClicked());

    }

    /// Thread methods (For ticket, JWT)

    private void startLicenseUpdateScheduler() {
        if (scheduler == null || scheduler.isShutdown()) { // Check if the scheduler is already running
            scheduler = Executors.newScheduledThreadPool(1); // Create a scheduler with a single thread
            System.out.println("Creating a scheduler with a single thread\n");
            scheduler.scheduleAtFixedRate(this::updatingThread, 0, 1, TimeUnit.MINUTES); // Run every 1 minute (change to 60 mins later)
        } else {
            System.out.println("Scheduler is already running\n");
        }
    }

    // Method to update license information
    private void updatingThread() {
        Platform.runLater(() -> {
            try {
                System.out.println("Updating license information and jwt...");
                checkForLicense();
                sleep(100);
//                updateJWT();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Method to stop the scheduler
    public void onClose() {
        if (scheduler != null && !scheduler.isShutdown()) {
            System.out.println("Shutting down the scheduler on application close\n");
            scheduler.shutdown(); // Shut down the scheduler when the application closes
            try {
                // Wait for all tasks to finish
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing task termination\n");
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.out.println("Termination was interrupted\n");
                scheduler.shutdownNow(); // Force termination in case of interruption
            }
        } else {
            System.out.println("Scheduler was already stopped or not started\n");
        }
    }

    /// With app launching ------------------------------------------------------------------------

    public void initSettings(){
        AnimationHover.applyHoverAnimation(HomeButtonBackground);
        AnimationHover.applyHoverAnimation(LicenseButtonBackground);
        AnimationHover.applyHoverAnimation(ProfileButtonBackground);

        checkForAuthorization();
        onProfileButtonClicked();
    }

    public void checkForAuthorization(){

        // Pipe request with login response or "false"
        LOGIN = PipeHandler.checkAuthorization();
        if(!LOGIN.equals("false")){
            IS_AUTHORIZED = true;
            // startLicenseUpdateScheduler();
        }

        if(IS_AUTHORIZED){
            switchButtonsEnabled(true);

            /// Set unique profile image for any user
            setUniqueAvatar();
            // checkForLicense();
            startLicenseUpdateScheduler();

        } else {
            switchButtonsEnabled(false);
        }
    }

    public void checkForLicense(){
        String response = PipeHandler.getLicenseInfo(MAC_ADDRESS);
        if(response == null){
            licenseActivateKeyText.setPromptText("Не удалось получить данные");
        }else if(response.contains("License for this device not found or blocked")){
            IS_HAVE_LICENSE = false;
            licenseText.setText("Текущая лицензия: \nЛицензия не найдена или заблокирована");
            licenseActivatePane.setDisable(false);
            licenseActivateKeyText.setPromptText("Введите код лицензии");
        }
        else if(response.contains("License expired")){
            IS_HAVE_LICENSE = false;
            licenseText.setText("Текущая лицензия: \nСрок действия лицензии истек, она была заблокирована");
            licenseActivatePane.setDisable(false);
//            licenseActivateKeyText.setText("Лицензия активирована");
            licenseActivateKeyText.setPromptText("Введите код лицензии");
        }
        else if(response.contains("Ticket{")){
            TICKET = response.substring(response.indexOf("Ticket{") + 7, response.indexOf("}"));
            licenseText.setText("Текущая лицензия: " + ticketToText(TICKET));

            IS_HAVE_LICENSE = true;
            licenseActivateKeyText.setPromptText("Лицензия активирована");
            licenseActivatePane.setDisable(true);
        }else{
            licenseActivateKeyText.setPromptText("Лицензия не активирована");
        }
//        String licenseCode = PipeHandler.checkActivation();
//        if(licenseCode != null){
//
//            IS_HAVE_LICENSE = true;
//            licenseActivateKeyText.setText(licenseCode);
//            licenseActivatePane.setDisable(true);
//            String response = PipeHandler.getActiveLicense(MAC_ADDRESS, licenseCode);
//            if(response == null){
//                System.out.println("checkForAuthorization: response is null, check server status");
//                return;
//            }
//            TICKET = response.substring(response.indexOf("Ticket{") + 7, response.indexOf("}"));
//            licenseText.setText("Текущая лицензия: " + ticketToText(TICKET));
//
//        }
    }

//    public void updateJWT(){
//        String response = PipeHandler.updateJWT();
//        System.out.println("updateJWT: response: " + response);
//
//        if(response.contains("Login completed")){
//            System.out.println("JWT updated");
//        }else{
//            MessageHandler.showWarning("Возникла ошибка авторизации\nПопробуйте перезайти в аккаунт");
//            onProfileExitClicked();
//        }
//    }

    /// Profile page Actions ----------------------------------------------------------------------

    public void onProfileButtonClicked(){
        if(IS_AUTHORIZED){
            AnimationPageTransition.animatePageTransition(ProfilePage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        }else{
            AnimationPageTransition.animatePageTransition(LoginPage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        }

        setAllButtonsWhite();

        profileText.setText("Здравствуйте, " + LOGIN);

        profileUpdatePane.setOpacity(0.0);
        profileUpdatePane.setDisable(true);
        profileUpdateInfoButton.setText("Обновить профиль");

        ProfileButton.setImage(new Image("/static/profile-dark.png"));
    }

    public void onLoginButtonClicked(){
        String login = logLoginField.getText();
        String password = (logPasswordFieldHidden.isVisible()) ? logPasswordFieldHidden.getText() : logPasswordFieldShow.getText();

        System.out.println("Login: " + login + " Password: " + password);

        // Validate for empty fields
        if(login.isEmpty() || password.isEmpty()){
            MessageHandler.showWarning("Заполните все поля!");
            return;
        }

        // Validate for not allowed symbols
        if(validateString(login) || validateString(password)){
            MessageHandler.showWarning("Недопустимые символы!\nРазрешены только английские буквы\nи цифры,а также \"-\" и \"_\"");
            return;
        }

        // Pipe request
        String response = PipeHandler.sendLoginData(login, password);
        System.out.println("onLoginButtonClicked: response: " + response);

        handleResponse(response);

        if(response != null && response.contains("Login completed")){
            MessageHandler.showOk("Вход выполнен");
            switchButtonsEnabled(true);
            IS_AUTHORIZED = true;
            LOGIN = login;

            setUniqueAvatar();

            onProfileButtonClicked();

            // Check for license activation
            startLicenseUpdateScheduler();
            // checkForLicense();

            return;
        }

    }

    public void onRegisterButtonClicked(){
        String email = regEmailField.getText();
        String login = regLoginField.getText();
        String password = (regPasswordFieldHidden.isVisible()) ? regPasswordFieldHidden.getText() : regPasswordFieldShow.getText();

        // Validate for empty fields
        if(email.isEmpty() || login.isEmpty() || password.isEmpty()){
            MessageHandler.showWarning("Заполните все поля!");
            return;
        }

        // Validate for not allowed symbols
        if(validateString(login) || validateString(password) || validateString(email)){
            MessageHandler.showWarning("Недопустимые символы!\nРазрешены только англисйкие буквы\nи цифры,а также \"-\" и \"_\"");
            return;
        }

        // Validate for length
        if(login.length() < 4 || password.length() < 4){
            MessageHandler.showWarning("Слишком короткий логин или пароль!\nМинимальная длина - 4 символа");
            return;
        }

        // Pipe request
        String response = PipeHandler.sendRegistrationData(login, password, email);
        System.out.println("onRegisterButtonClicked: response: " + response);

        handleResponse(response);

        if(response != null && response.contains("Registration completed")){
            MessageHandler.showOk("Регистрация завершена");
            switchButtonsEnabled(true);
            IS_AUTHORIZED = true;
            LOGIN = login;

            setUniqueAvatar();
            startLicenseUpdateScheduler();
            onProfileButtonClicked();
        }

    }

    public void onUpdateProfileInfoClicked(){
        if(!profileUpdatePane.isDisable()){
            profileUpdateInfoButton.setText("Обновить профиль");
            AnimationHover.startFadeTransition(profileUpdatePane, 1.0, 0.0);
            // profileUpdatePane.setVisible(false);
            profileUpdatePane.setDisable(true);
        }else{
            profileUpdateInfoButton.setText("Закрыть");
            // profileUpdatePane.setVisible(true);
            profileUpdatePane.setDisable(false);
            AnimationHover.startFadeTransition(profileUpdatePane, 0.0,1.0);
        }
    }

    public void onUpdateButtonClicked(){
        String newLogin = profileNewLoginText.getText();
        String newEmail = profileNewEmailText.getText();
        String newPassword = profileNewPasswordText.getText();

        String password = profileUpdatePasswordText.getText();
        if(password.isEmpty()){
            MessageHandler.showWarning("Введите текущий пароль для подтверждения");
            return;
        }

        if(newLogin.isEmpty()){ newLogin = "null"; }
        if(newEmail.isEmpty()){ newEmail = "null"; }
        if(newPassword.isEmpty()){ newPassword = "null"; }

        System.out.println("onUpdateButtonClicked: newLogin: " + newLogin + " newEmail: " + newEmail + " newPassword: " + newPassword);

        // Validate for not allowed symbols
        if(validateString(newLogin) || validateString(newPassword) || validateString(newEmail)){
            MessageHandler.showWarning("Недопустимые символы!\nРазрешены только английские буквы\nи цифры,а также \"-\" и \"_\"");
            return;
        }

        // Validate for length
        if(newLogin.length() < 4 && !newLogin.equals("null") || newPassword.length() < 4 && !newPassword.equals("null")){
            MessageHandler.showWarning("Слишком короткий логин или пароль!\nМинимальная длина - 4 символа");
            return;
        }


        String response = PipeHandler.sendUpdateUserData(newLogin, newPassword, newEmail, password);

        System.out.println("onUpdateButtonClicked: response: " + response);

        handleResponse(response);

        if(response.contains("Update completed")){
            MessageHandler.showOk("Данные обновлены. Используйте новые данные для входа");

            onProfileExitClicked();
        }

    }

    public void onProfileExitClicked(){
        clearTextFields();

        PipeHandler.clearAuthorization();
        IS_AUTHORIZED = false;

        IS_HAVE_LICENSE = false;
        licenseActivatePane.setDisable(false);

        LOGIN = "";
        TICKET = "";
        switchButtonsEnabled(false);
        onProfileButtonClicked();
        onClose();
    }

    /// Home page Actions -------------------------------------------------------------------------

    public void onHomeButtonClicked(){
        if(IS_HAVE_LICENSE){
            AnimationPageTransition.animatePageTransition(MainPage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        }else{
            AnimationPageTransition.animatePageTransition(MainPageBlocked, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        }
        setAllButtonsWhite();
        HomeButton.setImage(new Image("/static/home-dark.png"));
    }

    /// License page Actions ----------------------------------------------------------------------

    public void onLicenseButtonClicked(){
        AnimationPageTransition.animatePageTransition(LicensePage, LoginPage, MainPage, MainPageBlocked, ProfilePage, LicensePage);
        setAllButtonsWhite();
        LicenseButton.setImage(new Image("/static/license-dark.png"));

    }

    public void onLicenseActivateClicked(){
        String activationCode = licenseActivateKeyText.getText();

        if (activationCode == null || activationCode.isEmpty()) {
            MessageHandler.showWarning("Не введен ключ");
            return;
        }

        String response = PipeHandler.sendActivationData(activationCode, DEVICE_NAME, MAC_ADDRESS);

        handleResponse(response);

        if (response != null && response.contains("License activated")) {
            MessageHandler.showOk("Лицензия активирована");
            IS_HAVE_LICENSE = true;
            TICKET = response.substring(response.indexOf("Ticket{") + 7, response.indexOf("}"));
            licenseText.setText("Текущая лицензия: " + ticketToText(TICKET));

            licenseActivateKeyText.setPromptText("Лицензия активирована");
            licenseActivatePane.setDisable(true);
        }
    }

    public void onLicenseUpdateClicked(){
        String activationCode = licenseUpdateKeyText.getText();
        String login = licenseUpdateLoginText.getText();
        String password = licenseUpdatePasswordText.getText();


//        String activationCode = licenseActivateKeyText.getText();

        if (activationCode == null || activationCode.isEmpty()) {
            MessageHandler.showWarning("Не введен ключ");
            return;
        }

        if (login == null || login.isEmpty()) {
            MessageHandler.showWarning("Не введен логин");
            return;
        }

        if (password == null || password.isEmpty()) {
            MessageHandler.showWarning("Не введен пароль");
            return;
        }

        String response = PipeHandler.updateLicense(login, password, activationCode, MAC_ADDRESS);

        handleResponse(response);

        if (response != null && response.contains("License update successful")) {
            MessageHandler.showOk("Лицензия проделена");
            IS_HAVE_LICENSE = true;
            TICKET = response.substring(response.indexOf("Ticket{") + 7, response.indexOf("}"));
            licenseText.setText("Текущая лицензия: " + ticketToText(TICKET));
        }
    }

    /// Other methods -----------------------------------------------------------------------------

    public void setAllButtonsWhite(){
        HomeButton.setImage(new Image("/static/home-white.png"));
        LicenseButton.setImage(new Image("/static/license-white.png"));
        ProfileButton.setImage(new Image("/static/profile-white.png"));
    }

    public void switchButtonsEnabled(boolean enabled){
        HomeButton.setDisable(!enabled);
        HomeButton.setVisible(enabled);
        HomeButtonBackground.setVisible(enabled);

        LicenseButton.setDisable(!enabled);
        LicenseButton.setVisible(enabled);
        LicenseButtonBackground.setVisible(enabled);

    }

    public void toggleHidePassword(TextField passwordShow, PasswordField passwordHide){
        if(passwordHide.isVisible()){
            passwordHide.setVisible(false);
            passwordHide.setDisable(true);
            passwordShow.setText(passwordHide.getText());
        }else{
            passwordHide.setVisible(true);
            passwordHide.setDisable(false);
            passwordHide.setText(passwordShow.getText());
        }
    }

    public boolean validateString(String str){
        Pattern pattern = Pattern.compile("[" + Pattern.quote(
                ":!#$%^&*()\"'`{}<>/?," +
                "абвгдеёжзийклмнопрстуфхцчшщбыъэюя" +
                "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ") + "]");
        return pattern.matcher(str).find();
    }

    public void clearTextFields(){
        logLoginField.setText("");
        logPasswordFieldHidden.setText("");
        logPasswordFieldShow.setText("");
        regLoginField.setText("");
        regPasswordFieldHidden.setText("");
        regPasswordFieldShow.setText("");
        regEmailField.setText("");

        profileNewEmailText.setText("");
        profileNewLoginText.setText("");
        profileNewPasswordText.setText("");
        profileUpdatePasswordText.setText("");

        licenseActivateKeyText.setText("");
        licenseText.setText("Текущая лицензия: \nОтсутствует");
    }

    public void getDeviceNameAndMacAddress(){
        try{
            InetAddress ip = InetAddress.getLocalHost();
            // System.out.println("Device name: " + ip.getHostName());
            DEVICE_NAME = ip.getHostName();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            // System.out.println("Mac address: " + sb.toString());
            MAC_ADDRESS = sb.toString();

        }catch (Exception e){
            MessageHandler.showError("Ошибка при получении Mac-адреса");
        }
    }

    public void handleResponse(String response) {
        if (response == null || response.contains("Internal server error")) {
            MessageHandler.showError("Сервер недоступен, повторите попытку позже");
            System.out.println("handleResponse: response: " + response);
            return;
        }

        Map<String, String> errorMessages = new HashMap<>();

        // Login
        errorMessages.put("User not found", "Пользователь не найден");
        errorMessages.put("cannot be empty", "Заполните все поля правильно");
        errorMessages.put("Incorrect password", "Неправильный пароль");
        errorMessages.put("Multiple active sessions detected. All sessions blocked.","Обнаружено несколько сессий. Аккаунт заблокирован");

        // Registration
        errorMessages.put("should be valid", "Заполните все поля правильно");
        errorMessages.put("User with this login already exists","Пользователь с таким логином уже существует");
        errorMessages.put("User with this email already exists", "Пользователь с таким email уже существует");

        // Activation
        errorMessages.put("Validation error: User is not authenticated", "Попытка мошенничества!");
        errorMessages.put("Validation error: Device already registered by another user", "Устройство уже зарегистрировано другим пользователем");
        errorMessages.put("Validation error: License not found", "Лицензия не найдена");
        errorMessages.put("Validation error: License already activated", "Лицензия уже активирована");
        errorMessages.put("License for this device not found or blocked", "Лицензия заблокирована");
        errorMessages.put("License is expired", "Срок действия лицензии истек, она была заблокирована");
        errorMessages.put("Device count exceeded", "Превышено количество устройств");

        //Info
        errorMessages.put("Validation error: Device not found", "Устроиство не найдено");

        // Update user
        errorMessages.put("Validation error: login already exists", "Пользователь с таким логином уже существует");
        errorMessages.put("Validation error: email already exists", "Пользователь с таким email уже существует");

        errorMessages.put("Internal Server Error", "Ошибка сервера, попробуйте позже");

        for (Map.Entry<String, String> entry : errorMessages.entrySet()) {
            if (response.contains(entry.getKey())) {
                MessageHandler.showError(entry.getValue());
                return;
            }
        }
    }

    public String ticketToText(String ticket) {
        try {
            String currentDate = ticket.substring(ticket.indexOf("currentDate=") + 12, ticket.indexOf(", lifetime"));
            String lifetime = ticket.substring(ticket.indexOf("lifetime=") + 9, ticket.indexOf(", activationDate"));
            String activationDate = ticket.substring(ticket.indexOf("activationDate=") + 15, ticket.indexOf(", expirationDate"));
            String expirationDate = ticket.substring(ticket.indexOf("expirationDate=") + 15, ticket.indexOf(", userId"));
            // String userId = ticket.substring(ticket.indexOf("userId=") + 7, ticket.indexOf(", deviceId"));
            // String deviceId = ticket.substring(ticket.indexOf("deviceId=") + 9, ticket.indexOf(", isBlocked"));
            String isBlocked = ticket.substring(ticket.indexOf("isBlocked=") + 10, ticket.indexOf(", signature"));
            // String signature = ticket.substring(ticket.indexOf("signature=") + 10);

            return String.format(
                    "\nДата проверки: %s\n" +
                    "Срок действия тикета: %s\n" +
                    "Дата активации лицензии: %s\n" +
                    "Дата истечения лицензии: %s\n" +
                    // "Пользователь: %s\n" +
                    // "Устройство: %s\n" +
                    "Заблокирована: %s\n",// +
                    // "Подпись: %s\n",
                    currentDate, lifetime, activationDate, expirationDate, /*userId, deviceId, */ isBlocked /*, signature*/
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при обработке\nтикета, попробуйте перезайти\nна страницу";
        }
    }

    public void setUniqueAvatar(){
        profileAvatar.setImage(new Image(String.valueOf(MainApplication.class.getResource("/static/profile-icons/profile_icon_" + (LOGIN.hashCode() % 5 + 1) + ".png"))));
    }
}
