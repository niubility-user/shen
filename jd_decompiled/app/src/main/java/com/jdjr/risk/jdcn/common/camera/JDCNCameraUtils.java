package com.jdjr.risk.jdcn.common.camera;

import android.content.Context;
import android.hardware.Camera;

/* loaded from: classes18.dex */
public class JDCNCameraUtils {
    public static boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static Camera openCamera() {
        try {
            return Camera.open();
        } catch (Exception e2) {
            throw e2;
        }
    }
}
