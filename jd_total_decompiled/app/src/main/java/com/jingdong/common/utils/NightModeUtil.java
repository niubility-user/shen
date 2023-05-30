package com.jingdong.common.utils;

import android.app.Activity;
import android.view.WindowManager;
import com.jingdong.jdsdk.constant.Constants;

/* loaded from: classes6.dex */
public class NightModeUtil {
    private static final String TAG = "NightModeUtil";
    public static final float adjustVal = 0.005f;

    public static float getScreenBrightness(Activity activity) {
        Activity parent = activity.getParent();
        while (true) {
            Activity activity2 = parent;
            Activity activity3 = activity;
            activity = activity2;
            if (activity != null) {
                parent = activity.getParent();
            } else {
                return activity3.getWindow().getAttributes().screenBrightness;
            }
        }
    }

    public static void setNightModeAlpha(Activity activity, float f2, boolean z) {
        if (!CommonBase.getBooleanFromPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SWITCH, Boolean.FALSE).booleanValue() && !z) {
            return;
        }
        Activity parent = activity.getParent();
        while (true) {
            Activity activity2 = parent;
            Activity activity3 = activity;
            activity = activity2;
            if (activity != null) {
                parent = activity.getParent();
            } else {
                WindowManager.LayoutParams attributes = activity3.getWindow().getAttributes();
                attributes.screenBrightness = f2;
                activity3.getWindow().setAttributes(attributes);
                return;
            }
        }
    }

    public static void setNightModeAlpha(Activity activity) {
        float f2 = CommonBase.getBooleanFromPreference(Constants.SHARED_PREFERENCES_NIGHT_MODE_SWITCH, Boolean.FALSE).booleanValue() ? CommonBase.getJdSharedPreferences().getFloat(Constants.SHARED_PREFERENCES_NIGHT_MODE_ALPHA, -1.0f) : -1.0f;
        Activity parent = activity.getParent();
        while (true) {
            Activity activity2 = parent;
            Activity activity3 = activity;
            activity = activity2;
            if (activity != null) {
                parent = activity.getParent();
            } else {
                WindowManager.LayoutParams attributes = activity3.getWindow().getAttributes();
                attributes.screenBrightness = f2;
                activity3.getWindow().setAttributes(attributes);
                return;
            }
        }
    }
}
