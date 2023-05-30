package com.jd.voice.jdvoicesdk.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import com.jd.voice.jdvoicesdk.d.b;
import java.io.File;
import java.io.IOException;

/* loaded from: classes18.dex */
public class a {
    private Context a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private MediaRecorder f7208c;

    /* renamed from: e  reason: collision with root package name */
    private b.c f7209e;
    private boolean d = false;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f7210f = new Handler();

    /* renamed from: g  reason: collision with root package name */
    private Runnable f7211g = new RunnableC0225a();

    /* renamed from: h  reason: collision with root package name */
    private int f7212h = 600;

    /* renamed from: i  reason: collision with root package name */
    private int f7213i = 300;

    /* renamed from: com.jd.voice.jdvoicesdk.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    class RunnableC0225a implements Runnable {
        RunnableC0225a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    }

    public a(Context context, b.c cVar) {
        this.b = "";
        this.a = context;
        this.f7209e = cVar;
        this.b = com.jd.voice.jdvoicesdk.util.a.b(context, ".amr");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MediaRecorder mediaRecorder = this.f7208c;
        if (mediaRecorder != null) {
            int maxAmplitude = mediaRecorder.getMaxAmplitude();
            int i2 = (maxAmplitude * 100) / 32768;
            int i3 = maxAmplitude / this.f7212h;
            int log10 = i3 > 1 ? (int) (Math.log10(i3) * 20.0d) : 0;
            b.c cVar = this.f7209e;
            if (cVar != null) {
                cVar.changVolumLevel(i2, log10);
            }
            this.f7210f.postDelayed(this.f7211g, this.f7213i);
        }
    }

    @TargetApi(10)
    public void b() {
        if (this.d) {
            return;
        }
        if (this.f7208c == null) {
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.f7208c = mediaRecorder;
            mediaRecorder.setAudioSource(1);
            if (Build.VERSION.SDK_INT < 10) {
                this.f7208c.setOutputFormat(3);
                this.f7208c.setAudioEncoder(1);
            } else {
                this.f7208c.setOutputFormat(4);
                this.f7208c.setAudioEncoder(2);
            }
            this.f7208c.setAudioChannels(1);
            this.f7208c.setAudioSamplingRate(8000);
            File file = new File(this.b);
            if (file.exists()) {
                file.delete();
            }
            this.f7208c.setOutputFile(this.b);
        }
        try {
            this.f7208c.prepare();
            this.f7208c.start();
            this.d = true;
            d();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
    }

    public void c() {
        MediaRecorder mediaRecorder = this.f7208c;
        if (mediaRecorder != null) {
            this.d = false;
            mediaRecorder.stop();
            this.f7208c.release();
            this.f7208c = null;
        }
    }
}
