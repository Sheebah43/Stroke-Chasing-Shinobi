
package com.shinobi.scenes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import com.shinobi.PhraseBank;

public class TypingScene {

    // private final String challenge = "shadow stealth infiltration";
    private final String challenge = PhraseBank.randomPhrase();
    private int currentIndex = 0;
    private long startTime;
    private boolean started = false;
    private double finalTime = 0;
    private double finalWpm = 0;

    public Scene createScene(Runnable onSuccess, Runnable onFailure) {

        Label title = new Label("BUILD THE LADDER");
        title.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label objectiveLabel = new Label("Type the following phrase with 100% accuracy:");
        objectiveLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label challengeLabel = new Label(challenge);
        challengeLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: gold; -fx-font-weight: bold;");

        Label typedLabel = new Label(""); 
        typedLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: lightgreen;");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefHeight(30);
        progressBar.setPrefWidth(450);
        progressBar.setStyle("-fx-accent: limegreen; -fx-pref-height: 30px;");

        Label progressLabel = new Label("Progress: 0%");
        progressLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label timeLabel = new Label("Time: 0.00 s");
        timeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label wpmLabel = new Label("WPM: 0");
        wpmLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: lightgreen;" );

        Image backgroundImage = new Image(getClass().getResourceAsStream("/Night Background.png"));
        Image wallImage = new Image(getClass().getResourceAsStream("/Wall.png"));
        Image ladderImage = new Image(getClass().getResourceAsStream("/Ladder.png"));

        ImageView bgView = new ImageView(backgroundImage);
        ImageView wallView = new ImageView(wallImage);
        ImageView ladderView = new ImageView(ladderImage);

        StackPane root = new StackPane();
        Pane gameArea = new Pane();

        gameArea.prefHeightProperty().bind(root.heightProperty().multiply(0.55));
        gameArea.getChildren().addAll(wallView, ladderView);

        wallView.fitHeightProperty().bind(gameArea.heightProperty().multiply(1.15));
        wallView.fitWidthProperty().bind(gameArea.widthProperty());
        wallView.layoutYProperty().bind(gameArea.heightProperty().subtract(wallView.fitHeightProperty()));

        ladderView.setFitWidth(200);
        ladderView.fitHeightProperty().bind(gameArea.heightProperty().multiply(0.95));
        ladderView.layoutXProperty().bind(gameArea.widthProperty().subtract(ladderView.fitWidthProperty()).divide(2));
        ladderView.layoutYProperty().bind(gameArea.heightProperty().subtract(ladderView.fitHeightProperty()));
        ladderView.setOpacity(0);

        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER);

        HBox challengeRow = new HBox(10);
        challengeRow.setAlignment(Pos.CENTER);
        challengeRow.getChildren().addAll(objectiveLabel, challengeLabel);

        VBox typingBox = new VBox();
        typingBox.setAlignment(Pos.CENTER);
        typingBox.getChildren().addAll(challengeRow, typedLabel);

        content.getChildren().addAll(
            title,
            typingBox,
            gameArea,
            progressBar,
            progressLabel,
            timeLabel,
            wpmLabel
        );

        root.getChildren().addAll(bgView, content);

        Scene scene = new Scene(root);

        scene.setOnKeyTyped(event -> {//event here is an object of type KeyEvent and has the info of which key/char was typed
            if (!started) {//when it becomes true, start the timer
                startTime = System.currentTimeMillis();
                started = true;
            }
            if (currentIndex >= challenge.length()) {//keys typed after are ignored
                return;
            }
            String typed = event.getCharacter();//to ignore the empty chars
            if (typed.isEmpty()) {
                return;
            }
            char entered = typed.charAt(0);//store what's being typed, string to char
            char expected = challenge.charAt(currentIndex);

            if (entered != expected) {//compare stored and typed characters
                onFailure.run(); 
                return; //end execution
            }
            currentIndex++; //move to the next char  

            typedLabel.setText(challenge.substring(0, currentIndex)); //ek ek char add hota rahega

            //update the progress percentage and opacity of the ladder
            double progress = (double) currentIndex / challenge.length();
            progressBar.setProgress(progress);
            progressLabel.setText("Progress: " + (int)(progress * 100) + "%");
            ladderView.setOpacity(progress);

            long currentTime = System.currentTimeMillis();
            double elapsedSeconds = (currentTime - startTime) / 1000.0;//total time dega by subtracting the current timw with the time jab shuru kiya hoga
            timeLabel.setText(String.format("Time: %.2f s", elapsedSeconds));
            double minutes = elapsedSeconds / 60.0; //time minutes mei hoga, cuz wpM

            double wpm=0;
            if (minutes > 0) {
                wpm = (currentIndex / 5.0) / minutes;
                wpmLabel.setText(String.format("WPM: %.1f", wpm));//
            }
            if (currentIndex == challenge.length()) {
                finalTime = elapsedSeconds;
                finalWpm = wpm;
                onSuccess.run(); // success callback
            }
        });

        root.setFocusTraversable(true);//Normally, layout panes ko keyborad input nahi milta, but isse StackPane ko keyboard focus milenge
        Platform.runLater(() -> root.requestFocus());

        return scene;
    }
    public double getFinalTime() {
        return finalTime;
    }
    public double getFinalWpm() {
        return finalWpm;
    }
}
