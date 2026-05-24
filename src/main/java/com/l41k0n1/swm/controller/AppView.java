package com.l41k0n1.swm.controller;

import com.l41k0n1.swm.model.session.SessionPreset;
import com.l41k0n1.swm.model.timer.TimerState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AppView implements Initializable, IButtonHandler {

    @FXML
    private Label workTimerLabel;
    @FXML
    private Label breakTimerLabel;
    @FXML
    private Button startButton, pauseButton, resumeButton, stopButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button shortSession, mediumSession, longSession;
    @FXML
    private Button playMusic, stopMusic;

    private final AppController controller;

    public AppView(AppController c) {
        this.controller = c;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // initializing button handlers
        setupTimerButtons();
        setupSessionButtons();
        setupAppButtons();
        setupMusicButtons();


        // Window close handler
        Platform.runLater(this::setupWindowCloseHandler);

        // Work timer listener
        controller.getModel().workSecondsRemainingProperty().addListener((obs, oldVal, newVal) -> {
            updateTimeDisplay(newVal.intValue());
        });

        // Break timer listener
        controller.getModel().breakSecondsRemainingProperty().addListener((obs, oldVal, newVal) -> {
            updateBreakTimeDisplay(newVal.intValue());
        });


        // ! has to be listening TimerStage insteadof isRunning. needs to be updated.
        controller.getModel().isRunningProperty().addListener((obs, wasRunning, isNowRunning) -> {
            updateButtons();
        });

        controller.getMusicManager().isPlayingProperty().addListener((obs, isPlaying, wasPlaying) -> {
            updateMusicButtons();
        });

        // We initialize the listeners, but this happens after the timer is created.
        // To see the default session start/stop time at startup, you need to explicitly call the UI update methods
        updateTimeDisplay(controller.getModel().getWorkSecondsRemaining());
        updateBreakTimeDisplay(controller.getModel().getBreakSecondsRemaining());

        // Forcing to updateButtons manually in general because we didn't initialize timer at this moment yet
        updateButtons();
        updateMusicButtons();
    }


    @Override
    public void setupTimerButtons() {
        startButton.setOnAction(e -> controller.startTimer());
        pauseButton.setOnAction(e -> controller.pauseTimer());
        resumeButton.setOnAction(e -> controller.resumeTimer());
        stopButton.setOnAction(e -> controller.stopAndResetTimer());
    }

    @Override
    public void setupSessionButtons() {
        shortSession.setOnAction(e -> controller.switchSession(SessionPreset.SHORT));
        mediumSession.setOnAction(e -> controller.switchSession(SessionPreset.MEDIUM));
        longSession.setOnAction(e -> controller.switchSession(SessionPreset.LONG));
    }

    @Override
    public void setupAppButtons() {
        exitButton.setOnAction(e -> controller.handleExit());
    }

    @Override
    public void setupMusicButtons() {
        playMusic.setOnAction(e -> controller.playMusic());
        stopMusic.setOnAction(e -> controller.stopMusic());
    }

    private void updateButtons() {

        TimerState state = controller.getModel().getState();

        switch (state) {
            case COMPLETED -> {
                startButton.setDisable(false);

                pauseButton.setDisable(true);
                resumeButton.setDisable(true);
                stopButton.setDisable(true);
            }
            case WORK, BREAK -> {
                pauseButton.setDisable(false);
                stopButton.setDisable(false);

                startButton.setDisable(true);
                resumeButton.setDisable(true);
            }
            case PAUSE -> {
                resumeButton.setDisable(false);
                stopButton.setDisable(false);

                startButton.setDisable(true);
                pauseButton.setDisable(true);
            }
        }
    }

    private void updateTimeDisplay(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;

        workTimerLabel.setText(String.format("%02d:%02d", minutes, secs));
    }

    private void updateBreakTimeDisplay(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;

        breakTimerLabel.setText(String.format("%02d:%02d", minutes, secs));
    }

    private void updateMusicButtons() {
        Platform.runLater(() -> {
            boolean playing = controller.getMusicManager().isPlaying();
            playMusic.setDisable(playing);
            stopMusic.setDisable(!playing);
        });
    }

    private void setupWindowCloseHandler() {
        try {
            Stage stage = (Stage) playMusic.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                controller.handleExit();
                event.consume();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
