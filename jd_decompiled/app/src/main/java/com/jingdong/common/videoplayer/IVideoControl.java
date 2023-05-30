package com.jingdong.common.videoplayer;

/* loaded from: classes6.dex */
public interface IVideoControl {
    int getBufferPercentage();

    int getCurrentPosition();

    int getDuration();

    boolean isPlaying();

    void pause();

    void seekTo(int i2);

    void start();

    void stop();
}
