package com.jingdong.common.utils;

import android.app.Activity;

/* loaded from: classes6.dex */
public class ActivityUtils {
    public static void setOverridePendingTransition(Activity activity, int i2, int i3) {
        if (activity == null) {
            return;
        }
        activity.overridePendingTransition(i2, i3);
    }
}
