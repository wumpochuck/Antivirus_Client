package ru.mtuci.antivirus.animations;

import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AnimationPageTransition {

    public static void hideAndDisableAllPages(AnchorPane... pages) {
        for (AnchorPane page : pages) {
            page.setVisible(false);
            page.setDisable(true);
        }
    }

    public static void animatePageTransition(AnchorPane newPage, AnchorPane... allPages) {
        hideAndDisableAllPages(allPages);

        newPage.setVisible(true);
        newPage.setDisable(false);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), newPage);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}