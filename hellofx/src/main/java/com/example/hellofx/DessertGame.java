package com.example.hellofx;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;

public class DessertGame extends Application {

    public int score = 0;
    Random rand = new Random();


    @Override
    public void start(final Stage stage){
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 5
        Label scoreLabel = new Label("Score: " + this.score);
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        borderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        //requestFocus(exitButton);
        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        borderPane.setAlignment(pane, Pos.CENTER);

        Button buttonWin = new Button("Dessert");
        Button button1 = new Button("Desert");
        Button button2 = new Button("Desert");
        Button button3 = new Button("Desert");
        Button button4 = new Button("Desert");
        Button button5 = new Button("Desert");
        Button button6 = new Button("Desert");
        Button button7 = new Button("Desert");

        Button[] buttons = {buttonWin, button1, button2, button3, button4, button5, button6, button7};
        pane.getChildren().addAll(buttonWin, button1, button2, button3, button4, button5, button6, button7);
        randomizeButtonPositions(rand, buttons);
        // Step 7-10




        buttonWin.setOnAction(event -> {
            score += 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button1.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button2.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button3.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button4.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button5.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button6.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });
        button7.setOnAction(event -> {
            score -= 1;
            Label newScoreLabel = new Label("Score: " + this.score);
            borderPane.setTop(newScoreLabel);
            BorderPane.setAlignment(newScoreLabel, Pos.TOP_LEFT);
            randomizeButtonPositions(rand, buttons);
            pane.requestFocus();
        });

        stage.setScene(scene);
        stage.show();
    }
    private void randomizeButtonPositions(Random random, Button[] buttons) {
        for (Button button : buttons) {
            double x = random.nextDouble() * 600;
            double y = random.nextDouble() * 400;
            button.setLayoutX(x);
            button.setLayoutY(y);
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}