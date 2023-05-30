package com.jingdong.manto.sdk.api;

import android.media.MediaPlayer;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IAudioPlayer extends IMantoSdkBase {
    public static final String AUDIO_STATE_CAN_PLAY = "CanPlay";
    public static final String AUDIO_STATE_ENDED = "Ended";
    public static final String AUDIO_STATE_ERROR = "Error";
    public static final String AUDIO_STATE_PAUSE = "Pause";
    public static final String AUDIO_STATE_PLAY = "Play";
    public static final String AUDIO_STATE_SEEKED = "Seeked";
    public static final String AUDIO_STATE_SEEKING = "Seeking";
    public static final String AUDIO_STATE_STOP = "Stop";
    public static final String AUDIO_STATE_WAITING = "Waiting";
    public static final int CODE_FILE_ERROR = 10003;
    public static final int CODE_FORMAT_ERROR = 10004;
    public static final int CODE_NET_ERROR = 10002;
    public static final int CODE_OK = 0;
    public static final int CODE_SYSTEM_ERROR = 10001;
    public static final int CODE_UNKNOWN_ERROR = -1;

    /* loaded from: classes16.dex */
    public interface AudioListener {
        void onStateChange(String str, String str2, int i2);
    }

    /* loaded from: classes16.dex */
    public interface OnPreparedListener {
        void onPrepareError(Throwable th);

        void onPrepared();
    }

    /* loaded from: classes16.dex */
    public interface OnSeekCompleteListener {
        void onSeekComplete(MediaPlayer mediaPlayer);
    }

    AudioListener getAudioListener();

    int getBuffered();

    int getCurrentPosition();

    String getDataSource();

    int getDuration();

    int getStartTime();

    boolean isLooping();

    boolean isPlaying();

    boolean isPrepared();

    void pause();

    void prepare(boolean z, OnPreparedListener onPreparedListener);

    void release();

    void seekTo(int i2);

    void setAudioListener(AudioListener audioListener);

    void setAutoPlay(boolean z);

    void setDataSource(String str);

    void setLooping(boolean z);

    void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener);

    void setPrepared(boolean z);

    void setStartTime(int i2);

    void setVolume(float f2);

    void start();

    void stop();
}
