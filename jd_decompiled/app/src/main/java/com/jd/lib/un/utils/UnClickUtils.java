package com.jd.lib.un.utils;

/* loaded from: classes16.dex */
public class UnClickUtils {
    public static int COMMON_DELTA_TIME = 500;
    private static long lastClickTime;

    public static synchronized boolean isFastClick(long j2) {
        synchronized (UnClickUtils.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastClickTime < j2) {
                return true;
            }
            lastClickTime = currentTimeMillis;
            return false;
        }
    }

    public static synchronized boolean isFastClick() {
        boolean isFastClick;
        synchronized (UnClickUtils.class) {
            isFastClick = isFastClick(COMMON_DELTA_TIME);
        }
        return isFastClick;
    }
}
