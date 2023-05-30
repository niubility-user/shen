package com.jingdong.jdsdk.utils;

import android.os.Build;

/* loaded from: classes14.dex */
public class SDKUtils {
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isSDKVersionMoreThan16() {
        return getSDKVersion() > 4;
    }

    public static boolean isSDKVersionMoreThan20() {
        return getSDKVersion() > 6;
    }

    public static boolean isSDKVersionMoreThan21() {
        return getSDKVersion() > 7;
    }

    public static boolean isSDKVersionMoreThan23() {
        return getSDKVersion() > 10;
    }
}
