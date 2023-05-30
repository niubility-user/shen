package com.jingdong.manto.jsapi.refact.video;

/* loaded from: classes15.dex */
public interface IVideoInterface {
    public static final String onVideoClickFullScreenEvent = "onVideoFullScreenChange";
    public static final String onVideoEndedEvent = "onVideoEnded";
    public static final String onVideoErrorEvent = "onVideoError";
    public static final String onVideoLoadStartEvent = "onVideoLoadStart";
    public static final String onVideoLoadedDataEvent = "onVideoLoadedData";
    public static final String onVideoLoadedMetaDataEvent = "onVideoLoadedMetaData";
    public static final String onVideoPauseEvent = "onVideoPause";
    public static final String onVideoPlayEvent = "onVideoPlay";
    public static final String onVideoSeekedEvent = "onVideoSeeked";
    public static final String onVideoSeekingEvent = "onVideoSeeking";
    public static final String onVideoTimeUpdateEvent = "onVideoTimeUpdate";
    public static final String onVideoWaitingEvent = "onVideoWaiting";

    void onEnd(int i2);

    void onError(int i2, int i3, int i4);

    void onFullScreenChange(int i2, boolean z, String str);

    void onLoadedData(int i2);

    void onLoadedMetaData(int i2);

    void onLoadedStart(int i2);

    void onPause(int i2);

    void onPlay(int i2);

    void onSeeked(int i2);

    void onSeeking(int i2);

    void onWaiting(int i2);

    void timeUpdate(int i2);
}
