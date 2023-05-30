package com.jdpay.lib.util;

import android.annotation.TargetApi;
import android.app.Activity;
import androidx.annotation.NonNull;
import com.jdpay.system.SystemInfo;

/* loaded from: classes18.dex */
public class JPPermission {
    @TargetApi(23)
    public static boolean isGranted(@NonNull Activity activity, @NonNull String[] strArr) {
        if (isRequired()) {
            for (String str : strArr) {
                if (activity.checkSelfPermission(str) != 0) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static boolean isRequired() {
        return SystemInfo.getAndroidSdkVersion() >= 23;
    }

    @TargetApi(23)
    public static void requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, int i2) {
        if (isRequired()) {
            activity.requestPermissions(strArr, i2);
        }
    }

    public static boolean isGranted(@NonNull int[] iArr) {
        for (int i2 : iArr) {
            if (i2 != 0) {
                return false;
            }
        }
        return true;
    }
}
