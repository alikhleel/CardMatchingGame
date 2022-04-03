package views.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class CenterPane extends GridPane {


    public CenterPane() {
    }

    // This method is responsible for adding card items to the view
    public void addCards(CardItem[] cards) {
        getChildren().clear();
        add(cards[0], 0, 1);
        add(cards[1], 0, 2);
        add(cards[2], 0, 3);
        add(cards[3], 0, 4);
        add(cards[4], 1, 1);
        add(cards[5], 1, 2);
        add(cards[6], 1, 3);
        add(cards[7], 1, 4);
        add(cards[8], 2, 1);
        add(cards[9], 2, 2);
        add(cards[10], 2, 3);
        add(cards[11], 2, 4);
        add(cards[12], 3, 1);
        add(cards[13], 3, 2);
        add(cards[14], 3, 3);
        add(cards[15], 3, 4);

        cardsLayout();
    }

    private void cardsLayout() {

        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(12);
        setVgap(12);


    }

}