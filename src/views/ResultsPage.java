package views;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Player;
import views.game.HomePane;

public class ResultsPage extends FlowPane {
    public ResultsPage(Stage stage) {
        ListView listView = new ListView();
        Button btnReturn = new Button("Return Home");

        btnReturn.setOnAction(e -> {
            stage.getScene().setRoot(HomePane.getHomePane(stage));
        });
        Player player = Player.getInstance();
        for (int n = 0; n < player.getListMistakes().size(); n++) {
            HBox row = new HBox();
            Text mis = new Text(player.getListMistakes().get(n).toString());
            Text time = new Text(player.getListTimer().get(n).toString());
            Region spacer = new Region();
            row.getChildren().addAll(mis, spacer, time);
            HBox.setHgrow(spacer, Priority.ALWAYS);
            listView.getItems().add(row);
        }
        Text mistakesTxt = new Text("Number Of Mistakes");
        Text timeTxt = new Text("Time Taken in seconds");

        HBox subjectBox = new HBox(mistakesTxt, timeTxt);
        subjectBox.setSpacing(80);
        getChildren().clear();
        HBox b = new HBox(btnReturn);
        b.setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(subjectBox, listView, b);
        setOrientation(Orientation.VERTICAL);
        setAlignment(Pos.CENTER);
        setVgap(7);
    }
}