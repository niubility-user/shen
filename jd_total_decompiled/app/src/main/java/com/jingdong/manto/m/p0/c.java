package com.jingdong.manto.m.p0;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import com.jingdong.manto.utils.MantoLog;
import java.io.IOException;

/* loaded from: classes15.dex */
public class c implements IAudioPlayer {
    private MediaPlayer a;
    private volatile int b;

    /* renamed from: c  reason: collision with root package name */
    private volatile int f13496c;
    private volatile int d;

    /* renamed from: e  reason: collision with root package name */
    private volatile String f13497e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f13498f;

    /* renamed from: g  reason: collision with root package name */
    private IAudioPlayer.AudioListener f13499g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f13500h = true;

    /* renamed from: i  reason: collision with root package name */
    private boolean f13501i = true;

    /* renamed from: j  reason: collision with root package name */
    private boolean f13502j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f13503k;

    /* loaded from: classes15.dex */
    class a implements MediaPlayer.OnCompletionListener {
        a() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (c.this.a.equals(mediaPlayer)) {
                if (c.this.f13502j) {
                    c.this.f13502j = false;
                    return;
                }
                c.this.f13501i = true;
                if (c.this.f13499g != null) {
                    c.this.f13499g.onStateChange(IAudioPlayer.AUDIO_STATE_ENDED, null, 0);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements MediaPlayer.OnBufferingUpdateListener {
        b() {
        }

        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
            if (c.this.a.equals(mediaPlayer)) {
                c.this.b = ((int) ((i2 / 100.0f) * r2.a.getDuration())) / 1000;
            }
        }
    }

    /* renamed from: com.jingdong.manto.m.p0.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0590c implements MediaPlayer.OnErrorListener {
        C0590c() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            IAudioPlayer.AudioListener audioListener;
            int i4;
            String str;
            if (c.this.a.equals(mediaPlayer)) {
                c.this.f13502j = true;
                if (i2 != -38 && i3 != 0 && c.this.f13499g != null) {
                    if (i2 == 100) {
                        audioListener = c.this.f13499g;
                        i4 = 10001;
                        str = "media server died";
                    } else if (i3 == -1004 || i3 == -110) {
                        audioListener = c.this.f13499g;
                        i4 = 10002;
                        str = "media network error";
                    } else if (i3 == -1010) {
                        audioListener = c.this.f13499g;
                        i4 = 10004;
                        str = "media format error";
                    } else if (i3 == -1007) {
                        audioListener = c.this.f13499g;
                        i4 = 10003;
                        str = "media file error";
                    } else {
                        audioListener = c.this.f13499g;
                        i4 = -1;
                        str = "media unknown error";
                    }
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, str, i4);
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements IAudioPlayer.OnPreparedListener {
        d() {
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepareError(Throwable th) {
            if (!(th instanceof IOException) || c.this.f13499g == null) {
                return;
            }
            c.this.f13499g.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "unknown format", 10004);
        }

        @Override // com.jingdong.manto.sdk.api.IAudioPlayer.OnPreparedListener
        public void onPrepared() {
            c.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements MediaPlayer.OnSeekCompleteListener {
        e() {
        }

        @Override // android.media.MediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            c.this.a.start();
            if (c.this.f13499g != null) {
                c.this.f13499g.onStateChange(IAudioPlayer.AUDIO_STATE_PLAY, null, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class f implements MediaPlayer.OnPreparedListener {
        final /* synthetic */ IAudioPlayer.OnPreparedListener a;

        f(IAudioPlayer.OnPreparedListener onPreparedListener) {
            this.a = onPreparedListener;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            c.this.setPrepared(true);
            if (c.this.f13499g != null) {
                c.this.f13499g.onStateChange(IAudioPlayer.AUDIO_STATE_CAN_PLAY, null, 0);
            }
            IAudioPlayer.OnPreparedListener onPreparedListener = this.a;
            if (onPreparedListener != null) {
                onPreparedListener.onPrepared();
            }
            c.this.a.setOnPreparedListener(null);
        }
    }

    /* loaded from: classes15.dex */
    class g implements MediaPlayer.OnSeekCompleteListener {
        final /* synthetic */ IAudioPlayer.OnSeekCompleteListener a;

        g(c cVar, IAudioPlayer.OnSeekCompleteListener onSeekCompleteListener) {
            this.a = onSeekCompleteListener;
        }

        @Override // android.media.MediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            IAudioPlayer.OnSeekCompleteListener onSeekCompleteListener = this.a;
            if (onSeekCompleteListener != null) {
                onSeekCompleteListener.onSeekComplete(mediaPlayer);
            }
        }
    }

    public c() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.a = mediaPlayer;
        if (Build.VERSION.SDK_INT >= 21) {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).build());
        } else {
            mediaPlayer.setAudioStreamType(3);
        }
        this.a.setOnCompletionListener(new a());
        this.a.setOnBufferingUpdateListener(new b());
        this.a.setOnErrorListener(new C0590c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            if (this.f13500h || this.f13501i) {
                this.f13500h = false;
                this.f13501i = false;
                mediaPlayer.setOnSeekCompleteListener(new e());
                this.a.seekTo(this.f13496c * 1000);
                return;
            }
            mediaPlayer.start();
            IAudioPlayer.AudioListener audioListener = this.f13499g;
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_PLAY, null, 0);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public IAudioPlayer.AudioListener getAudioListener() {
        return this.f13499g;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public int getBuffered() {
        try {
            if (!TextUtils.isEmpty(this.f13497e) && !this.f13497e.startsWith("http") && isPrepared()) {
                this.b = this.a.getDuration() / 1000;
            }
            return this.b;
        } catch (Throwable unused) {
            return this.b;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public int getCurrentPosition() {
        try {
            if (this.a == null || !isPrepared()) {
                return 0;
            }
            return this.a.getCurrentPosition() / 1000;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public String getDataSource() {
        return this.f13497e;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public int getDuration() {
        if (this.d == 0 && this.a != null && isPrepared()) {
            this.d = this.a.getDuration() / 1000;
        }
        return this.d;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public int getStartTime() {
        return this.f13496c;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public boolean isLooping() {
        try {
            if (this.a == null || !isPrepared()) {
                return false;
            }
            return this.a.isLooping();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public boolean isPlaying() {
        try {
            if (this.a == null || !isPrepared()) {
                return false;
            }
            return this.a.isPlaying();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public boolean isPrepared() {
        return this.f13498f;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void pause() {
        try {
            MediaPlayer mediaPlayer = this.a;
            if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
                return;
            }
            this.a.pause();
            IAudioPlayer.AudioListener audioListener = this.f13499g;
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_PAUSE, null, 0);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void prepare(boolean z, IAudioPlayer.OnPreparedListener onPreparedListener) {
        this.f13503k = z;
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setOnPreparedListener(new f(onPreparedListener));
                IAudioPlayer.AudioListener audioListener = this.f13499g;
                if (audioListener != null) {
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_WAITING, null, 0);
                }
                if (z) {
                    this.a.prepareAsync();
                    return;
                }
                this.a.prepare();
                setPrepared(true);
            } catch (Throwable th) {
                MantoLog.e("Audio.DefaultPlayer", "prepare error", th);
                if (onPreparedListener == null) {
                    throw new IllegalStateException(th);
                }
                onPreparedListener.onPrepareError(th);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void release() {
        try {
            MediaPlayer mediaPlayer = this.a;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                setPrepared(false);
                this.a = null;
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void seekTo(int i2) {
        try {
            if (this.a != null) {
                IAudioPlayer.AudioListener audioListener = this.f13499g;
                if (audioListener != null) {
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_SEEKING, null, 0);
                }
                this.a.seekTo(i2);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setAudioListener(IAudioPlayer.AudioListener audioListener) {
        this.f13499g = audioListener;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setAutoPlay(boolean z) {
        if (!z || isPlaying()) {
            return;
        }
        start();
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setDataSource(String str) {
        if (this.a != null) {
            try {
                if (TextUtils.equals(str, this.f13497e)) {
                    return;
                }
                this.f13500h = true;
                this.f13501i = true;
                this.b = 0;
                this.d = 0;
                this.a.reset();
                this.a.setDataSource(str);
                this.f13497e = str;
            } catch (Throwable unused) {
                IAudioPlayer.AudioListener audioListener = this.f13499g;
                if (audioListener != null) {
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "media data source error", 10003);
                }
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setLooping(boolean z) {
        try {
            MediaPlayer mediaPlayer = this.a;
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(z);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setOnSeekCompleteListener(IAudioPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            mediaPlayer.setOnSeekCompleteListener(new g(this, onSeekCompleteListener));
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setPrepared(boolean z) {
        this.f13498f = z;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setStartTime(int i2) {
        this.f13496c = i2;
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void setVolume(float f2) {
        try {
            MediaPlayer mediaPlayer = this.a;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(f2, f2);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void start() {
        if (isPrepared()) {
            a();
            return;
        }
        try {
            this.a.reset();
            this.a.setDataSource(this.f13497e);
            prepare(this.f13503k, new d());
        } catch (Throwable unused) {
            IAudioPlayer.AudioListener audioListener = this.f13499g;
            if (audioListener != null) {
                audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_ERROR, "play error", 10004);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IAudioPlayer
    public void stop() {
        try {
            MediaPlayer mediaPlayer = this.a;
            if (mediaPlayer != null) {
                this.f13500h = true;
                mediaPlayer.setOnSeekCompleteListener(null);
                if (Build.VERSION.SDK_INT >= 26) {
                    this.a.seekTo(0L, 3);
                } else {
                    this.a.seekTo(0);
                }
                try {
                    Thread.sleep(200L);
                } catch (Throwable unused) {
                }
                IAudioPlayer.AudioListener audioListener = this.f13499g;
                if (audioListener != null) {
                    audioListener.onStateChange(IAudioPlayer.AUDIO_STATE_STOP, null, 0);
                }
                this.a.pause();
            }
        } catch (Throwable unused2) {
        }
    }
}
