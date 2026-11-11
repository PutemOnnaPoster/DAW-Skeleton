package sequencer;

import audio.AudioEngine;

public class Pattern {
    private AudioEngine clap;
    boolean[] steps = new boolean[32];
    private int currentStep = 0;
    private int bpm;


    public Pattern(AudioEngine clap, int bpm) {
        this.clap = clap;
        this.bpm = bpm;
        for (int i = 2; i < 32; i += 4) {
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
    public void clapper() throws RuntimeException {
        while(true) {
            try {
                if (steps[currentStep]) {
                    clap.playSound();
                }
                currentStep++;
                double milis = 60000.0 / bpm;
                Thread.sleep((long) milis);
                if (currentStep == 32) {
                    currentStep = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}
