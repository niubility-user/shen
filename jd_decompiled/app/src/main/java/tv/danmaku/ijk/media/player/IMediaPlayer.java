package tv.danmaku.ijk.media.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* loaded from: classes11.dex */
public interface IMediaPlayer {
    public static final int MEDIA_ERROR_IO = -1004;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    public static final int MEDIA_ERROR_SERVER_DIED = 100;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_ERROR_UNSUPPORTED = -1010;
    public static final int MEDIA_INFO_AUDIO_DECODED_START = 10003;
    public static final int MEDIA_INFO_AUDIO_RENDERING_START = 10002;
    public static final int MEDIA_INFO_AUDIO_SEEK_RENDERING_START = 10009;
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_COMPONENT_OPEN = 10007;
    public static final int MEDIA_INFO_DNS_OBTAINED = 10307;
    public static final int MEDIA_INFO_FIND_STREAM_INFO = 10006;
    public static final int MEDIA_INFO_HTTP_CONNECTED = 10306;
    public static final int MEDIA_INFO_JD_LOOP_COMPLETED = 30003;
    public static final int MEDIA_INFO_JD_WILL_OPEN_INPUT = 30001;
    public static final int MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE = 10100;
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;
    public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
    public static final int MEDIA_INFO_OPEN_INPUT = 10005;
    public static final int MEDIA_INFO_STARTED_AS_NEXT = 2;
    public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
    public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    public static final int MEDIA_INFO_VIDEO_DECODED_START = 10004;
    public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    public static final int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
    public static final int MEDIA_INFO_VIDEO_SEEK_RENDERING_START = 10008;
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    public static final int MEDIA_INFO_WILL_FIND_STREAM_INFO = 30002;

    /* loaded from: classes11.dex */
    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2);
    }

    /* loaded from: classes11.dex */
    public interface OnCompletionListener {
        void onCompletion(IMediaPlayer iMediaPlayer);
    }

    /* loaded from: classes11.dex */
    public interface OnErrorListener {
        public static final int ERROR_NO_AUTH = -1;

        boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3);
    }

    /* loaded from: classes11.dex */
    public interface OnExtInfoListener {
        void onExtInfo(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, HashMap<String, Object> hashMap);
    }

    /* loaded from: classes11.dex */
    public interface OnInfoListener {
        boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3);
    }

    /* loaded from: classes11.dex */
    public interface OnMediaCodecSelectListener {
        String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i2, int i3);
    }

    /* loaded from: classes11.dex */
    public interface OnNativeInvokeListener {
        public static final String ARG_ERROR = "error";
        public static final String ARG_FAMILIY = "family";
        public static final String ARG_FD = "fd";
        public static final String ARG_FILE_SIZE = "file_size";
        public static final String ARG_HTTP_CODE = "http_code";
        public static final String ARG_IP = "ip";
        public static final String ARG_OFFSET = "offset";
        public static final String ARG_PORT = "port";
        public static final String ARG_RETRY_COUNTER = "retry_counter";
        public static final String ARG_SEGMENT_INDEX = "segment_index";
        public static final String ARG_URL = "url";
        public static final int CTRL_DID_QUIC_OPEN = 131083;
        public static final int CTRL_DID_TCP_OPEN = 131074;
        public static final int CTRL_WILL_CONCAT_RESOLVE_SEGMENT = 131079;
        public static final int CTRL_WILL_HTTP_OPEN = 131075;
        public static final int CTRL_WILL_LIVE_OPEN = 131077;
        public static final int CTRL_WILL_TCP_OPEN = 131073;
        public static final int EVENT_DID_DNS_OPEN = 6;
        public static final int EVENT_DID_HTTP_OPEN = 2;
        public static final int EVENT_DID_HTTP_SEEK = 4;
        public static final int EVENT_LAS_OPEN_INDEX = 77825;
        public static final int EVENT_WILL_DNS_OPEN = 5;
        public static final int EVENT_WILL_HTTP_OPEN = 1;
        public static final int EVENT_WILL_HTTP_SEEK = 3;

        boolean onNativeInvoke(int i2, Bundle bundle);
    }

    /* loaded from: classes11.dex */
    public interface OnPlayerEventListener {
        public static final int PLAYER_AUDIO_RENDERING_START = 11;
        public static final int PLAYER_BUFFERING = 7;
        public static final int PLAYER_BUFFER_END = 8;
        public static final int PLAYER_COMPLETED = 3;
        public static final int PLAYER_ERROR = 4;
        public static final int PLAYER_IDLE = 0;
        public static final int PLAYER_LOOP_COMPLETED = 12;
        public static final int PLAYER_PAUSE = 2;
        public static final int PLAYER_PREPARED = 6;
        public static final int PLAYER_REDRAW_REUSE = 15;
        public static final int PLAYER_RELEASE = 14;
        public static final int PLAYER_RENDERING_START = 9;
        public static final int PLAYER_RESET = 13;
        public static final int PLAYER_RREDRAW_RESET = 17;
        public static final int PLAYER_RREDRAW_START = 16;
        public static final int PLAYER_SEEK_RENDERING_START = 10;
        public static final int PLAYER_START = 1;
        public static final int PLAYER_STOP = 5;

        void onPlayEvent(int i2);
    }

    /* loaded from: classes11.dex */
    public interface OnPreparedListener {
        void onPrepared(IMediaPlayer iMediaPlayer);
    }

    /* loaded from: classes11.dex */
    public interface OnSeekCompleteListener {
        void onSeekComplete(IMediaPlayer iMediaPlayer);
    }

    /* loaded from: classes11.dex */
    public interface OnSeiListener {
        void onSeiText(IMediaPlayer iMediaPlayer, String str);
    }

    /* loaded from: classes11.dex */
    public interface OnTimedTextListener {
        void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText);
    }

    /* loaded from: classes11.dex */
    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5);
    }

    int getAudioSessionId();

    long getCurrentPosition();

    String getDataSource();

    long getDuration();

    MediaInfo getMediaInfo();

    float getPropertyFloat(int i2);

    long getPropertyLong(int i2);

    ITrackInfo[] getTrackInfo();

    int getVideoHeight();

    int getVideoSarDen();

    int getVideoSarNum();

    int getVideoWidth();

    boolean isLooping();

    boolean isPlaying();

    void notifyPlayEvent(int i2);

    void notifyPlayState(int i2, long j2, int[] iArr, int[] iArr2);

    void pause();

    void prepareAsync() throws IllegalStateException;

    void release();

    void reset();

    void resetListeners();

    void seekTo(long j2) throws IllegalStateException;

    void setAudioStreamType(int i2);

    void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    @TargetApi(14)
    void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException;

    void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    void setDataSource(IMediaDataSource iMediaDataSource);

    void setDisplay(SurfaceHolder surfaceHolder);

    void setLoopCount(int i2);

    void setLooping(boolean z);

    void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener);

    void setOnCompletionListener(OnCompletionListener onCompletionListener);

    void setOnErrorListener(OnErrorListener onErrorListener);

    void setOnExtInfoListener(OnExtInfoListener onExtInfoListener);

    void setOnInfoListener(OnInfoListener onInfoListener);

    void setOnMediaCodecSelectListener(OnMediaCodecSelectListener onMediaCodecSelectListener);

    void setOnNativeInvokeListener(OnNativeInvokeListener onNativeInvokeListener);

    void setOnPlayerEventListener(OnPlayerEventListener onPlayerEventListener);

    void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener);

    void setOnPreparedListener(OnPreparedListener onPreparedListener);

    void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener);

    void setOnSeiListener(OnSeiListener onSeiListener);

    void setOnTimedTextListener(OnTimedTextListener onTimedTextListener);

    void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener);

    void setOption(int i2, String str, long j2);

    void setOption(int i2, String str, String str2);

    void setPropertyFloat(int i2, float f2);

    void setPropertyLong(int i2, long j2);

    void setScreenOnWhilePlaying(boolean z);

    void setSurface(Surface surface);

    void setVolume(float f2, float f3);

    @Deprecated
    void setWakeMode(Context context, int i2);

    void start();

    void stop();
}
