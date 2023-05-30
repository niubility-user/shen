package com.meizu.cloud.pushsdk.f.c.h;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes14.dex */
public class b {
    private static ExecutorService a = null;
    private static int b = 2;

    public static ExecutorService a() {
        synchronized (b.class) {
            if (a == null) {
                a = Executors.newScheduledThreadPool(b);
            }
        }
        return a;
    }

    public static Future b(Callable callable) {
        return a().submit(callable);
    }

    public static void c(int i2) {
        b = i2;
    }

    public static void d(Runnable runnable) {
        a().execute(runnable);
    }
}
