package views.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TopPane extends HBox {
    private int seconds, minutes;
    private SimpleIntegerProperty mistakes;
    private Text timerText, mistakeText;
    private Animation timer;

    public TopPane() {
        mistakes = new SimpleIntegerProperty(0);
        timerText = new Text("00:00");
        mistakeText = new Text(String.valueOf(mistakes.get()));
        timerText.setFont(Font.font(25));
        mistakeText.setFont(Font.font(25));
        mistakes.addListener(observable -> {
            updateMistakeCounter();
        });
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> countUp()));
        timer.setCycleCount(Timeline.INDEFINITE);
        paint();

    }

    private void paint() {

        //DESIGNED
        Image timerIcon = new Image("file:resources/img/Timer.jpg");
        ImageView viewTimerIcon = new ImageView(timerIcon);
        viewTimerIcon.setFitWidth(40);
        viewTimerIcon.setFitHeight(40);

        Image mistakeIcon = new Image("file:resources/img/Mistake.jpg");
        ImageView viewMistakeIcon = new ImageView(mistakeIcon);
        viewMistakeIcon.setFitWidth(40);
        viewMistakeIcon.setFitHeight(40);

        HBox B1 = new HBox();
        B1.getChildren().addAll(viewTimerIcon,timerText);
        B1.setSpacing(7);
        HBox B2 = new HBox(viewMistakeIcon,mistakeText);
        B2.setSpacing(7);
        Pane spacer = new Pane();//separate timer form mistake counter
        getChildren().addAll(B1,spacer, B2);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 20, 10, 20));
    }

    public void startTimer() {
        if (timer.getStatus() == Animation.Status.STOPPED)
            timer.play();
    }

    public void stopTimer() {
        timer.stop();
    }

    private void updateMistakeCounter() {
        mistakeText.setText(mistakes.get() + "");
    }

    private void countUp() {
        if (seconds == 59) {
            minutes++;
            seconds = 0;
        } else {
            seconds++;
        }
        String timer = String.format("%02d:%02d", minutes, seconds);
        timerText.setText(timer);
        timerText.setFont(Font.font(25));
    }

    public int getSeconds() {
        return seconds + minutes * 60;
    }

    public void addMistake() {
        this.mistakes.set(mistakes.get() + 1);
        System.out.println(mistakes.get());
    }

    public int getMistakes() {
        return mistakes.get();
    }
}

