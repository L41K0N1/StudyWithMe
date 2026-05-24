package com.l41k0n1.swm.service;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class MusicManager implements IMusicService {

    // private static final String LOFI_STREAM = "https://radiorecord.hostingradio.ru/lofi96.aacp";

    private static final String RADIO_STREAM = "http://galnet.ru:8000/soft";

/*    private static final List<String> radioStations = List.of(
            "https://radiorecord.hostingradio.ru/lofi96.aacp",
            "https://radiorecord.hostingradio.ru/ambient96.aacp"
    );*/

    private MediaPlayer mediaPlayer;

    private final ReadOnlyBooleanWrapper isPlaying = new ReadOnlyBooleanWrapper(false);


    @Override
    public void playLoFi() {
        // playStation(LOFI_STREAM);
        playStation(RADIO_STREAM);
    }

/*    @Override
    public void playAmbient() {
        playStation(radioStations.get(1));
    }*/

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            } catch (Exception e) {
                // needs to be taken in a closer look
                e.printStackTrace();
            } finally {
                mediaPlayer = null;
            }
            isPlaying.set(false);
        }
    }

    private void playStation(String streamURL) {

        // closing in a way to prevent errors
        stop();

        Media media = new Media(streamURL);
        mediaPlayer = new MediaPlayer(media);


        mediaPlayer.setOnReady(() -> {
            mediaPlayer.setVolume(50.0);
            mediaPlayer.play();
            isPlaying.set(true);
        });

        mediaPlayer.setOnError(() -> {
            MediaException error = mediaPlayer.getError();
            System.out.println("Error: " + error.getMessage());
            isPlaying.set(false);
        });

        mediaPlayer.setOnPlaying(() -> {
            System.out.println("Stream is playing");
        });

    }

    @Override
    public ReadOnlyBooleanProperty isPlayingProperty() {
        return isPlaying.getReadOnlyProperty();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying.get();
    }
}
