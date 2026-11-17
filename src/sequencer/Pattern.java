package sequencer;

import audio.AudioEngine;

public class Pattern {
    private AudioEngine kick;
    boolean[] steps = new boolean[32];
    private int currentStep = 0;
    private int bpm;


    public Pattern(AudioEngine kick, int bpm) {
        this.kick = kick;
        this.bpm = bpm;
            steps[0] = true;
            steps[4] = true;
            steps[8] = true;
            steps[11] = true;
            steps[14] = true;
            steps[16] = true;
            steps[20] = true;
            steps[24] = true;
            steps[27] = true;
            steps[30] = true;

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
    public void reset() {
        currentStep = 0;
    }
    public void toggleStep(int step) {
        steps[step] = !steps[step];
    }
    public void printPattern() {
        for (int i = 0; i < 32; i++) {
            if (i % 4 == 0) {
                System.out.println();
            }
            if (steps[i]) {
                System.out.print("[X]");
            } else {
                System.out.print("[ ]");
            }

        }
    }
    public void stepper() throws RuntimeException {
        if (steps[currentStep]) {
            kick.playSound();
        }
        currentStep++;

        if (currentStep == 32) {
            currentStep = 0;
        }
    }

}
