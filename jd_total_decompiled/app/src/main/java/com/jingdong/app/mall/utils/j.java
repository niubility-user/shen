package com.jingdong.app.mall.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public class j {
    private static volatile j d;
    private r a;
    private Handler b;

    /* renamed from: c  reason: collision with root package name */
    private Runnable f11792c = new a();

    /* loaded from: classes4.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (j.this.a != null) {
                j.this.a.a(false);
            }
        }
    }

    public static synchronized j c() {
        j jVar;
        synchronized (j.class) {
            if (d == null) {
                synchronized (j.class) {
                    if (d == null) {
                        d = new j();
                    }
                }
            }
            jVar = d;
        }
        return jVar;
    }

    public void b() {
        e();
        d = null;
    }

    public void d() {
        if (this.b == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            this.b = handler;
            handler.postDelayed(this.f11792c, 10000L);
        }
    }

    public void e() {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacks(this.f11792c);
        }
    }

    public void f(r rVar) {
        this.a = rVar;
    }

    public void g(boolean z) {
        r rVar = this.a;
        if (rVar != null) {
            rVar.a(z);
        }
        if (z) {
            e();
        }
    }
}
