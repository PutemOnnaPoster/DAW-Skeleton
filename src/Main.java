import audio.AudioEngine;
import javafx.application.Application;
import screen.Screen;
import sequencer.Pattern;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File kickFile = new File("src/samples/kick.wav");

        AudioEngine kick = new AudioEngine(kickFile);

        Pattern simple = new Pattern(kick, 100);
        Screen.setPattern(simple);
        Application.launch(Screen.class, args);


    }


}
