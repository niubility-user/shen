package com.jingdong.app.mall.bundle.smile.utils;

import android.app.Activity;
import android.os.Build;

/* loaded from: classes3.dex */
public class ActivityUtil {
    public static boolean checkDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }
}
