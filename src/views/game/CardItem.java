package views.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import models.Card;


public class CardItem extends Pane {
    private static final double CARD_WIDTH = 100;
    private static final double CARD_HEIGHT = 150;
    private static final Image backImage = new Image("file:resources/img/b2fv.png");
    private final Card card;
    private ImageView imageView;

    public CardItem(Card card) {
        this.card = card;
        // design the layout
        paint();
        setPrefWidth(CARD_WIDTH);
        setPrefHeight(CARD_HEIGHT);
    }

    public void turnCard() {
        if (!card.isOpen())
            imageView.setImage(new Image("file:" + card.getImagePath()));
         else
            imageView.setImage(backImage);
        card.setOpen(!card.isOpen());
    }

    private void paint() {
        getChildren().clear();
        imageView = new ImageView(backImage);
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.fitWidthProperty().bind(this.widthProperty());
        getChildren().add(imageView);
    }

    public Card getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardItem cardItem = (CardItem) o;
        return (card.equals(cardItem.card));
    }


}
