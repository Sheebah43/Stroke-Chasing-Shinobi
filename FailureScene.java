package com.shinobi.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FailureScene {

    public Scene createScene(Stage stage) {

        Image bgImage = new Image(getClass().getResourceAsStream("/Mission Failed.png"));
        ImageView bgView = new ImageView(bgImage);
        Label failureLabel = new Label("SHINOBI FELL OFF!\nTHE SCROLL REMAINS UNRECOVERED.");

        failureLabel.setStyle("-fx-font-size: 34px;" + "-fx-font-weight: bold;" + "-fx-text-fill: yellow;" );
        failureLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        failureLabel.setAlignment(Pos.CENTER);
        failureLabel.setTranslateY(-20);

        Button retryButton = new Button("Retry Mission");
        retryButton.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" + "-fx-background-color: #cc0000;" + "-fx-text-fill: #fff229;" + "-fx-padding: 12 28 12 28;");

        retryButton.setTranslateY(28);

        retryButton.setOnAction(e -> { 
                TypingScene typingScene = new TypingScene();
                stage.setScene(typingScene.createScene(() -> { 
                        SuccessScene successScene = new SuccessScene();
                        stage.setScene(successScene.createScene(typingScene.getFinalTime(),typingScene.getFinalWpm()));
                },
                () -> { 
                        FailureScene failureScene = new FailureScene();
                        stage.setScene(failureScene.createScene(stage));
                }));
        });

VBox overlay = new VBox(25);

overlay.setAlignment(Pos.CENTER);

overlay.getChildren().addAll(
        failureLabel,
        retryButton
);
        overlay.setAlignment(Pos.CENTER);
        overlay.setTranslateY(300);

        StackPane root = new StackPane(bgView, overlay);
        root.setStyle("-fx-background-color: #111111;");
        return new Scene(root, 1920, 1080);}
}