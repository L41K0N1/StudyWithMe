package com.l41k0n1.swm.service;

import javafx.beans.property.ReadOnlyBooleanProperty;

public interface IMusicService {
    void playLoFi();
    // void playAmbient();
    void stop();

    boolean isPlaying();

    ReadOnlyBooleanProperty isPlayingProperty();
}
