package com.jingdong.sdk.perfmonitor.c;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes12.dex */
public abstract class a {

    /* renamed from: f  reason: collision with root package name */
    private static Handler f15399f;
    Context a;

    /* renamed from: c  reason: collision with root package name */
    private long f15400c;
    private long d;
    private AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private Runnable f15401e = new RunnableC0743a();

    /* renamed from: com.jingdong.sdk.perfmonitor.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class RunnableC0743a implements Runnable {
        RunnableC0743a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.this.f();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (a.this.b.get()) {
                a.f15399f.postDelayed(a.this.f15401e, a.this.f15400c);
            } else {
                a.this.h();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, long j2, long j3) {
        this.a = context;
        this.f15400c = j3;
        this.d = j2;
        if (f15399f == null) {
            HandlerThread handlerThread = new HandlerThread("perfmonitor-reader");
            handlerThread.start();
            f15399f = new Handler(handlerThread.getLooper());
        }
    }

    void e() {
    }

    abstract void f();

    public void g() {
        if (this.b.get() || this.f15400c <= 0) {
            return;
        }
        this.b.set(true);
        e();
        f15399f.removeCallbacks(this.f15401e);
        f15399f.postDelayed(this.f15401e, this.d);
    }

    public void h() {
        if (this.b.get()) {
            this.b.set(false);
            f15399f.removeCallbacks(this.f15401e);
        }
    }
}
