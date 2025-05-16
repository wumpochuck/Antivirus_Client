package ru.mtuci.antivirus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.mtuci.antivirus.utils.PipeHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {

    private static Stage primaryStage;
    private static boolean inTrayMode = true;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("templates/main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Antikripus");

        Platform.setImplicitExit(false);
        createTrayIcon();

        // Settings
        primaryStage.setResizable(false);

        if(inTrayMode){
            primaryStage.hide();
        }else{
            primaryStage.show();
        }


    }

    private void createTrayIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray not supported!");
            Platform.exit();
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(MainApplication.class.getResource("/static/icon.png"));

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
        MenuItem openItem = new MenuItem("Показать Antivirus");
        openItem.addActionListener(showListener);
        popup.add(openItem);

        popup.addSeparator();

        MenuItem exitItem = new MenuItem("Выход");
        exitItem.addActionListener(e -> {
            String response = PipeHandler.sendExitRequest();
            if(response.equals("true")) {
                String response1 = PipeHandler.sendCloseServiceRequest();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                // Вот тут почему то не хочет отправляться запрос службе на закрытие

                tray.remove(tray.getTrayIcons()[0]);
                Platform.exit();
                System.exit(0);
            }
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
        primaryStage.show();
        primaryStage.toFront();
    }

    public static void main(String[] args) {

        if(args.length > 0){
            if(args[0].equals("--no-tray")){
                inTrayMode = false;
            }
        }

        launch();
    }
}