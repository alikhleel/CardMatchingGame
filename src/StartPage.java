import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.ResultsPage;
import views.game.AlertBox;
import views.game.GamePane;
import views.game.HomePane;

public class StartPage extends Application {
    private static final double PREF_HEIGHT = 700;
    private static final double PREF_WIDTH = 600;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Memory Puzzle");
        Scene scene = new Scene(HomePane.getHomePane(stage));
        stage.setScene(scene);
        stage.setHeight(PREF_HEIGHT);
        stage.setWidth(PREF_WIDTH);
        stage.show();
    }


}
