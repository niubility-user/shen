package com.jingdong.app.mall.unifiedcontrol.recoder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.widget.Toast;
import com.jingdong.corelib.utils.Log;
import java.io.File;

/* loaded from: classes4.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private MediaRecorder f11654c;
    private b d;

    /* renamed from: e  reason: collision with root package name */
    private long f11655e;

    /* renamed from: f  reason: collision with root package name */
    private long f11656f;

    /* renamed from: g  reason: collision with root package name */
    private final Handler f11657g = new Handler();

    /* renamed from: h  reason: collision with root package name */
    private Runnable f11658h = new RunnableC0377a();

    /* renamed from: i  reason: collision with root package name */
    private int f11659i = 1;

    /* renamed from: j  reason: collision with root package name */
    private int f11660j = 100;

    /* renamed from: com.jingdong.app.mall.unifiedcontrol.recoder.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class RunnableC0377a implements Runnable {
        RunnableC0377a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f();
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onStop(String str);

        void onUpdate(double d, long j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        MediaRecorder mediaRecorder = this.f11654c;
        if (mediaRecorder != null) {
            double maxAmplitude = mediaRecorder.getMaxAmplitude();
            double d = this.f11659i;
            Double.isNaN(maxAmplitude);
            Double.isNaN(d);
            double d2 = maxAmplitude / d;
            if (d2 > 1.0d) {
                double log10 = Math.log10(d2) * 20.0d;
                b bVar = this.d;
                if (bVar != null) {
                    bVar.onUpdate(log10, System.currentTimeMillis() - this.f11655e);
                }
            }
            this.f11657g.postDelayed(this.f11658h, this.f11660j);
        }
    }

    public boolean b(Context context) {
        File file = new File(context.getExternalFilesDir(null), "jdrecord");
        if (!file.exists() && !file.mkdirs()) {
            Toast.makeText(context, "\u62b1\u6b49\uff0c\u5f55\u97f3\u6587\u4ef6\u76ee\u5f55\u751f\u6210\u5931\u8d25", 0).show();
            return false;
        }
        this.b = file.getAbsolutePath() + File.separatorChar;
        return true;
    }

    public void c(b bVar) {
        this.d = bVar;
    }

    public void d(Context context) {
        if (this.f11654c == null) {
            this.f11654c = new MediaRecorder();
        }
        try {
            this.f11654c.setAudioSource(1);
            this.f11654c.setOutputFormat(0);
            this.f11654c.setAudioEncoder(0);
            String str = this.b + "jdrecod.amr";
            this.a = str;
            this.f11654c.setOutputFile(str);
            this.f11654c.setMaxDuration(180000);
            this.f11654c.prepare();
            this.f11654c.start();
            this.f11655e = System.currentTimeMillis();
            f();
            if (Log.D) {
                Log.e("fan", "startTime" + this.f11655e);
            }
        } catch (Exception e2) {
            Toast.makeText(context, " \u65e0\u6cd5\u4f7f\u7528\u5f55\u97f3\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u8bbe\u5907", 0).show();
            if (Log.D) {
                Log.i("AudioRecoderUtils", "call startAmr(File mRecAudioFile) failed!" + e2.getMessage());
            }
            this.f11654c = null;
        }
    }

    public long e() {
        if (this.f11654c == null) {
            return 0L;
        }
        this.f11656f = System.currentTimeMillis();
        this.f11654c.stop();
        this.f11654c.reset();
        this.f11654c.release();
        this.f11654c = null;
        b bVar = this.d;
        if (bVar != null) {
            bVar.onStop(this.a);
        }
        this.a = "";
        return this.f11656f - this.f11655e;
    }
}
