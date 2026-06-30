package com.shinobi.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
public class SuccessScene {

    public Scene createScene(double time, double wpm) {

        Image bgImage = new Image(getClass().getResourceAsStream("/Mission Completed.png"));
        ImageView bgView = new ImageView(bgImage);

        Label timeWPM = new Label(String.format("Time Taken: %.2f seconds \n Typing Speed: %.1f WPM", time, wpm));
        timeWPM.setStyle("-fx-font-size: 40px; -fx-font-weight: 900; -fx-text-fill: #b7f36b;");

        Button continueButton = new Button("Continue Mission");
        continueButton.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-background-color: #0A1F44; -fx-text-fill: #b7f36b; -fx-padding: 12 28 12 28;");

        continueButton.setOnAction(e -> {
            System.out.println("Next Mission Coming Soon!");
        });
        VBox overlay = new VBox(20, timeWPM, continueButton);
        overlay.setAlignment(Pos.CENTER);
        overlay.setTranslateY(300);

        StackPane root = new StackPane(bgView, overlay);
        root.setStyle("-fx-background-color: #111111;");
        return new Scene(root, 1920, 1080);
    }
}
