
package views.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.ResultsPage;


public class HomePane extends StackPane {
    private static HomePane homePane;

    private HomePane(Stage stage) {

        //Setting a background image to the start window
        Image background = new Image("file:resources/img/Background.jpg");
        setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //Initializing elements of the class
        Button playButton = new Button("PLAY");
        playButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        Button highScore = new Button("HIGH SCORES");
        highScore.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Button quitButton = new Button("QUIT");
        quitButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


        //Buttons sizes
        playButton.setPrefWidth(200);
        playButton.setPrefHeight(34);

        highScore.setPrefWidth(200);
        highScore.setPrefHeight(34);

        quitButton.setPrefWidth(200);
        quitButton.setPrefHeight(34);

        Text gameTitle = new Text("MEMORY PUZZLE!");
        gameTitle.setFill(Color.DARKRED);
        gameTitle.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 42));
        gameTitle.setStroke(Color.WHITE);
        gameTitle.setStrokeWidth(2);

        //Assigning buttons functionalities
        playButton.setOnAction(e -> {
            stage.getScene().setRoot(new GamePane(stage));
        });

        highScore.setOnAction(e -> {
            stage.getScene().setRoot(new ResultsPage(stage));
        });

        quitButton.setOnAction(actionEvent -> stage.close());


        //Arranging positions of elements
        VBox layout = new VBox();

        HBox nameElement = new HBox(gameTitle);
        nameElement.setAlignment(Pos.CENTER);

        HBox buttonElements = new HBox(playButton, highScore);
        buttonElements.setAlignment(Pos.CENTER);
        buttonElements.setSpacing(30);

        HBox quitElement = new HBox(quitButton);
        quitElement.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(nameElement, buttonElements, quitElement);

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(60);
        getChildren().add(layout);
    }


    public static HomePane getHomePane(Stage stage) {
        if (homePane == null)
            homePane = new HomePane(stage);
        return homePane;
    }
}
