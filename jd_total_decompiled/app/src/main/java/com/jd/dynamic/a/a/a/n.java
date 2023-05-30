package com.jd.dynamic.a.a.a;

import android.os.Handler;
import android.os.Looper;
import com.jd.dynamic.engine.jni.TypeConvertor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public class n {

    /* renamed from: c  reason: collision with root package name */
    private static Handler f1705c = new Handler(Looper.getMainLooper());
    private long a;
    private AtomicBoolean b = new AtomicBoolean(false);

    /* loaded from: classes13.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f1706g;

        a(long j2) {
            this.f1706g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (n.this.b.get()) {
                return;
            }
            TypeConvertor.JSFunctionCall(n.this.a, n.this.a, this.f1706g, com.jd.dynamic.a.h.d(n.this.a, new Object[0]));
        }
    }

    public n(long j2) {
        this.a = j2;
    }

    public void b() {
        this.b.set(true);
        f1705c.removeCallbacksAndMessages(null);
    }

    public void c(long j2, long j3) {
        if (this.b.get()) {
            return;
        }
        f1705c.postDelayed(new a(j2), j3);
    }
}
