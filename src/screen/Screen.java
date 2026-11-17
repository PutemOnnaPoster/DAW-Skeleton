package screen;


import audio.AudioEngine;
import javafx.scene.control.Button;
import sequencer.Pattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;




public class Screen extends Application {
    private static Pattern pattern;
    private Rectangle[] nodes = new Rectangle[32];
    private boolean playing = false;



    public Screen() {

    }
    public static void setPattern(Pattern p) {
        pattern = p;
    }
    @Override
    public void start(Stage stage) {
        boolean[] steps = pattern.getSteps();
        Pane root = new Pane();
        Button playButton = new Button("|>");
        Button restart = new Button("|<");
        restart.setLayoutX(30);
        restart.setOnAction(e -> {
            pattern.reset();
            refresh();
        });
        root.getChildren().add(restart);
        playButton.setOnAction(e -> {
            playing = !playing;

            if (playing) {
                playButton.setText("| |");
            } else {
                playButton.setText("|>");
            }
        });
        root.getChildren().add(playButton);

        for (int i = 0; i < 32; i++) {
            final int step = i;
            int x = 50 + i * 40;
            Rectangle node = new Rectangle(x, 50, 30, 30);

            nodes[i] = node;

            if (steps[i]) {

                node.setFill(Color.LIGHTGREEN);
            } else {
                node.setFill(Color.GRAY);
            }
            if(i == pattern.getCurrentStep()) {
                node.setFill(Color.ALICEBLUE);
            }
            node.setOnMouseClicked(e -> {
                pattern.toggleStep(step);
                refresh();
            });
            root.getChildren().add(node);
        }

        Scene scene = new Scene(root, 800, 200);
        stage.setTitle("REDUCTION");
        stage.setScene(scene);

        stage.show();
        long nanosPerBeat = 15_000_000_000L / pattern.getBpm();
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (playing && now - lastUpdate > nanosPerBeat) {
                    pattern.stepper();
                    refresh();
                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }
    public void refresh() {
        for (int i = 0; i < 32; i++) {
            if(i == pattern.getCurrentStep()) {
                nodes[i].setFill(Color.ALICEBLUE);
            } else if(pattern.getSteps()[i]) {
                nodes[i].setFill(Color.LIGHTGREEN);
            } else {
                nodes[i].setFill(Color.GRAY);
            }
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}