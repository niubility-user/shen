package com.tencent.smtt.sdk;

import android.graphics.SurfaceTexture;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class TbsMediaPlayer {
    private n a;

    /* loaded from: classes9.dex */
    public interface TbsMediaPlayerListener {
        public static final int MEDIA_EXTRA_AUDIOTRACK_INDEX = 104;
        public static final int MEDIA_EXTRA_AUDIOTRACK_TITLES = 103;
        public static final int MEDIA_EXTRA_SUBTITLE_COUNT = 101;
        public static final int MEDIA_EXTRA_SUBTITLE_INDEX = 102;
        public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
        public static final int MEDIA_INFO_BUFFERING_END = 702;
        public static final int MEDIA_INFO_BUFFERING_PERCENTAGE = 790;
        public static final int MEDIA_INFO_BUFFERING_START = 701;
        public static final int MEDIA_INFO_EXTERNAL_METADATA_UPDATE = 803;
        public static final int MEDIA_INFO_HAVE_VIDEO_DATA = 752;
        public static final int MEDIA_INFO_METADATA_UPDATE = 802;
        public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
        public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
        public static final int MEDIA_INFO_NO_VIDEO_DATA = 751;
        public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
        public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
        public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
        public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
        public static final int ROTATE_ACTION_HASROTATE = 3;
        public static final int ROTATE_ACTION_NOTHING = 1;
        public static final int ROTATE_ACTION_SETDEGREE = 2;
        public static final int ROTATE_ACTION_UNKNOWN = 0;

        void onBufferingUpdate(float f2);

        void onPlayerCompleted();

        void onPlayerError(String str, int i2, int i3, Throwable th);

        void onPlayerExtra(int i2, Object obj);

        void onPlayerInfo(int i2, int i3);

        void onPlayerPaused();

        void onPlayerPlaying();

        void onPlayerPrepared(long j2, int i2, int i3, int i4, int i5);

        void onPlayerProgress(long j2);

        void onPlayerSeeked(long j2);

        void onPlayerSubtitle(String str);
    }

    public TbsMediaPlayer(n nVar) {
        this.a = null;
        this.a = nVar;
    }

    public void audio(int i2) {
        this.a.b(i2);
    }

    public void close() {
        this.a.e();
    }

    public float getVolume() {
        return this.a.b();
    }

    public boolean isAvailable() {
        return this.a.a();
    }

    public void pause() {
        this.a.c();
    }

    public void play() {
        this.a.d();
    }

    public void seek(long j2) {
        this.a.a(j2);
    }

    public void setPlayerListener(TbsMediaPlayerListener tbsMediaPlayerListener) {
        this.a.a(tbsMediaPlayerListener);
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.a.a(surfaceTexture);
    }

    public void setVolume(float f2) {
        this.a.a(f2);
    }

    public void startPlay(String str, Bundle bundle) {
        this.a.a(str, bundle);
    }

    public void subtitle(int i2) {
        this.a.a(i2);
    }
}
