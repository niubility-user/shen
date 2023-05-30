package com.jd.aips.common.camera;

import android.content.Context;
import android.hardware.Camera;

/* loaded from: classes12.dex */
public class JDCNCameraUtils {
    public static boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static Camera openCamera() throws Exception {
        try {
            return Camera.open();
        } catch (Exception e2) {
            throw e2;
        }
    }
}
