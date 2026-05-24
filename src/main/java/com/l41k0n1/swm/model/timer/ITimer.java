package com.l41k0n1.swm.model.timer;

public interface ITimer {

    void start();

    void pause();

    void resume();

    void stopAndReset();

    void tick();
}
