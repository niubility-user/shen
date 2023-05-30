package com.jd.lib.flexcube.iwidget.ui.video;

/* loaded from: classes14.dex */
public interface FlexPlayerStateListener {
    public static final int PLAYER_COMPLETED = 3;
    public static final int PLAYER_PAUSE = 2;
    public static final int PLAYER_START = 1;
    public static final int PLAYER_STOP = 5;
    public static final int PLAYER_UI_AUTO = 0;
    public static final int PLAYER_UI_HAND = 1;

    void onCompletion();

    boolean onError(int i2, int i3);

    void onPlayState(int i2, int i3);

    void release();
}
