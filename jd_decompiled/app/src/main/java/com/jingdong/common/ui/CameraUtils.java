package com.jingdong.common.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

/* loaded from: classes6.dex */
public class CameraUtils {
    @SuppressLint({"NewApi"})
    public static boolean checkCameraHardware(Context context) {
        return Build.VERSION.SDK_INT <= 7 || context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }
}
