package ru.mtuci.antivirus.animations;

import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ImageView;

public class AnimationHover {

    public static void applyHoverAnimation(Pane pane) {
        pane.setOnMouseEntered(event -> startFadeTransition(pane, pane.getOpacity(), 0.7));
        pane.setOnMouseExited(event -> startFadeTransition(pane, pane.getOpacity(), 0.0));
    }

    public static void startFadeTransition(Pane pane, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.play();
    }
}