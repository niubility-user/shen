package tv.danmaku.ijk.media.pha;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class JDHPlayerJSEvent implements Serializable {
    public static final String GET_CURRENTSRC = "getCurrentSrc";
    public static final String GET_ERROR = "getError";
    public static final String GET_NETTYPE = "getNetworkType";
    public static final String GET_PLAYBACKRATE = "getPlaybackRate";
    public static final String IS_AUTOPLAY = "isAutoplay";
    public static final String IS_ENDED = "isEnded";
    public static final String IS_LOOP = "isLoop";
    public static final String IS_MUTED = "isMuted";
    public static final String IS_PAUSED = "isPaused";
    public static final String IS_PLAYING = "isPlaying";
    public static final String PLAY_CUR_DURATION = "getCurrentTime";
    public static final String PLAY_DURATION = "getDuration";
    public static final String PLAY_GET_VOLUME = "getVolume";
    public static final String PLAY_MUTE = "mute";
    public static final String PLAY_PAUSE = "pause";
    public static final String PLAY_RELEASE = "release";
    public static final String PLAY_SEEK = "seekTo";
    public static final String PLAY_SET_VOLUME = "setVolume";
    public static final String PLAY_START = "play";
    public static final String PLAY_STOP = "stop";
    public String args;
    public String funName;
    public String tagId;

    public JDHPlayerJSEvent(String str, String str2, String str3) {
        this.tagId = str;
        this.funName = str2;
        this.args = str3;
    }
}
