package com.jingdong.pdj.libdjbasecore.utils;

/* loaded from: classes7.dex */
public class ClickFilter {
    public static final long INTERVAL = 500;
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(500L);
    }

    public static boolean isFastDoubleClick(long j2) {
        if (j2 <= 0) {
            j2 = 500;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j3 = currentTimeMillis - lastClickTime;
        if (0 >= j3 || j3 >= j2) {
            lastClickTime = currentTimeMillis;
            return false;
        }
        return true;
    }
}
