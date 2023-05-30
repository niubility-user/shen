package com.jingdong.sdk.jdhttpdns.core;

import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class PrivacyController {
    private static AtomicBoolean isBackground = new AtomicBoolean();

    public static boolean isBackground() {
        return isBackground.get();
    }

    public static void onAppBackground() {
        isBackground.set(true);
    }

    public static void onAppForeground() {
        isBackground.set(false);
    }
}
