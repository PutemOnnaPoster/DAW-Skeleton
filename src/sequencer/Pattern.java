package sequencer;



import audio.AudioEngine;

public class Pattern {
    private AudioEngine clap;

    public Pattern(AudioEngine clap) {
        this.clap = clap;
    }
    public void clapper() throws RuntimeException {

        while(true) {
            try {
                clap.playSound();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}
