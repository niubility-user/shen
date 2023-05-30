package com.jd.aips.verify.face.utils;

import android.app.Activity;
import android.view.WindowManager;

/* loaded from: classes12.dex */
public class BrightnessTools {
    private float originalBrightness;

    public void increaseBrightness(Activity activity) {
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            this.originalBrightness = attributes.screenBrightness;
            attributes.screenBrightness = 1.0f;
            activity.getWindow().setAttributes(attributes);
        } catch (Exception unused) {
        }
    }

    public void resetBrightness(Activity activity) {
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            this.originalBrightness = attributes.screenBrightness;
            attributes.screenBrightness = 1.0f;
            activity.getWindow().setAttributes(attributes);
        } catch (Exception unused) {
        }
    }
}
