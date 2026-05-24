package com.l41k0n1.swm.model.session;

public enum SessionPreset {
    SHORT(25, 5),
    MEDIUM(50, 10),
    LONG(120, 20);

    private final int workDuration;
    private final int breakDuration;

    SessionPreset(int initWork, int initBreak) {
        workDuration = initWork;
        breakDuration = initBreak;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }
}
