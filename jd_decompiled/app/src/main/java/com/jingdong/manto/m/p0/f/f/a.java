package com.jingdong.manto.m.p0.f.f;

import android.media.MediaRecorder;
import android.os.Build;
import com.jingdong.manto.m.p0.f.c;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes15.dex */
public class a implements c {
    private MediaRecorder a;
    private com.jingdong.manto.m.p0.f.b b;

    /* renamed from: c  reason: collision with root package name */
    private String f13543c;

    /* renamed from: com.jingdong.manto.m.p0.f.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0599a implements MediaRecorder.OnErrorListener {
        C0599a() {
        }

        @Override // android.media.MediaRecorder.OnErrorListener
        public void onError(MediaRecorder mediaRecorder, int i2, int i3) {
            MantoLog.e("Audio.AACRecorder", "onError: what:" + i2 + ", extra:" + i3);
            if (a.this.b != null) {
                a.this.b.a("recorder error(" + i2 + ")");
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements MediaRecorder.OnInfoListener {
        b() {
        }

        @Override // android.media.MediaRecorder.OnInfoListener
        public void onInfo(MediaRecorder mediaRecorder, int i2, int i3) {
            MantoLog.i("Audio.AACRecorder", "onInfo: what:" + i2 + ", extra:" + i3);
            if (i2 != 800 || a.this.b == null) {
                return;
            }
            a.this.b.a();
        }
    }

    public a(com.jingdong.manto.m.p0.f.b bVar) {
        this.b = bVar;
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void a() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        try {
            mediaRecorder.prepare();
        } catch (Throwable th) {
            MantoLog.e("Audio.AACRecorder", "prepare error", th);
        }
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void a(String str, int i2, int i3, int i4, int i5) {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        this.f13543c = str;
        mediaRecorder.setAudioSource(0);
        this.a.setOutputFormat(6);
        this.a.setAudioEncoder(3);
        this.a.setMaxDuration(i2);
        this.a.setAudioSamplingRate(i3);
        this.a.setAudioEncodingBitRate(i4);
        this.a.setAudioChannels(i5);
        this.a.setOutputFile(str);
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void b() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        mediaRecorder.reset();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public int getDuration() {
        return com.jingdong.manto.m.p0.f.a.a(this.f13543c);
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void init() {
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.a = mediaRecorder;
        mediaRecorder.setOnErrorListener(new C0599a());
        this.a.setOnInfoListener(new b());
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void pause() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null || Build.VERSION.SDK_INT < 24) {
            return;
        }
        mediaRecorder.pause();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void release() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        mediaRecorder.release();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void resume() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null || Build.VERSION.SDK_INT < 24) {
            return;
        }
        mediaRecorder.resume();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void start() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        mediaRecorder.start();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void stop() {
        MediaRecorder mediaRecorder = this.a;
        if (mediaRecorder == null) {
            return;
        }
        mediaRecorder.stop();
    }
}
