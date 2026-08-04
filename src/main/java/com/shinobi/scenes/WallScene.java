package com.shinobi.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;

public class WallScene {

    public Scene createScene(Runnable onContinue) {

        Image bgImage = new Image(getClass().getResourceAsStream("/Wall.png"));
        ImageView bgView = new ImageView(bgImage);

        Rectangle panel = new Rectangle();

        panel.setWidth(800);      
        panel.setHeight(300);

        panel.setArcWidth(50);     
        panel.setArcHeight(50);

        panel.setFill(Color.rgb(255, 255, 255, 0.5));

        Label wallText = new Label( "I NEED TO CLIMB THE VILLAGE WALL.\n\n" + "CAN YOU BUILD ME A LADDER?");

        Button continueButton = new Button("Build Ladder");
        continueButton.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;" + "-fx-background-color: #0A1F44;" + "-fx-text-fill: #b7f36b;" + "-fx-padding: 12 28 12 28;" );

        continueButton.setOnAction(e -> onContinue.run());

        wallText.setStyle("-fx-font-size: 36px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #b7f36b;");

        wallText.setTextAlignment(TextAlignment.CENTER);
        wallText.setAlignment(Pos.CENTER);

        VBox content = new VBox(30);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(wallText, continueButton);

        StackPane panelGroup = new StackPane(panel, content);
        panelGroup.setAlignment(Pos.CENTER);

        panelGroup.setTranslateY(-320);

        StackPane sceneRoot = new StackPane(bgView, panelGroup);
        sceneRoot.setStyle("-fx-background-color: linear-gradient(#111111,#222222);");
        sceneRoot.setTranslateY(-20);

        Scene scene = new Scene(sceneRoot, 1920, 1080);

        return scene;   
    }
}