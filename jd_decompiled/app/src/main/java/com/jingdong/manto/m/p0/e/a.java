package com.jingdong.manto.m.p0.e;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes15.dex */
public class a {
    private static volatile HandlerThread a;
    private static Object b;

    /* renamed from: c  reason: collision with root package name */
    private static Handler f13524c;

    static {
        new AtomicInteger(0);
        b = new Object();
    }

    private static void a() {
        if (a == null) {
            a = new HandlerThread("audio_player_thread");
            a.start();
        }
        if (f13524c == null) {
            f13524c = new Handler(a.getLooper());
        }
    }

    public static void a(Runnable runnable) {
        synchronized (b) {
            if (f13524c == null) {
                a();
            }
            f13524c.post(runnable);
        }
    }
}
