package com.jingdong.sdk.jdcrashreport.e.a;

import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.crash.jni.NativeMonitor;

/* loaded from: classes7.dex */
public class b {
    private static final b b = new b();

    /* renamed from: c  reason: collision with root package name */
    private static long f14910c = 1000;
    private FileObserver a = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a extends FileObserver {
        a(b bVar, String str, int i2) {
            super(str, i2);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i2, String str) {
            if (str == null || !(str.contains("trace") || str.contains("anr"))) {
                r.d("JDCrashReport", "Not a trace file: %s", String.valueOf(str));
                return;
            }
            try {
                r.b("JDCrashReport", "AnrMonitor fileObserver onEvent");
                d.b().e("/data/anr/" + str, false);
            } catch (Exception e2) {
                r.c("JDCrashReport", "AnrMonitor fileObserver onEvent failed", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.jdcrashreport.e.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public class RunnableC0718b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f14911g;

        RunnableC0718b(Context context) {
            this.f14911g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = new c(b.this, new Handler(Looper.getMainLooper()));
            while (true) {
                if (!cVar.b()) {
                    cVar.a();
                }
                try {
                    Thread.sleep(b.f14910c);
                } catch (InterruptedException unused) {
                }
                if (cVar.b() && !cVar.c()) {
                    r.b("JDCrashReport", "main thread is blocked and hasn't been handled");
                    r.b("JDCrashReport", "check process state");
                    if (com.jingdong.sdk.jdcrashreport.e.a.c.d(this.f14911g, 20000L)) {
                        r.b("JDCrashReport", "find anr!");
                        com.jingdong.sdk.jdcrashreport.d.s("anr", true);
                        r.b("JDCrashReport", "send notification and dump trace!");
                        NativeMonitor.a().b();
                        cVar.d();
                    }
                }
            }
        }
    }

    /* loaded from: classes7.dex */
    private class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private Handler f14913g;

        /* renamed from: i  reason: collision with root package name */
        private long f14915i;

        /* renamed from: h  reason: collision with root package name */
        private boolean f14914h = false;

        /* renamed from: j  reason: collision with root package name */
        private boolean f14916j = false;

        c(b bVar, Handler handler) {
            this.f14913g = handler;
        }

        private void e() {
            this.f14914h = true;
            this.f14916j = false;
            this.f14915i = SystemClock.uptimeMillis();
        }

        public final void a() {
            if (this.f14914h) {
                return;
            }
            e();
            this.f14913g.postAtFrontOfQueue(this);
        }

        boolean b() {
            return this.f14914h && SystemClock.uptimeMillis() - this.f14915i > b.f14910c;
        }

        boolean c() {
            return this.f14916j;
        }

        void d() {
            this.f14916j = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f14914h = false;
            com.jingdong.sdk.jdcrashreport.d.s("anr", false);
        }
    }

    private b() {
    }

    public static b a() {
        return b;
    }

    private void d(Context context) {
        new Thread(new RunnableC0718b(context)).start();
    }

    private void e() {
        a aVar = new a(this, "/data/anr/", 8);
        this.a = aVar;
        try {
            aVar.startWatching();
        } catch (Exception e2) {
            this.a = null;
            r.c("JDCrashReport", "AnrMonitor fileObserver startWatching failed", e2);
        }
    }

    public synchronized void b(Context context) {
        d.b().c(context);
        if (Build.VERSION.SDK_INT >= 21) {
            d(context);
        } else {
            e();
        }
    }
}
