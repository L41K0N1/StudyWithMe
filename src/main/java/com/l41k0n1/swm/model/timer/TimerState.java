package com.l41k0n1.swm.model.timer;

public enum TimerState {
    WORK("Работа"),
    BREAK("Перерыв"),
    PAUSE("Пауза"),
    COMPLETED("Завершено");

    private final String displayText;

    TimerState(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
