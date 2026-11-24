import audio.AudioEngine;
import javafx.application.Application;
import screen.Screen;
import sequencer.Pattern;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File kickFile = new File("src/samples/kick.wav");
        File clapFile = new File("src/samples/clap.wav");
        File hatFile = new File("src/samples/hat.wav");

        AudioEngine kick = new AudioEngine(kickFile);
        AudioEngine clap = new AudioEngine(clapFile);
        AudioEngine hat = new AudioEngine(hatFile);
        Pattern hatPattern = new Pattern(hat, 120);
        Pattern kickPattern  = new Pattern(kick, 120);
        Pattern clapPattern = new Pattern(clap, 120);
        Pattern[] patterns = {
                kickPattern,
                clapPattern,
                hatPattern
        };
        Screen.setPatterns(patterns);
        Application.launch(Screen.class, args);


    }


}
