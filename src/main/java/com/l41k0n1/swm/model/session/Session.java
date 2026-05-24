package com.l41k0n1.swm.model.session;

import com.l41k0n1.swm.model.timer.SessionTimer;

public class Session {

    // static field to count pomodoros for each session
    private static int sessionCounter = 0;

    // Initializing default session. Has to be short 25/5;
    private SessionPreset sessionPreset = SessionPreset.SHORT;

    // Need a reference to the model;
    private SessionTimer timer;


    public void applyPreset(SessionPreset preset) {
        sessionPreset = preset;

        int workSeconds = sessionPreset.getWorkDuration() * 60;
        int breakSeconds = sessionPreset.getBreakDuration() * 60;

        timer.workSecondsRemainingProperty().set(workSeconds);
        timer.breakSecondsRemainingProperty().set(breakSeconds);
    }

    public SessionPreset getDefaultSessionPreset() {
        return sessionPreset;
    }

    public int getSessionCounter() {
        return sessionCounter;
    }

    public void updateSessionCounter() {
        sessionCounter++;
    }

    public void setTimer(SessionTimer t) {
        timer = t;
    }
}
