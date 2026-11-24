package sequencer;

import audio.AudioEngine;

public class Pattern {
    private AudioEngine sound;
    boolean[] steps = new boolean[32];
    private int currentStep = 0;
    private int bpm;


    public Pattern(AudioEngine sound, int bpm) {
        this.sound = sound;
        this.bpm = bpm;
        for (int i = 0; i < steps.length; i += 4) {
            steps[i] = true;
        }
    }
    public boolean[] getSteps() {
        return steps;
    }
    public int getCurrentStep() {
        return currentStep;
    }
    public int getBpm() {
        return bpm;
    }
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
    public void reset() {
        currentStep = 0;
    }
    public void toggleStep(int step) {
        steps[step] = !steps[step];
    }

    public void stepper() throws RuntimeException {
        if (steps[currentStep]) {
            sound.playSound();
        }
        currentStep++;

        if (currentStep == 32) {
            currentStep = 0;
        }
    }

}
