import audio.AudioEngine;
import sequencer.Pattern;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File clapFile = new File("src/samples/clap.wav");
        AudioEngine clap = new AudioEngine(clapFile);

        Pattern simple = new Pattern(clap, 147);
        simple.printPattern();
        simple.toggleStep(7);
        simple.printPattern();
        simple.clapper();
    }


}
