package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class AudioEngine {
    File clap = new File("src/samples/clap.wav");

    public void playSound() throws RuntimeException {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(clap);

            Clip clip = AudioSystem.getClip();

            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
