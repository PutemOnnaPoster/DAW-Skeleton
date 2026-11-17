import audio.AudioEngine;
import javafx.application.Application;
import screen.Screen;
import sequencer.Pattern;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File kickFile = new File("src/samples/kick.wav");
        File clapFile = new File("src/samples/clap.wav");

        AudioEngine kick = new AudioEngine(kickFile);
        AudioEngine clap = new AudioEngine(clapFile);

        Pattern[] patterns = {
                kickPattern,
                clapPattern
        }
        Screen.setPatterns(patterns);
        Application.launch(Screen.class, args);


    }


}
