package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class AudioEngine {
    private File sound;

    public AudioEngine(File sound){
        this.sound = sound;
    }
    public void playSound() throws RuntimeException {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(sound);

            Clip clip = AudioSystem.getClip();

            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
