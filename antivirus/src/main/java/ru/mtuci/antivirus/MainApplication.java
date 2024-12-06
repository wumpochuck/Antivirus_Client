package ru.mtuci.antivirus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainApplication extends Application {

    private static Stage primaryStage;

//    @Override
//    public void start(Stage stage) throws IOException {
//        primaryStage = stage;
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("templates/login-window.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
//        primaryStage.setTitle("Antivirus");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Antivirus");

        Platform.setImplicitExit(false);
        createTrayIcon();

        // Initially hide the stage
        primaryStage.hide();
    }

    private void createTrayIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            Platform.exit();
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        // Get image from /resources/static/
        Image image = Toolkit.getDefaultToolkit().getImage(MainApplication.class.getResource("/static/house-icon.png"));

        ActionListener showListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(() -> {
                    try {
                        showStage();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        };

        PopupMenu popup = new PopupMenu();
        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(showListener);
        popup.add(openItem);

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> {
            tray.remove(tray.getTrayIcons()[0]);
            Platform.exit();
            System.exit(0);
        });
        popup.add(exitItem);

        TrayIcon trayIcon = new TrayIcon(image, "Antivirus", popup);
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(showListener);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
    }

    private void showStage() throws IOException {
        if (primaryStage.getScene() == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("templates/login-window.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            primaryStage.setScene(scene);
        }
        primaryStage.show();
        primaryStage.toFront();
    }

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}