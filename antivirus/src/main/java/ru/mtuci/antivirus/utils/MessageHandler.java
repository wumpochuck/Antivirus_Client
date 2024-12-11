package ru.mtuci.antivirus.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class MessageHandler {

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        applyCustomStyle(alert);
        alert.showAndWait();
    }

    public static void showOk(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        applyCustomStyle(alert);
        alert.showAndWait();
    }

    public static void showWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        applyCustomStyle(alert);
        alert.showAndWait();
    }

    private static void applyCustomStyle(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill:  #172A3A; -fx-font-weight: bold; -fx-font-size: 15px;");
    }


}