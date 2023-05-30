package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

/* loaded from: classes5.dex */
public final class AppRTCUtils {
    private AppRTCUtils() {
    }

    public static void assertIsTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    public static void logDeviceInfo(String str) {
    }
}
