package com.facebook.react.devsupport;

import android.os.Build;

/* loaded from: classes12.dex */
class WindowOverlayCompat {
    private static final int ANDROID_OREO = 26;
    private static final int TYPE_APPLICATION_OVERLAY = 2038;
    static final int TYPE_SYSTEM_ALERT;
    static final int TYPE_SYSTEM_OVERLAY;

    static {
        int i2 = Build.VERSION.SDK_INT;
        TYPE_SYSTEM_ALERT = i2 < 26 ? 2003 : 2038;
        TYPE_SYSTEM_OVERLAY = i2 < 26 ? 2006 : 2038;
    }

    WindowOverlayCompat() {
    }
}
