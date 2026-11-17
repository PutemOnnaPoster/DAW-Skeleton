package screen;


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
    private static Pattern[] patterns;
    private Rectangle[] nodes = new Rectangle[32];
    private boolean playing = false;



    public Screen() {

    }
    public static void setPatterns(Pattern[] patterns) {
        Screen.patterns = patterns;
    }
    @Override
    public void start(Stage stage) {
        boolean[] steps = patterns[0].getSteps();
        Pane root = new Pane();
        Button playButton = new Button("|>");
        Button restart = new Button("|<");
        restart.setLayoutX(30);
        restart.setOnAction(e -> {
            patterns[0].reset();
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

        for (int track = 0; track < patterns.length; track++) {
            Pattern pattern = patterns[track];
            for int step = 0; step <
            final int step = track;
            int x = 50 + track * 40;
            Rectangle node = new Rectangle(x, 50, 30, 30);

            nodes[track] = node;

            if (steps[track]) {

                node.setFill(Color.LIGHTGREEN);
            } else {
                node.setFill(Color.GRAY);
            }
            if(track == patterns[0].getCurrentStep()) {
                node.setFill(Color.ALICEBLUE);
            }
            node.setOnMouseClicked(e -> {
                patterns[0].toggleStep(step);
                refresh();
            });
            root.getChildren().add(node);
        }

        Scene scene = new Scene(root, 800, 200);
        stage.setTitle("REDUCTION");
        stage.setScene(scene);

        stage.show();
        long nanosPerBeat = 15_000_000_000L / patterns[0].getBpm();
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (playing && now - lastUpdate > nanosPerBeat) {
                    patterns[0].stepper();
                    refresh();
                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }
    public void refresh() {
        for (int i = 0; i < 32; i++) {
            if(i == patterns[0].getCurrentStep()) {
                nodes[i].setFill(Color.ALICEBLUE);
            } else if(patterns[0].getSteps()[i]) {
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