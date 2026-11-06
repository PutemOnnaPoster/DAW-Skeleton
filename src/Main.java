import audio.AudioEngine;
import sequencer.Pattern;

public class Main {
    public static void main(String[] args) {
        AudioEngine clap = new AudioEngine();

        Pattern simple = new Pattern(clap);
        simple.clapper();
    }


}
