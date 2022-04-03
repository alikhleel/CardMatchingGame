package views.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Card;
import models.Player;

import java.io.File;
import java.util.Random;


public class GamePane extends BorderPane {
    private final CardItem[] cards;
    private final CardItem[] checkCards;
    private int correctCards;
    private CenterPane centerPane;
    private TopPane topPane;
    private Player player;
    private MediaPlayer mediaPlayer;

    public GamePane(Stage stage) {
        centerPane = new CenterPane();
        topPane = new TopPane();
        cards = new CardItem[16];
        checkCards = new CardItem[2];
        player = Player.getInstance();
        createCards();
        setTop(topPane);
        setCenter(centerPane);
        centerPane.addCards(cards);

        AlertBox.statusProperty().addListener(observable -> {


            switch (AlertBox.getStatus()) {
                case PLAY_AGAIN:
                    restart();
                    break;
                case CLOSE:
                    stage.close();
                    break;
                case RETURN_HOME:
                    stage.getScene().setRoot(HomePane.getHomePane(stage));
                    break;
            }
        });
    }

    public void win() {
        System.out.println("win form main page");
        AlertBox.display("Win", String.format("Congratulations !\n" +
                "You won in %s seconds with %s mistakes", getTime(), getNumMistakes()
        ));

    }

    public void restart() {
        centerPane = new CenterPane();
        topPane = new TopPane();
        correctCards = 0;
        createCards();
        setTop(topPane);
        setCenter(centerPane);
        centerPane.addCards(cards);

    }

    private void createCards() {
        int c = 0;
        while (c < cards.length / 2) {
            int id = c * 2;
            cards[id] = new CardItem(new Card(c, "resources/img/" + (c) + ".png"));
            cards[id + 1] = new CardItem(new Card(c, "resources/img/" + (c) + ".png"));
            c++;
        }
        shuffleCards();
        addControllers();
    }

    private void addControllers() {
        for (int i = 0; i < cards.length; i++) {
            int id = i;
            cards[id].addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                cardSelected(cards[id]);
            });
        }
    }


    private void shuffleCards() {
        int index;
        CardItem temp;
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    private void cardSelected(CardItem cardItem) {
        topPane.startTimer();
        if (cardItem.getCard().isOpen()) return; // to prevent clicking on the open card
        if (checkCards[0] == null) {
            clickSound();
            cardItem.turnCard();
            checkCards[0] = cardItem;
        } else if (checkCards[1] == null && checkCards[0] != cardItem) {
            cardItem.turnCard();
            checkCards[1] = cardItem;
            checkSelection();
        }
    }

    private void checkSelection() {
        if (checkCards[0].equals(checkCards[1])) {
            correctAnswerSound();
            correctCards += 2;
            checkCards[0] = null;
            checkCards[1] = null;
            checkWin();
        } else {
            wrongAnswerSound();
            new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                checkCards[0].turnCard();
                checkCards[1].turnCard();
                checkCards[0] = null;
                checkCards[1] = null;
                topPane.addMistake();
            })).play();
        }
    }

    private void checkWin() {
        if (correctCards == cards.length) {
            winningSound();
            topPane.stopTimer();
            player.addScore(topPane.getMistakes(), topPane.getSeconds());
            win();
        }
    }

    public int getTime() {
        return topPane.getSeconds();
    }

    public int getNumMistakes() {
        return topPane.getMistakes();
    }

    private void correctAnswerSound() {
        Media correctAudio = new Media((new File("resources/sounds/CorrectAnswerAudio.wav")).toURI().toString());
        MediaPlayer correctPlayer = new MediaPlayer(correctAudio);
        correctPlayer.play();


    }

    private void wrongAnswerSound() {
        Media media = new Media((new File("resources/sounds/WrongAnswer2.wav")).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();


    }

    private void winningSound() {
        Media media = new Media((new File("resources/sounds/Winning.wav")).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void clickSound() {
        Media media = new Media((new File("resources/sounds/Mouse Click.wav")).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
