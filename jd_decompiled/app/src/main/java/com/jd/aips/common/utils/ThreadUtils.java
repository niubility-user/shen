package com.jd.aips.common.utils;

import android.os.Looper;

/* loaded from: classes12.dex */
public class ThreadUtils {
    private ThreadUtils() {
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }
}
