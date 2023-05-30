package com.jingdong.manto.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes16.dex */
public class MantoThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void post(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void post(Runnable runnable, int i2) {
        sHandler.postDelayed(runnable, i2);
    }

    public static void runOnUIThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void runOnUIThreadImmediately(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isMainThread()) {
            runnable.run();
        } else {
            sHandler.postAtFrontOfQueue(runnable);
        }
    }
}
