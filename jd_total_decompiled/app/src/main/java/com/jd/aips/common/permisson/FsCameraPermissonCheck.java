package com.jd.aips.common.permisson;

import android.content.Context;
import android.os.Build;

/* loaded from: classes12.dex */
public class FsCameraPermissonCheck {
    public static void hasCameraPermission(Context context, IFsPermissionTestCallback iFsPermissionTestCallback) {
        if (Build.VERSION.SDK_INT < 23) {
            iFsPermissionTestCallback.permissionTestCallback(0, 0);
            return;
        }
        try {
            new FsCameraTest(context).testAsync(iFsPermissionTestCallback);
        } catch (Throwable unused) {
            iFsPermissionTestCallback.permissionTestCallback(-1, 1);
        }
    }

    public static boolean hasCameraPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            return new FsCameraTest(context).test();
        } catch (Throwable unused) {
            return false;
        }
    }
}
