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
import javafx.scene.control.TextField;




public class Screen extends Application {
    private static Pattern[] patterns;
    private Rectangle[][] nodes = new Rectangle[4][32];
    private boolean playing = false;



    public Screen() {

    }
    public static void setPatterns(Pattern[] patterns) {
        Screen.patterns = patterns;
    }
    @Override
    public void start(Stage stage) {


            Pane root = new Pane();
            TextField bpmField = new TextField();

            bpmField.setText(String.valueOf(patterns[0].getBpm()));
            bpmField.setLayoutX(60);
            root.getChildren().add(bpmField);

            bpmField.setOnAction(e -> {
                try {
                    int bpm = Integer.parseInt(bpmField.getText());

                    for (Pattern pattern : patterns) {
                        pattern.setBpm(bpm);
                    }

                } catch(NumberFormatException nfe) {
                    bpmField.setText(String.valueOf(patterns[0].getBpm())
                    );

                }
            });
            Button playButton = new Button("|>");
            Button restart = new Button("|<");
            restart.setLayoutX(30);
                restart.setOnAction(e -> {

                    for (int track = 0; track < patterns.length; track++) {
                        patterns[track].reset();

                    }
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
            final int currentTrack = track;
            Pattern pattern = patterns[track];
            boolean[] row = pattern.getSteps();
            for (int step = 0; step < pattern.getSteps().length ; step++) {
                final int currentStep = step;
                int x = 10 + step * 40;
                int y = 50 + track * 40;
                Rectangle node = new Rectangle(x, y, 30, 30);

                nodes[track][step] = node;



                if (row[step]) {

                    node.setFill(Color.LIGHTGREEN);
                } else {
                    node.setFill(Color.GRAY);
                }
                if (step == patterns[track].getCurrentStep()) {
                    node.setFill(Color.ALICEBLUE);
                }
                node.setOnMouseClicked(e -> {
                    patterns[currentTrack].toggleStep(currentStep);
                    refresh();
                });
                root.getChildren().add(node);

            }
        }

        Scene scene = new Scene(root, 800, 200);
        stage.setTitle("REDUCTION");
        stage.setScene(scene);

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                long nanosPerStep = 15_000_000_000L / patterns[0].getBpm();
                if (playing && now - lastUpdate > nanosPerStep) {
                    for(int track = 0; track < patterns.length; track++) {
                        patterns[track].stepper();

                    }
                    refresh();
                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }
    public void refresh() {
        for (int track = 0; track < patterns.length; track++) {
            Pattern pattern = patterns[track];

            for (int step = 0; step < pattern.getSteps().length; step++) {
                if (step == pattern.getCurrentStep()) {
                    nodes[track][step].setFill(Color.ALICEBLUE);
                } else if (pattern.getSteps()[step]) {
                    nodes[track][step].setFill(Color.LIGHTGREEN);
                } else {
                    nodes[track][step].setFill(Color.GRAY);
            }
        }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}