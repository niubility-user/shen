package tv.danmaku.ijk.media.player;

/* loaded from: classes11.dex */
public interface IjkEventListener {
    public static final int AUDIO_RENDERING_START = 403;
    public static final int BUFFERING_EDN = 501;
    public static final int BUFFERING_START = 500;
    public static final int BUFFERING_UPDATE = 502;
    public static final int CURRENT_POSITION_UPDATE = 510;
    public static final int ERROR = 100;
    public static final int PLAYBACK_STATE_CHANGED = 700;
    public static final int PREPARED = 200;
    public static final int SEEK_COMPLETED = 600;
    public static final int VIDEO_RENDERING_START = 402;
    public static final int VIDEO_ROTATION_CHANGED = 404;
    public static final int VIDEO_SIZE_CHANGED = 400;

    void onEvent(IjkMediaPlayer ijkMediaPlayer, int i2, int i3, int i4, Object obj);
}
