package views.game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    private static SimpleIntegerProperty status = new SimpleIntegerProperty(-1);
    ;

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Verdana", FontWeight.BOLD,16));
        label.setTextFill(Color.DARKGREEN);

        Button btnPlayAgain = new Button("Play Again");
        btnPlayAgain.setPrefWidth(120);
        btnPlayAgain.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

        Button btnReturnHome = new Button("Return Home");
        btnReturnHome.setPrefWidth(120);
        btnReturnHome.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

        Button btnClose = new Button("Exit");
        btnClose.setPrefWidth(120);
        btnClose.setFont(Font.font("Verdana", FontWeight.BOLD,11));

        btnPlayAgain.setOnAction(actionEvent -> {
            window.close();
            status.set(AlertBoxSelection.PLAY_AGAIN.ordinal());
        });
        btnReturnHome.setOnAction(e -> {
            window.close();
            status.set(AlertBoxSelection.RETURN_HOME.ordinal());
        });
        btnClose.setOnAction(e -> {
            window.close();
            status.set(AlertBoxSelection.CLOSE.ordinal());
        });

        VBox layout = new VBox(50);
        layout.setSpacing(16);
        label.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(11,11,11,11));

        HBox labLayout = new HBox(label);
        labLayout.setAlignment(Pos.CENTER);

        HBox btnPlayHBox = new HBox(btnPlayAgain);
        btnPlayHBox.setAlignment(Pos.CENTER);

        HBox btnReturnHBox = new HBox(btnReturnHome);
        btnReturnHBox.setAlignment(Pos.CENTER);

        HBox btnCloseHBox = new HBox(btnClose);
        btnCloseHBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(labLayout, btnPlayHBox,btnReturnHBox,btnCloseHBox);
        Scene scene = new Scene(layout,500,250);
        window.setScene(scene);
        window.showAndWait();
    }

    public static AlertBoxSelection getStatus() {
        return AlertBoxSelection.values()[status.get()];
    }

    public static SimpleIntegerProperty statusProperty() {
        return status;
    }

    public enum AlertBoxSelection {
        PLAY_AGAIN,
        RETURN_HOME,
        CLOSE
    }
}

