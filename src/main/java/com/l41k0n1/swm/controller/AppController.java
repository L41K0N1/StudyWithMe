package com.l41k0n1.swm.controller;

import com.l41k0n1.swm.model.session.Session;
import com.l41k0n1.swm.model.session.SessionPreset;
import com.l41k0n1.swm.model.timer.SessionTimer;
import com.l41k0n1.swm.service.IMusicService;
import com.l41k0n1.swm.service.MusicManager;
import javafx.application.Platform;

public class AppController implements IExitHandler {

    private final SessionTimer timer;

    private final Session session;

    private final IMusicService musicService = new MusicManager();


    public AppController(SessionTimer t, Session s) {
        this.timer = t;
        this.session = s;

        // Registering shutDownHook for emergency exit
        Runtime.getRuntime().addShutdownHook(new Thread(this::cleanUpResources));
    }

    public void startTimer() {
        timer.start();
    }

    public void pauseTimer() {
        timer.pause();
    }

    public void resumeTimer() {
        timer.resume();
    }

    public void stopAndResetTimer() {
        timer.stopAndReset();
    }

    public void playMusic() {
        musicService.playLoFi();
    }

    public void stopMusic() {
        musicService.stop();
    }

    public void switchSession(SessionPreset preset) {
        session.applyPreset(preset);
    }

    public SessionTimer getModel() {
        return timer;
    }

    public IMusicService getMusicManager() {
        return musicService;
    }

    public void handleExit() {

        cleanUpResources();
        Platform.exit();

        // redundant
        // System.exit(0);
    }

    @Override
    public void cleanUpResources() {
        musicService.stop();
        waitForThreadsToFinish(2000);
    }

    private void waitForThreadsToFinish(int timeoutMs) {
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}