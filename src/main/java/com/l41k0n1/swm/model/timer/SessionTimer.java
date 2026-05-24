package com.l41k0n1.swm.model.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class SessionTimer implements ITimer {

    private Timeline timeline;
    private TimerState state = TimerState.COMPLETED;

    private final IntegerProperty workSecondsRemaining;
    private final IntegerProperty breakSecondsRemaining;
    private final BooleanProperty isRunning = new SimpleBooleanProperty(false);

    private final int workDuration;
    private final int breakDuration;

    public SessionTimer(int initialMinutes, int breakMinutes) {
        workDuration = initialMinutes * 60;
        breakDuration = breakMinutes * 60;

        workSecondsRemaining = new SimpleIntegerProperty(workDuration);
        breakSecondsRemaining = new SimpleIntegerProperty(breakDuration);

        initializeTimeline();
    }

    private void initializeTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> tick()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void tick() {
        if (isRunning.get()) {
            switch (state) {
                case WORK -> {
                    workSecondsRemaining.set(workSecondsRemaining.get() - 1);
                    if (workSecondsRemaining.get() <= 0) {
                        state = TimerState.BREAK;
                        breakSecondsRemaining.set(breakDuration);
                    }
                }
                case BREAK -> {
                    breakSecondsRemaining.set(breakSecondsRemaining.get() - 1);
                    if (breakSecondsRemaining.get() <= 0) {
                        state = TimerState.WORK;
                        workSecondsRemaining.set(workDuration);
                    }
                }
            }
        }
    }

    @Override
    public void start() {
        if (state == TimerState.COMPLETED) {
            state = TimerState.WORK;
            timeline.play();
            isRunning.set(true);
        }
    }

    public void pause() {
        if (state == TimerState.WORK || state == TimerState.BREAK) {
            state = TimerState.PAUSE;
            timeline.pause();
            isRunning.set(false);
        }
    }

    @Override
    public void resume() {
        if (state == TimerState.PAUSE) {
            state = TimerState.WORK;
            timeline.play();
            isRunning.set(true);
        }
    }


    @Override
    public void stopAndReset() {
        if (state != TimerState.COMPLETED) {
            state = TimerState.COMPLETED;
            workSecondsRemaining.set(workDuration);
            timeline.stop();
            isRunning.set(false);
        }
    }

    public IntegerProperty workSecondsRemainingProperty() {
        return workSecondsRemaining;
    }

    public int getWorkSecondsRemaining() {
        return workSecondsRemaining.get();
    }

    public IntegerProperty breakSecondsRemainingProperty() {
        return breakSecondsRemaining;
    }

    public int getBreakSecondsRemaining() {
        return breakSecondsRemaining.get();
    }

    public BooleanProperty isRunningProperty() {
        return isRunning;
    }

    public TimerState getState() {
        return state;
    }
}
