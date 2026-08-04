package com.shinobi.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;

public class IntroScene {

    //Runnable is an interface in Java, it has one abstract method run()
    public Scene createScene(Runnable onContinue) {

        Image bgImage = new Image(getClass().getResourceAsStream("/Start Frame.png"));
        ImageView bgView = new ImageView(bgImage);

        Rectangle panel = new Rectangle();

        panel.setWidth(1120);      
        panel.setHeight(852);

        panel.setArcWidth(50);     
        panel.setArcHeight(50);
        panel.setTranslateY(-20);

        panel.setFill(Color.rgb(255, 255, 255, 0.5));

        Label titleShadow = new Label("MISSION BRIEFING");
        titleShadow.setStyle("-fx-font-size: 56px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #b7f36b;");
        titleShadow.setTranslateX(4);
        titleShadow.setTranslateY(188);

        Label title = new Label("MISSION BRIEFING");
        title.setStyle("-fx-font-size: 56px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #081C3A;");
        title.setTranslateY(184);

        StackPane titlePane = new StackPane(titleShadow, title);

        Label dialogueShadow = new Label(">I am a shinobi from the Village of Naamu.\n" + ">The neighboring Village of Baaram has stolen our sacred scroll.\n"+ ">I need to enter their village and recover the scroll.\n" + ">Assist me in my mission.");
        dialogueShadow.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #081C3A;");

        dialogueShadow.setTranslateX(0);
        Label dialogue = new Label(">I am a shinobi from the Village of Naamu.\n" + ">The neighboring Village of Baaram has stolen our sacred scroll.\n" + ">I need to enter their village and recover the scroll.\n" + ">Assist me in my mission.");
        dialogue.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" +"-fx-text-fill: #b7f36b;");
        dialogue.setTranslateX(4);
        dialogue.setTranslateY(194);

        StackPane dialoguePane = new StackPane(dialogueShadow, dialogue);

        Button continueButton = new Button("CONTINUE");
        continueButton.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;" + "-fx-background-color: #0A1F44;" + "-fx-text-fill: #b7f36b;" + "-fx-padding: 12 28 12 28;" );

        VBox menuContent = new VBox(30, titlePane, dialogue, dialoguePane, continueButton);
        menuContent.setAlignment(Pos.CENTER);

        StackPane menuRoot = new StackPane(bgView, panel, menuContent);

        Scene menuScene = new Scene(menuRoot, 1920, 1080);

        continueButton.setOnAction(e -> onContinue.run());

        return menuScene;
    }
}