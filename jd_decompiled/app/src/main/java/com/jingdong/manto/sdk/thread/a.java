package com.jingdong.manto.sdk.thread;

import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

/* loaded from: classes16.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static MantoHandler f14192c;
    private static MantoHandler d;
    private MantoHandler a = null;
    public HandlerThread b = null;

    public a(String str) {
        a(str);
    }

    public static void a(Runnable runnable) {
        if (runnable != null) {
            c().a(runnable);
        }
    }

    public static void a(Runnable runnable, long j2) {
        if (runnable != null) {
            d().a(runnable, j2);
        }
    }

    private void a(String str) {
        this.a = null;
        if (TextUtils.isEmpty(str)) {
            str = "MantoHandlerThread";
        }
        HandlerThread handlerThread = new HandlerThread("manto_" + str, 0);
        this.b = handlerThread;
        handlerThread.start();
    }

    public static void b(Runnable runnable) {
        if (runnable != null) {
            d().a(runnable);
        }
    }

    public static boolean b() {
        return Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();
    }

    private static MantoHandler c() {
        if (f14192c == null) {
            f14192c = new MantoHandler(Looper.getMainLooper());
        }
        return f14192c;
    }

    private static MantoHandler d() {
        if (d == null) {
            d = new a("worker-thread").a();
        }
        return d;
    }

    public final MantoHandler a() {
        if (this.a == null) {
            this.a = new MantoHandler(this.b.getLooper());
        }
        return this.a;
    }
}
