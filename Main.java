package com.shinobi;
//agar yeh waale imports nahi kiya toh external classes istimaal karte waqt new com.shinobi.scenes.ClassName();(example) likhna padega
import com.shinobi.scenes.FailureScene;
import com.shinobi.scenes.IntroScene;
import com.shinobi.scenes.SuccessScene;
import com.shinobi.scenes.TypingScene;
import com.shinobi.scenes.WallScene;

import javafx.application.Application; 
import javafx.geometry.Pos;
import javafx.scene.Scene; //Stage ke andar scene/s hote hain
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane; //Used for background image + overlaying text/buttons
import javafx.scene.layout.VBox;  //Used to stack title, buttons, labels
import javafx.stage.Stage; //Stage is the application's window
//Application Javafx ka class hai, toh ussi ko Main extend karega to get recongnized as Javafx application, warna kaam hee nahi karta
public class Main extends Application {
// pehle main method par jaayga, wahan se launch call hoga, uski wajah se Javafx stage banaayga, uske baad start(Stage) hoga
//start basically ui wagairah ke liye hai aur launch matlab JavaFX ko initiate karega
    @Override
    public void start(Stage stage) {

        //Image stores the image but then ImageView actually displays it
        Image bgImage = new Image(getClass().getResourceAsStream("/Start Frame.png"));
        ImageView bgView = new ImageView(bgImage);

        Label titleShadow = new Label("STROKE CHASING SHINOBI");
        titleShadow.setStyle("-fx-font-size: 56px; -fx-font-weight: bold; -fx-text-fill: #0A1F44;");
        titleShadow.setTranslateX(4);
        titleShadow.setTranslateY(4);

        Label title = new Label("STROKE CHASING SHINOBI");
        title.setStyle("-fx-font-size: 56px; -fx-font-weight: bold; -fx-text-fill: #B7F36B;");

        //StackPane stacks elements on top of one another
        StackPane titlePane = new StackPane(titleShadow, title);

        Button beginButton = new Button("BEGIN THE CHASE");
        beginButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-background-color: #B7F36B; -fx-text-fill: #0A1F44; -fx-padding: 12 28 12 28;");

        //VBox arranges elements vertically
        VBox menuContent = new VBox(30, titlePane, beginButton);
        menuContent.setAlignment(Pos.CENTER);
        menuContent.setTranslateY(45);

        //Using StackPane to overlay menuContent (=titltePane (titleShadow + title) + BeginButton) over background view
        StackPane menuRoot = new StackPane(bgView, menuContent);

        Scene menuScene = new Scene(menuRoot, 1920, 1080);

        bgView.fitWidthProperty().bind(menuScene.widthProperty()); //bgView ki width ko menuScene ki width ke baraabar karo
        bgView.fitHeightProperty().bind(menuScene.heightProperty());
        bgView.setPreserveRatio(false); //will stretch the image kyunki false rakha hai

        /*  jo JavaFX hai, wahan par ek class hai, ActionEvent, uska ham object banate hain "e"
            jo yahan par ->{....} hai, it is said to be Java's lambda expression syntax, shorthand way of writing a method implementation
            e = the ActionEvent
            -> = "goes to" or "does the following"
            {...} = the body of the event handler
            example neeche
            
            beginButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {

            IntroScene intro = new IntroScene();

            stage.setScene(intro.createScene(new Runnable() {
                @Override
                public void run() {
                    WallScene wallScene = new WallScene();
                    stage.setScene(wallScene.createScene(new Runnable() {
                        @Override
                        public void run() {
                            TypingScene typingScene = new TypingScene();
            yeh kuch bohat lamba jaaraha hai......
            in short, it is better to use lambda expression syntax idhar par
        */

        /*"Whenever this button fires an ActionEvent, run this block of code."__gpt
        Apparently JavaFX sorts this stuff for you, so by ActionEvent it means
        mouse clicking the button, spacebar pressed or even Enter pressed
        */
        beginButton.setOnAction(e -> { 
            IntroScene intro = new IntroScene();

            /*  () -> { ... } is a Runnable callback. It will be sent to the "Runnable onContinue" parameter in the next scene
                what happens is, introScene yeh code pakadta hai, 
                but doesn't execute it right away so what happens it that it sets the stage to introScene and 
                only when the ActionEvent happens, the passed code gets used
            */
            stage.setScene(intro.createScene(() -> { 
                WallScene wallScene = new WallScene();

                stage.setScene(wallScene.createScene(() -> {
                    TypingScene typingScene = new TypingScene();

                    stage.setScene(typingScene.createScene(() -> {
                        SuccessScene success = new SuccessScene();
                            stage.setScene(success.createScene(typingScene.getFinalTime(),typingScene.getFinalWpm()));
                        },
                                                        () -> {
                        FailureScene failure = new FailureScene();
                            stage.setScene(failure.createScene(stage));
                        }
                    ));
                }));
            }));
        });
        stage.setTitle("Stroke Chasing Shinobi");
        stage.setScene(menuScene); //will put menuScene inside the window
        stage.setFullScreen(true);
        stage.show(); //makes the appearance
    }

    public static void main(String[] args) {
        launch();
    }
}
